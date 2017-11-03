package org.sdnhub.flowtags;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.lang.*;
import java.util.*;
import java.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PSIDAGModifier {

    private static volatile PSIDAGModifier instance;
    protected static final Logger log = LoggerFactory.getLogger(PSIDAGModifier.class);

    public static final String CONTROL_SWITCH     = "control_sw";
    public static final String CONTROL_NW_NETMASK = "255.255.255.0";
    public static final String CONTROL_IF         = "eth1";
    public static final String NET_IF             = "eth4";
    public static final String CONTROLLER_ADDRESS = "192.168.123.201:6633";
    public static final String HOST_DOMAIN_PREFIX     = "host";
    public static final String HOST_PREFIX            = "h";
    public static final String HOST_AGENT_PREFIX      = "ha";
    public static final String HOST_AGENT_NET_PORT    = "2";
    public static final String HOST_AGENT_HOST_PORT   = "1";
    public static final String SW_PREFIX              = "s";
    public static final String VSW_PREFIX             = "v";
    public static final String NET                    = "net";
    public static final String PSI_GATEWAY = "s1";
    public static final String INGRESS = "Ingress";
    public static final String EGRESS = "Egress";
    public static final String DROP = "Drop";
    public static final String SCOPE_SW = "s1";

    private SocketChannel socket;
    /* Control Middleboxes and Switches */
    private FTTags ftTags = null;
    private FTOutputTags ftOutTags = null;
    private FTMiddeBoxes mbes = null;
    private CoreRouter coreRouter = null;
    private HashMap<String, EdgeResponder> edgeSW = null;
    private CoreRouters coreRouters = null;
    public int swNum = 0;
    public int mbid = 0;
    public Map<String, PSIMBList> mb_map = new HashMap<String, PSIMBList>();

    // sw map
    public Map<String, PSISw> swmap = new HashMap<String, PSISw>();

    // current last sw in topo
    private int curLastSW = 1;

    public PSIDAGModifier(){
        // init map from mbtype to mb
        initMBMap("/testbed/opendaylight/toolkit/psi-odl-controller/psi_repo/policy/nflist");
    }

    public static PSIDAGModifier getInstance() {
        if (instance == null) {
            synchronized (PSIDAGModifier.class) {
                if (instance == null) {
                    instance = new PSIDAGModifier();
                }
            }
        }
        return instance;
    }

    public void initMBMap(String policyfile)
    {
        BufferedReader br = null;
        try
        {
            String sCurrentLine;
            FileReader freader = new FileReader(policyfile);
            br = new BufferedReader(freader);

            while ((sCurrentLine = br.readLine()) != null)
            {
                // log.info("PolicySet CurrentLine="+sCurrentLine);
                String[] splited = sCurrentLine.split("\\s+");
                // log.info("PolicySet Token="+splited[0]);
                String mbtype = splited[0];
                PSIMBList mblist = new PSIMBList(mbtype);

                // add all states
                for (int i=1;i<splited.length;i++){
                    mblist.addMB(splited[i]);
                }

                mb_map.put(mbtype, mblist);

            }
            freader.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void setCoreRouters(CoreRouters routers){
        log.info("setSwitch");
        coreRouters = routers;
    }

    public void setSocket(SocketChannel s){
        socket = s;
    }

    public boolean isIngress(String name){
        return name.startsWith("Ingress");
    }

    public boolean isEgress(String name){
        return name.startsWith("Egress");
    }

    public boolean isDrop(String name){
        return name.startsWith("Drop");
    }

    public void regDevices()
    {
    }

    public void regMiddleboxes(PSIFSM fsm)
    {
        // set middlebox
        for (PSINF nf: fsm.nfmap.values()){
            String nfName = nf.getName();
            log.info("initDAG: NF name "+nf.getName());
            boolean isIngress = PSIDAGModifier.getInstance().isIngress(nfName);
            boolean isEgress = PSIDAGModifier.getInstance().isEgress(nfName);
            boolean isDrop = PSIDAGModifier.getInstance().isDrop(nfName);

            if ( !isIngress && !isEgress && !isDrop)
            {
                this.mbid = this.mbid+1;
                nf.mbid = this.mbid; 
                int mbhostid = nf.mbhostid;
                PSIDAGModifier.getInstance().execCMD("flowtag_control mb add -mbId "+mbid+" -hostId "+mbhostid+" -type NON_CONSUME -address 10."+mbhostid+".0.1 -mask 255.255.0.0");
            }
        }
    }

    public void initLocalScope()
    {
        this.curLastSW = this.curLastSW + 1;
        String sw_in = SW_PREFIX+this.curLastSW;
        this.addSW( sw_in );

        this.curLastSW = this.curLastSW + 1;
        String sw_eg = SW_PREFIX+this.curLastSW;
        this.addSW( sw_eg );

        this.addLink(sw_in, sw_eg);
    }

    // add scopes other than local
    public void addScope(PSIScope scope)
    {
        String sw_in = SW_PREFIX+this.curLastSW;

        scope.in_sw = sw_in;

        this.curLastSW = this.curLastSW + 1;
        String sw_eg = SW_PREFIX+this.curLastSW;
        scope.e_sw = sw_eg;

        this.addSW( sw_eg );
        this.addLink(sw_in, sw_eg);
    }

    public void addScope(PSIScope scope, int device_num)
    {
        String sw_in;
        if (this.curLastSW == ( device_num + 1))
        {
            sw_in = SCOPE_SW;
        }
        else{
            sw_in = SW_PREFIX+this.curLastSW;
        }

        scope.in_sw = sw_in;

        this.curLastSW = this.curLastSW + 1;
        String sw_eg = SW_PREFIX+this.curLastSW;
        scope.e_sw = sw_eg;

        this.addSW( sw_eg );
        this.addLink(sw_in, sw_eg);

        log.info("addScope: "+sw_in+"-"+sw_eg+"-"+this.curLastSW+"-"+device_num);
    }

    // add scopes local
    public void addScope(PSIScope scope, String sw_in, String sw_eg)
    {
        scope.in_sw = sw_in;
        this.curLastSW = this.curLastSW + 1;
        scope.e_sw = sw_eg;
    }

    public void initFSMChain(PSIFSM fsm, String in_sw, String e_sw, int nexthops)
    {
        String scope = fsm.scope;
        for (PSINF nf: fsm.nfmap.values()){
            String nfName = nf.getName();
            log.info("initDAG: NF name "+nf.getName());

            // init sw for nf
            if (nfName.startsWith(INGRESS)){
                nf.sw = in_sw;
            }
            else if (nfName.startsWith(EGRESS)){
                nf.sw = e_sw;
            }
            else if (nfName.startsWith(DROP)){
            }
            else {
                if (nf.sw == null)
                {
                    // init sw
                    this.curLastSW = this.curLastSW + 1;
                    nf.sw = "s" + this.curLastSW;

                    addSW(nf.sw);
                    // this.curLastSW = this.curLastSW + 1;
                    // init vm
                    String ha = "";
                    String nf_type = nf.type;
                    log.info("initFSMChain"+nf.str);
                    log.info("initFSMChain"+nf_type);
                    PSIMBList mblist = mb_map.get(nf_type);
                    String mb_ha = mblist.getNextMB();
                    log.info("initFSMChain"+nf_type+"-"+mb_ha);
                    ha = this.addHost(mb_ha);
                    // init link between nf and ha
                    // remove not number
                    String mbhostid_str = mb_ha.replaceAll("[^\\d.]", "");
                    // store mbhostid
                    nf.mbhostid = Integer.parseInt(mbhostid_str);
                    addLink(ha, nf.sw);

                    // add mb port
                    PSISw sw_mb = swmap.get(nf.sw);
                    // sw_mb.curPort = sw_mb.curPort + 1;
                    sw_mb.mbport = "" + sw_mb.curPort; 
                    sw_mb.curPort = sw_mb.curPort + 1;
                }
            }
        }       

        // build the link based on service chain
        List<PSINF> NFList = new ArrayList<PSINF>(fsm.nfmap.values());
        for (int i = 0; i < NFList.size() - 1; i = i + 1){
            PSINF snf = NFList.get(i);
            PSINF dnf = NFList.get(i+1);
            log.info("nf name: "+snf.str+"-"+dnf.str);
            //addLink(snf.sw, dnf.sw);

            PSISw sw_snf = swmap.get(snf.sw);
            // sw_snf.curPort = sw_snf.curPort + 1;
            PSISw sw_dnf = swmap.get(dnf.sw);
            // sw_dnf.curPort = sw_dnf.curPort + 1;

            sw_snf.outports.put(""+sw_snf.curPort, ""+sw_snf.curPort);
            sw_dnf.inports.put(""+sw_dnf.curPort, ""+sw_dnf.curPort);
            addLink(snf.sw, dnf.sw);
        }
    }

    // init the DAGs for next n hops 
    public void initFSMDAGs(PSIFSM fsm, int nexthops)
    {
        String curState = fsm.getCurState();
        // fifo list to store next states
        LinkedList<String> nextStates = new LinkedList<String>();
        nextStates.add(curState);

        while ( (!nextStates.isEmpty()) && (nexthops>0) ){
            nexthops = nexthops - 1;
            curState = nextStates.removeFirst();

            // add all its neighbors
            for (PSIFSMEdge edge: fsm.getFSMEdges(curState)){
                String nextState = edge.getDState();
                nextStates.add(nextState);
            }

            // init the DAG for current state
            PSITrafficState trafficstate = fsm.getTrafficState(curState);
            String curDAG = trafficstate.getDAG();
            PSIDAG dag = fsm.getDAG(curDAG);
            log.info("dag: "+dag.getKey());
            String scope = fsm.scope;

            PSIDAGModifier.getInstance().initChainWithScope(trafficstate, scope);
        }
    }

    public void setChainForward(PSIFSM fsm)
    {
        for (PSINF nf: fsm.nfmap.values()){
            String nfName = nf.getName();
            log.info("initDAG: NF name "+nf.getName());

            String sw_str = nf.sw;
            PSISw sw = this.swmap.get(sw_str);

            PSITraffic traffic = fsm.getTraffic(0);
            String srcIP = traffic.pred.sip;
            String dstIP = traffic.pred.dip;
            log.info("flowtag_control: src "+srcIP);
            log.info("flowtag_control: dst "+dstIP);

            if (this.isIngress(nfName))
            {
            }
            else if (this.isEgress(nfName)){
            } 
            else if (this.isDrop(nfName)){
            }
            else {

                String mbport = sw.mbport;
                log.info("setChainForward: "+sw);
                // set inport to mbport
                for (String inport: sw.inports.values())
                {
                    // addForwardRule(String sw, String dstIP, String inport, String output, int piority)
                    log.info(""+sw_str + "-" + dstIP+ "-" + inport+ "-" + mbport);
                    this.addForwardRule(sw_str, dstIP, inport, mbport, 11);
                    this.addForwardRule(sw_str, srcIP, mbport, inport, 11);
                }

                // set mbport to outport 
                for (String outport: sw.outports.values())
                {
                    // addForwardRule(String sw, String dstIP, String inport, String output, int piority)
                    log.info(""+sw_str + "-" + dstIP+ "-" + outport+ "-" + mbport);
                    this.addForwardRule(sw_str, srcIP, outport, mbport, 11);
                    this.addForwardRule(sw_str, dstIP, mbport, outport, 11);
                }

            }
        }
    }

    public void setDAGForward(PSIFSM fsm, int nexthops)
    {
        String curState = fsm.getCurState();
        // fifo list to store next states
        LinkedList<String> nextStates = new LinkedList<String>();
        nextStates.add(curState);

        while ( (!nextStates.isEmpty()) && (nexthops>0) ){
            nexthops = nexthops - 1;
            curState = nextStates.removeFirst();

            // add all its neighbors
            for (PSIFSMEdge edge: fsm.getFSMEdges(curState)){
                String nextState = edge.getDState();
                nextStates.add(nextState);
            }

            // init the DAG for current state
            PSITrafficState trafficstate = fsm.getTrafficState(curState);
            for (String dag_str: trafficstate.daglist){

                PSIDAG dag = fsm.getDAG(dag_str);
                log.info("dag: "+dag.getKey());

                // assign tags and record ports
                /*
                for (PSINF nf: dag.getNFs())
                {
                    // find nf with degree > 2
                    int j = 0;

                    if ( (dag.getNeighbors(nf)).size() - j  > 1){
                        nf.intag = fsm.intag;
                        fsm.intag = fsm.intag + 1;
                    }

                    while ( (dag.getNeighbors(nf)).size() - j  > 1){
                        PSINF dnf = (dag.getNeighbors(nf)).get(j);
                        PSIDAGModifier.getInstance().execCMD("flowtag_control tag add -tag "+String.valueOf(fsm.intag)+" -srcIP "+srcIP+" -next 1");
                        String mbid_str = String.valueOf(nf.mbid);
                        String dmbid_str = String.valueOf(dnf.mbid);
                        dnf.intag = fsm.intag;
                        PSIDAGModifier.getInstance().execCMD("flowtag_control out add -mbId "+mbid_str+" -state 0 -preTag "+String.valueOf(nf.intag)+" -newTag "+String.valueOf(dnf.intag)+" -next "+dmbid_str);
                        fsm.intag = fsm.intag + 1;
                        j = j + 1;
                    }
                }
                */

            }            

        }
    }

    public void initChain(PSITrafficState state)
    {
        log.info("initDAG");

        // add all nodes
        for (PSINF nf: state.getNFs()){
            String nfName = nf.getName();
            log.info("initDAG: NF name "+nf.getName());
            if (isIngress(nfName)){
                nf.sw = "s1";
            }
            else if (isEgress(nfName)){
                nf.sw = "s2";
            }
            else{
                nf.sw = "s"+swNum;
                addSW(nf.sw);
                swNum = swNum + 1;
                String ha = "";

                String nf_type = nf.getName();
                // remove number
                nf_type = nf_type.replaceAll("\\d","");
                PSIMBList mblist = mb_map.get(nf_type);
                String mb_ha = mblist.getNextMB();

                ha = addHost(mb_ha);
                // remove not number
                String mbhostid_str = mb_ha.replaceAll("[^\\d.]", "");
                nf.mbhostid = Integer.parseInt(mbhostid_str);
                addLink(ha, nf.sw);
            }
        }

        // build the link based on service chain
        List<PSINF> NFList = state.getNFs();
        for (int i = 0; i < NFList.size() - 1; i = i + 1){
            PSINF snf = NFList.get(i);
            PSINF dnf = NFList.get(i+1);
            if ((snf.getName()).startsWith("HON")){
            }else{
                addLink(snf.sw, dnf.sw);
            }
        }

    }

    public void initChainWithScope(PSITrafficState state, String scope)
    {
        log.info("initDAG");

        // add all nodes
        for (PSINF nf: state.getNFs()){
            String nfName = nf.getName();
            log.info("initDAG: NF name "+nf.getName());
            if (isIngress(nfName)){
                if (scope.startsWith("Local"))
                {
                    nf.sw = "s1";
                }
                if (scope.startsWith("Global"))
                {
                    nf.sw = "s2";
                }
            }
            else if (isEgress(nfName)){
                if (scope.startsWith("Local"))
                {
                    nf.sw = "s2";
                }
                if (scope.startsWith("Global"))
                {
                    nf.sw = "s3";
                }
            }
            else{
                // if NF is not in fsm NF list
                if (nf.sw == null)
                {

                    nf.sw = "s"+swNum;
                    addSW(nf.sw);
                    swNum = swNum + 1;
                    String ha = "";

                    String nf_type = nf.getName();
                    // remove number
                    nf_type = nf_type.replaceAll("\\d","");
                    PSIMBList mblist = mb_map.get(nf_type);
                    String mb_ha = mblist.getNextMB();

                    ha = addHost(mb_ha);
                    // remove not number
                    String mbhostid_str = mb_ha.replaceAll("[^\\d.]", "");
                    nf.mbhostid = Integer.parseInt(mbhostid_str);
                    addLink(ha, nf.sw);
                }
            }
        }

        // build the link based on service chain
        List<PSINF> NFList = state.getNFs();
        for (int i = 0; i < NFList.size() - 1; i = i + 1){
            PSINF snf = NFList.get(i);
            PSINF dnf = NFList.get(i+1);
            if ((snf.getName()).startsWith("HON")){
            }else{
                addLink(snf.sw, dnf.sw);
            }
        }

    }

    public void handleTransition(PSITrans trans){
        String dagdif = trans.DAGdif;
        // log.info("PSIDAGModifier handleTransition PSITrans" + dagdif);
        this.swUpdate(dagdif);
    }

    public void handleTransition(PSIFSM fsm, PSITrans trans){
        String dagdif = trans.DAGdif;
        // log.info("PSIDAGModifier handleTransition PSITrans" + dagdif);
        this.setDAGForward(fsm, 2); 
    }

    public int getSwCurPort(String sw_str){
        PSISw sw = this.swmap.get(sw_str);
        return sw.curPort;
    }

    // return the current gw switch of the device
    public String addDevice(String device)
    {
        String ha = this.addHost(device);
        this.curLastSW = this.curLastSW + 1;
        String sw = "s"+this.curLastSW;
        log.info("PSIDAGModifier addDevice: " + device + "-" + ha + "-" + sw);
        this.addSW(sw);
        this.addLink(ha, sw);
        return sw;
    }

    public String addHost(String host) {
        String hostNum = host.replace("h", "");
        String hostAgentSW = addHostAgent(hostNum);
        // check how to map it to host4
        String hostDomain = "host" + hostNum;
        this.startVM(hostDomain);
        return hostAgentSW;
    }

    public boolean startVM(String hostDomain) {
        this.execCMD("virsh start " + hostDomain);
        return true;
    }

    public boolean addLink(String n1, String n2) {
        String vt1 = "vt-" + n1 + "-" + n2;
        String vt2 = "vt-" + n2 + "-" + n1;

        // increase current port
        PSISw sw_n1 = this.swmap.get(n1);
        sw_n1.curPort = sw_n1.curPort + 1;
        PSISw sw_n2 = this.swmap.get(n2);
        sw_n2.curPort = sw_n2.curPort + 1;

        sw_n1.fsm_outports.put(vt1, ""+sw_n1.curPort); 
        sw_n2.fsm_inports.put(vt2, ""+sw_n2.curPort); 

        this.execCMD("ip link add " + vt1 + " type veth peer name "+ vt2);

        // Add port to HA or to switch
        if (isHostAgent(n1))
        {
            addPortToSwitchWithPortNum(n1, vt1, 2);
        }

        if (isSW(n1))
        {
            addPortToSwitch(n1, vt1);
        }

        if (isHostAgent(n2))
        {
            addPortToSwitchWithPortNum(n2, vt2, 2);
        }

        if (isSW(n2))
        {
            addPortToSwitch(n2, vt2);
        }

        // Promisc UP
        this.execCMD("ifconfig " + vt1 + " -broadcast -arp promisc up");
        this.execCMD("ifconfig " + vt2 + " -broadcast -arp promisc up");

        return true;
    }

    public boolean addPortToSwitch(String sw, String port) {
        this.execCMD("ovs-vsctl add-port "+ sw +" "+ port);
        return true;
    }

    public boolean addPortToSwitchWithPortNum(String sw, String port, int portNum) {
        this.execCMD("ovs-vsctl add-port "+ sw +" "+ port + " -- set interface "+ sw + " ofport="+String.valueOf(portNum));
        return true;
    }


    public String addHostAgent(String hostNum) {
        String hostAgentSW = "ha" + hostNum;
        this.addSW(hostAgentSW);
        return hostAgentSW;
    }

    public boolean addSW(String sw){
        log.info("Start SW " + sw);

        // add sw to swmap
        if (this.swmap.get(sw) == null){
            PSISw sw_new = new PSISw(sw);
            this.swmap.put(sw, sw_new);
        }

        // add switch 
        this.execCMD("ovs-vsctl add-br "+ sw); 

        // ifup interface
        this.ifUP(sw);

        // config dpid 
        this.setDPID(sw, this.createDPID(sw));
        this.setController(sw);
        this.setFailureMode(sw);

        return true;
    }

    public boolean setDPID(String sw, String dpid) {
        this.execCMD("ovs-vsctl set bridge "+sw+" other-config:datapath-id="+dpid);
        return true;
    }

    public boolean setController(String sw) {
        this.execCMD("ovs-vsctl set-controller "+ sw + " tcp:"+"192.168.123.201:6633");
        return true;
    }

    public boolean setFailureMode(String sw) {
        this.execCMD("ovs-vsctl set-fail-mode "+ sw +" secure");
        return true;
    }

    public boolean isHostAgent(String node) {
        return node.startsWith("ha");
    }

    public boolean isSW(String node) {
        return node.startsWith("s");
    }

    public String createDPID(String node) {
        String nodeNum; 
        long num = 0;
        // var err error
        String dpid = "";
        log.info("createDPID: " + node);

        if (isHostAgent(node)){
            nodeNum = node.replace("ha", "");
            num = Long.parseLong(nodeNum);
        }
        if (isSW(node)){
            nodeNum = node.replace("s", "");
            num = Long.parseLong(nodeNum);
            num += 0x10000;
        }

        if ((0 <= num) && (num < 0x100)){
            dpid = String.format("00000000000000%02x", num);
        }

        if ((0x100 <= num) && (num < 0x10000)){
            dpid = String.format("000000000000%02x%02x", (num&0xff00)>>8, num&0xff);
        }

        if ((0x10000 <= num) && (num < 0x1000000)){
            dpid = String.format("0000000000%02x%02x%02x", (num&0xff0000)>>16, (num&0xff00)>>8, num&0xff);
        }

        return dpid;
    }

    public boolean ifUP(String ifName){
        this.execCMD("ifconfig "+ ifName +" 1.1.1.1 netmask 255.255.255.0");
        this.execCMD("ifconfig "+ ifName +" 0.0.0.0");
        return true;
    }

    /*
       public boolean swUpdate(PSIPredicates pred, String swDPID, String inport, String outport, Int pior){
// this.switchMod(swDPID, pred.getIP(), inport, pred.getMAC(),outport, pior);
return true;
       }
       */


    // bootStrap 
    public boolean swBootStrap(String nodeId){
        return true;
    }

    public boolean addForwardRule(String sw, String dstIP, String inport, String outport, int piority){
        log.info("addForwardRule srcIP: "+sw+"-"+dstIP+"-"+inport+"-"+outport+"-"+piority);
        String swid = sw.replace("s","");
        String dmac = (dstIP.split("\\."))[1];
        this.switchMod("00:00:00:00:00:01:00:0"+swid, dstIP, inport, "00:00:00:00:00:0"+dmac, outport, piority);
        return true;
    }

    public boolean addForwardRuleByTraffic(String sw, String srcIP, String dstIP, String outport, int piority){
        String swid = sw.replace("s","");
        log.info("addForwardRuleByTraffic: "+sw+"-"+srcIP+"-"+dstIP+"-"+outport+"-"+piority);
        this.switchModByTraffic("00:00:00:00:00:01:00:0"+swid, srcIP, dstIP, outport, piority);
        return true;
    }

    public boolean addForwardSwIn(String sw, String srcIP, String dstIP, String outport, int piority){
        PSISw sw_var = this.swmap.get(sw);
        for (String inport: sw_var.fsm_inports.values()){
            String swid = sw.replace("s","");
            log.info("addForwardSwIn: "+sw+"-"+srcIP+"-"+dstIP+"-"+inport+"-"+outport+"-"+piority);
            this.switchModByTraffic("00:00:00:00:00:01:00:0"+swid, srcIP, dstIP, inport, outport, piority);
        }
        
        // no fsm fix
        if (sw.equals("s1"))
        {
            String swid = sw.replace("s","");
            this.switchModByTraffic("00:00:00:00:00:01:00:0"+swid, dstIP, srcIP, "5", "1", piority);
            this.switchModByTraffic("00:00:00:00:00:01:00:0"+swid, "10.2.0.1", "10.5.0.1", "5", "1", piority);
        }

        return true;
    }

    public boolean addForwardSwEg(String sw, String srcIP, String dstIP, String outport, int piority){
        PSISw sw_var = this.swmap.get(sw);
        for (String inport: sw_var.fsm_outports.values()){
            String swid = sw.replace("s","");
            log.info("addForwardSwIn: "+sw+"-"+srcIP+"-"+dstIP+"-"+inport+"-"+outport+"-"+piority);
            this.switchModByTraffic("00:00:00:00:00:01:00:0"+swid, srcIP, dstIP, inport, outport, piority);
        }

        // no fsm fix
        if (sw.equals("s6"))
        {
            String swid = sw.replace("s","");
            this.switchModByTraffic("00:00:00:00:00:01:00:0"+swid, srcIP, dstIP, "1", "2", piority);
            this.switchModByTraffic("00:00:00:00:00:01:00:0"+swid, "10.2.0.1", "10.5.0.1", "1", "2", piority);
        }

        return true;
    }

    public boolean addForwardRuleByDstHost(String sw, int dstHost, String srcIP, String dstIP, String inport, int piority){
        String swid = sw.replace("s","");
        log.info("addForwardRuleByTraffic: "+sw+"-"+srcIP+"-"+dstIP+"-"+inport+"-"+piority);
        this.switchModByDstHost("00:00:00:00:00:01:00:0"+swid, dstHost, srcIP, dstIP, inport, piority);
        return true;
    }

    public boolean inlineSW(String sw, String srcIP, String dstIP){
        String swid = sw.replace("s","");
        log.info("inlineSW srcIP: "+srcIP);
        String smac = (srcIP.split("\\."))[1];
        String dmac = (dstIP.split("\\."))[1];
        this.switchMod("00:00:00:00:00:01:00:0"+swid, dstIP, "2", "00:00:00:00:00:0"+dmac,"1", 11);
        this.switchMod("00:00:00:00:00:01:00:0"+swid, dstIP, "1", "00:00:00:00:00:0"+dmac,"3", 11);
        this.switchMod("00:00:00:00:00:01:00:0"+swid, srcIP, "3", "00:00:00:00:00:0"+smac,"1", 11);
        this.switchMod("00:00:00:00:00:01:00:0"+swid, srcIP, "1", "00:00:00:00:00:0"+smac,"2", 11);
        return true;
    }

    public boolean inlineSW(String sw){
        String swid = sw.replace("s","");
        this.switchMod("00:00:00:00:00:01:00:0"+swid, "10.2.0.1", "2", "00:00:00:00:00:02","1", 11);
        this.switchMod("00:00:00:00:00:01:00:0"+swid, "10.2.0.1", "1", "00:00:00:00:00:02","3", 11);
        this.switchMod("00:00:00:00:00:01:00:0"+swid, "10.1.0.1", "3", "00:00:00:00:00:01","1", 11);
        this.switchMod("00:00:00:00:00:01:00:0"+swid, "10.1.0.1", "1", "00:00:00:00:00:01","2", 11);
        return true;
    }

    public boolean endSW(String sw){
        String swid = sw.replace("s","");
        this.switchMod("00:00:00:00:00:01:00:0"+swid, "10.2.0.1", "2", "00:00:00:00:00:02","1", 11);
        this.switchMod("00:00:00:00:00:01:00:0"+swid, "10.2.0.1", "1", "00:00:00:00:00:02","1", 11);
        return true;
    }

    public void setupSW(){
        this.addForwardRule("s1", "10.2.0.1","1","3",100);
        this.addForwardRule("s2", "10.1.0.1","3","1",100);
    }

    public boolean swUpdate(String dagdif){
        return true;
    }

    public boolean switchMod(String swID, String dstIP, String inport, String dstMac, String outport, int prior ){
        CoreRouter router = this.coreRouters.getRouter(swID);
        return router.addFlow(dstIP, "255.255.255.0", inport, dstMac, outport, (short) prior);
    }

    public boolean switchModByTraffic(String swID, String srcIP, String dstIP, String outport, int prior ){
        CoreRouter router = this.coreRouters.getRouter(swID);
        return router.addFlowByTraffic(srcIP, dstIP, "255.255.255.0", outport, (short) prior);
    }

    public boolean switchModByTraffic(String swID, String srcIP, String dstIP, String inport, String outport, int prior ){
        CoreRouter router = this.coreRouters.getRouter(swID);
        return router.addFlowByTraffic(srcIP, dstIP, "255.255.255.0", inport, outport, (short) prior);
    }

    //initailRuleByDstHost(int dstHost, String src, String dst, String mask, short priority)
    public boolean switchModByDstHost(String swID, int dstHost, String srcIP, String dstIP, String inport, int prior ){
        CoreRouter router = this.coreRouters.getRouter(swID);
        return router.addFlowByDstHost(dstHost, srcIP, dstIP, "255.255.255.0", inport, (short) prior);
    }

    public long trans_num = 0;
    public long s_time = 0;
    public long c_time = 0;
    public void measureThroughput(){
        /*
         * Measurement
         * */
        trans_num += 1;
        if (trans_num == 1) s_time = System.currentTimeMillis();
        c_time = System.currentTimeMillis();
        if (trans_num > 1)
        {
            // log.info("Logger name = " + log.getName());
            if ((trans_num%50000)==1)
            {
                //log.info("Trans rate ="+(((float)trans_num)*1000/(c_time-s_time)));
                System.out.print("Trans rate ="+(((float)trans_num)*1000/(c_time-s_time)));
                //System.out.print("\n");
            }
        }
    }

    public void cBench(){

        try{
            String s = "psipongmsg";
            ByteBuffer msgbuffer = ByteBuffer.allocate(48);
            msgbuffer.clear(); 
            msgbuffer.put(s.getBytes());
            msgbuffer.flip();
            while (msgbuffer.hasRemaining())
            {
                socket.write(msgbuffer);
            }
        }
        catch (IOException e)
        {
            log.info("IO Error");
        }
    }

    public void execCMD(String s)
    {
        log.info("execCMD: "+s);
        Process p;
        try {
            p = Runtime.getRuntime().exec(s);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null)
                log.info("cmd: " + s);
            p.waitFor();
            log.info("exit:" + p.exitValue());
            p.destroy();
        } catch (Exception e) {}
    }

    public void execFile(String path)
    {

        BufferedReader br = null;
        try
        {
            String sCurrentLine;
            FileReader freader = new FileReader(path);
            br = new BufferedReader(freader);

            // skip the first line
            sCurrentLine = br.readLine();
            while ((sCurrentLine = br.readLine()) != null)
            {
                sCurrentLine.replace("\n", "");
                log.info("ExecFile CurrentLine= "+sCurrentLine);
                this.execCMD(sCurrentLine);
            }
            freader.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}
