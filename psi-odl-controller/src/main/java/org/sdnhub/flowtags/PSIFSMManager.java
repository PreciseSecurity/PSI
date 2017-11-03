package org.sdnhub.flowtags;

import java.util.HashMap;
import java.util.Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.io.InputStreamReader;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class PSIFSMManager {

    // constants
    public static final String HOST_MB_PREFIX = "10";
    public static final String CY_TYPE_DEVICE = "Device";
    public static final String CY_TYPE_TRAFFIC = "Traffic";
    public static final String CY_TYPE_FSM = "FSM";
    public static final String CY_TYPE_DAG = "dag";
    public static final String CY_TYPE_STATE = "State";
    public static final String CY_TYPE_NF = "NF";
    public static final String CY_TYPE_FSMEDGE = "FSMEdge";
    public static final String CY_TYPE_DAGEDGE = "DAGEdge";
    public static final String SCOPE_SW = "s1";
    public static final int PIOR = 20;
    public String policyfilepath = "/testbed/opendaylight/toolkit/psi-odl-controller/psi_repo/policy/cy/";
    public int policyNum = 2;
    // number of prefetch hops
    public int prefetchHops = 1;

    private static volatile PSIFSMManager instance;
    protected static final Logger log = LoggerFactory.getLogger(PSIFSMManager.class); 
    static StringBuilder sb = new StringBuilder();

    // traffic map
    private Map<String, PSITraffic> trafficmap = new HashMap<String, PSITraffic>();
    // event map 
    private Map<String, PSIEvent> eventmap = new HashMap<String, PSIEvent>();
    // fsm map 
    private Map<String, PSIFSM> fsmmap = new HashMap<String, PSIFSM>();
    // event - fsm map: an event can relate to multiple traffic
    private Map<String, List<String>> e_f_map = new HashMap<String, List<String>>();
    // traffic fsm map
    private Map<String, String> t_f_map = new HashMap<String, String>();
    // json policy elems map
    private Map<String, PSICyElements> cy_elems_map = new HashMap<String,PSICyElements>();
    // json device elems map
    private PSICyElements cy_devices = new PSICyElements();
    // scope list
    public Map<String, PSIScope> scopemap = new HashMap<String, PSIScope>();
    // device map
    public Map<String, PSIDevice> devicemap = new HashMap<String, PSIDevice>();

    public PSIFSMManager()
    {
    }

    static {
        instance = new PSIFSMManager();
    }

    public static PSIFSMManager getInstance() {
        return instance;
    }

    public void initFSM()
    {
        log.info("PSIFSMManager");

        // read devices json
        String devices_str = policyfilepath+"devices";
        this.cy_devices = readJson(devices_str);
        this.loadDevices();

        // read policies json
        for (int i=1;i<policyNum+1;i++)
        {
            String s = policyfilepath+"policy"+i;
            log.info("load policy: "+s);
            PSICyElements elems = readJson(s);
            this.cy_elems_map.put("Policy"+i, elems);
            this.loadJson(i);
        }

        log.info("initFSM: fsmmap "+this.fsmmap.size());
        log.info("initFSM: scopemap "+this.scopemap.size());

        // PSIBenchmark.getInstance().PSIMemory();
        this.initPSITopology();
    }

    public void initTopology()
    {
        // Init the sw and VM for every FSM
        PSIDAGModifier.getInstance().addSW("s2");
        log.info("fsmmap size = "+fsmmap.size());
        int dag_port = 2;
        for (int i=1; i<(fsmmap.size()+1); i++){
            // get the traffic class
            PSIFSM fsm = fsmmap.get("FSM"+String.valueOf(i));
            log.info("fsm: "+fsm.getKey());

            PSITrafficState trafficstate = fsm.getTrafficState(fsm.getCurState());
            PSIDAG dag = fsm.getDAG(trafficstate.getDAG());
            log.info("dag: "+dag.getKey());
            String scope = fsm.scope;

            PSIDAGModifier.getInstance().initChainWithScope(trafficstate, scope);
            trafficstate.s1_port = dag_port;
            dag_port = dag_port + 1;

            // 1-hop prefetching
            log.info("before prefetch: "+fsm.getKey());
            for (PSIFSMEdge edge: fsm.getFSMEdges(fsm.getCurState())){
                PSITrafficState nextstate = fsm.getTrafficState(edge.getDState());
                PSIDAG nextdag = fsm.getDAG(nextstate.getDAG());
                log.info("nextdag: "+nextdag.getKey());

                PSIDAGModifier.getInstance().initChainWithScope(nextstate, scope);
                nextstate.s1_port = dag_port;
                dag_port = dag_port + 1;
            }
            log.info("after prefetch: "+fsm.getKey());
        }
    }

    // init sw, links, vms for PSI
    // assume s1 is launched as gateway
    public void initPSITopology()
    {
        // add switch between local and other scope
        PSIDAGModifier.getInstance().addSW(SCOPE_SW);

        // add devices
        for (PSIDevice device: this.devicemap.values()){
            String device_name = device.name;
            String gw_sw = PSIDAGModifier.getInstance().addDevice( device_name );
            device.gateway = gw_sw;
        }

        // add scopes
        log.info("initPSITopology() this.scopemap.size()"+this.scopemap.size());
        for (PSIScope scope: this.scopemap.values()){
            if (scope.name.equals("local")){
                for (PSIDevice device: this.devicemap.values()){
                    String gw_sw = device.gateway;
                    PSIDAGModifier.getInstance().addLink(gw_sw, SCOPE_SW);
                }
            }
            else{
                PSIDAGModifier.getInstance().addScope(scope, devicemap.size());
            }
        }

        // init SWs, links, vms of DAGs for each FSM
        log.info("initPSITopology() this.fsm.size()"+this.fsmmap.size());
        for (int i=1; i<(this.fsmmap.size()+1); i++){
            PSIFSM fsm = fsmmap.get("FSM"+String.valueOf(i));
            log.info("fsm: "+fsm.getKey());

            if (fsm.scope.equals("local"))
            {
                String device_str = fsm.device;
                PSIDevice device = this.devicemap.get(device_str);
                String in_sw = device.gateway;
                String e_sw = SCOPE_SW;
                PSIDAGModifier.getInstance().initFSMChain(fsm, in_sw, e_sw, this.prefetchHops);

                fsm.sw_in = in_sw;
                fsm.port_in = PSIDAGModifier.getInstance().getSwCurPort(in_sw);
                fsm.sw_eg = e_sw;
                fsm.port_eg = PSIDAGModifier.getInstance().getSwCurPort(e_sw);

                log.info("initPSITopo: "+fsm.key+"-"+fsm.sw_in+"-"+fsm.sw_eg+"-"+fsm.port_in+"-"+fsm.port_eg);
            }
            else{
                PSIScope scope_ins = this.scopemap.get(fsm.scope);
                String in_sw = scope_ins.in_sw;
                String e_sw = scope_ins.e_sw;
                PSIDAGModifier.getInstance().initFSMChain(fsm, in_sw, e_sw, this.prefetchHops);

                fsm.sw_in = in_sw;
                fsm.port_in = PSIDAGModifier.getInstance().getSwCurPort(in_sw);
                fsm.sw_eg = e_sw;
                fsm.port_eg = PSIDAGModifier.getInstance().getSwCurPort(e_sw);

                log.info("initPSITopo: "+fsm.key+"-"+fsm.sw_in+"-"+fsm.sw_eg+"-"+fsm.port_in+"-"+fsm.port_eg);
            }

        }

    }

    public void initPSIForward(){
        // install IP forwarding
        PSIDAGModifier.getInstance().execCMD("sudo python /testbed/bin/topo_get.py");
        PSIDAGModifier.getInstance().execCMD("sh /testbed/bin/fw.sh");

        // clear tag forwarding
        PSIDAGModifier.getInstance().execCMD("flowtag_control mb clear");

        // reg devices
        for (PSIDevice device: this.devicemap.values()){
            String ip_str = device.ip;
            log.info("flowtag_control: src "+ip_str);

            String hostid = (ip_str.split("\\."))[1];
            String hostmbid = HOST_MB_PREFIX + hostid;
            PSIDAGModifier.getInstance().execCMD("flowtag_control mb add -mbId "+hostmbid+" -hostId "+hostid+" -type CONSUME -address "+ip_str+" -mask 255.255.0.0");
        }

        // reg middleboxes
        for (int i=1; i<fsmmap.size()+1; i++){
            PSIFSM fsm = fsmmap.get("FSM"+String.valueOf(i));
            PSIDAGModifier.getInstance().regMiddleboxes(fsm);
        }

        // init forwarding at ingress and egress of each scope
        for (int i=1; i<fsmmap.size()+1; i++){
            PSIFSM fsm = fsmmap.get("FSM"+String.valueOf(i));
            PSITraffic traffic = fsm.getTraffic(0);

            String sw_in = fsm.sw_in; 
            String port_in = ""+fsm.port_in; 
            String sw_eg = fsm.sw_eg; 
            String port_eg = ""+fsm.port_eg; 

            // set forwarding
            String srcIP = traffic.pred.sip;
            String dstIP = traffic.pred.dip;

            log.info("initPSIForward: "+ "FSM"+ i + "-" +sw_in + "-" + sw_eg + "-" + srcIP + "-" + dstIP + "-" + port_in);

            // traffic sw_in port_in
            // String sw, String srcIP, String dstIP, String outport, int piority
            // PSIDAGModifier.getInstance().addForwardRuleByTraffic(sw_in, srcIP, dstIP, port_in, 20);
            // rtraffic sw_eg port_eg
            // PSIDAGModifier.getInstance().addForwardRuleByTraffic(sw_eg, dstIP, srcIP, port_eg, 20);
            // addForwardRuleByDstHost(String sw, String srcIP, String dstIP, String inport, int piority)

            // sw_in
            PSIDAGModifier.getInstance().addForwardSwIn(sw_in, srcIP, dstIP, port_in, 20);
            // sw_eg
            PSIDAGModifier.getInstance().addForwardSwEg(sw_eg, dstIP, srcIP, port_eg, 20);
            // set 

        }

        // install chain forwarding
        for (int i=1; i<fsmmap.size()+1; i++){
            PSIFSM fsm = fsmmap.get("FSM"+String.valueOf(i));
            PSIDAGModifier.getInstance().setChainForward(fsm);
        }

        // build forwarding based on dag
        /*
        for (PSINF nf: dag.getNFs()){
            String nfName = nf.getName();
            log.info("initDAG: NF name "+nf.getName());
            if (PSIDAGModifier.getInstance().isIngress(nfName)){
            }
            else if (PSIDAGModifier.getInstance().isEgress(nfName)){
            }
            else{
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
        }

        // build forwarding based on rdag
        for (PSINF nf: rdag.getNFs()){
            String nfName = nf.getName();
            log.info("initDAG: NF name "+nf.getName());
            if (PSIDAGModifier.getInstance().isIngress(nfName)){
            }
            else if (PSIDAGModifier.getInstance().isEgress(nfName)){
            }
            else{
                // find nf with degree > 2
                int j = 0;
                while ( (rdag.getNeighbors(nf)).size() - j  > 1){
                    PSINF dnf = (rdag.getNeighbors(nf)).get(j);
                    PSIDAGModifier.getInstance().execCMD("flowtag_control tag add -tag "+String.valueOf(fsm.intag)+" -srcIP "+srcIP+" -next 1");
                    String mbid_str = String.valueOf(nf.mbid);
                    String dmbid_str = String.valueOf(dnf.mbid);
                    dnf.intag = fsm.intag;
                    PSIDAGModifier.getInstance().execCMD("flowtag_control out add -mbId "+mbid_str+" -state 0 -preTag "+String.valueOf(nf.intag)+" -newTag "+String.valueOf(dnf.intag)+" -next "+dmbid_str);
                    fsm.intag = fsm.intag + 1;
                    j = j + 1;
                }

            }
        }
        */

    }

    public void initForward(){
        // set basic forwarding
        PSIDAGModifier.getInstance().execCMD("sudo python /testbed/bin/topo_get.py");
        PSIDAGModifier.getInstance().execCMD("sh /testbed/bin/fw.sh");

        // set forward rules for s1, s2 and s3
        PSIDAGModifier.getInstance().setupSW();

        // clear previous tags 
        PSIDAGModifier.getInstance().execCMD("flowtag_control mb clear");

        // set host
        int hostmbid = 101;
        // int hostid = 1;
        String hostid = "1";
        int mbid = 1;
        for (int i=1; i<fsmmap.size()+1; i++){
            // traffic class
            // set host
            PSIFSM fsm = fsmmap.get("FSM"+String.valueOf(i));
            PSITraffic traffic = fsm.getTraffic(0);
            String srcIP = traffic.src.getIP();
            String dstIP = traffic.dst.getIP();
            log.info("flowtag_control: src "+srcIP);
            log.info("flowtag_control: dst "+dstIP);

            if (!(srcIP.startsWith("any")))
            {
                // add src host
                hostid = (srcIP.split("\\."))[1];
                PSIDAGModifier.getInstance().execCMD("flowtag_control mb add -mbId "+String.valueOf(hostmbid)+" -hostId "+hostid+" -type CONSUME -address "+srcIP+" -mask 255.255.0.0");
                hostmbid = hostmbid + 1;

                // add dst host
                hostid = (dstIP.split("\\."))[1];
                PSIDAGModifier.getInstance().execCMD("flowtag_control mb add -mbId "+String.valueOf(hostmbid)+" -hostId "+hostid+" -type CONSUME -address "+dstIP+" -mask 255.255.0.0");
                hostmbid = hostmbid + 1;
            }
        }

        for (int i=2; i<fsmmap.size()+1; i++){
            // traffic class
            // set host
            PSIFSM fsm = fsmmap.get("FSM"+String.valueOf(i));
            PSITraffic traffic = fsm.getTraffic(0);
            String srcIP = traffic.src.getIP();
            String dstIP = traffic.dst.getIP();

            // set middlebox
            PSITrafficState trafficstate = fsm.getTrafficState(fsm.getCurState());
            PSIDAG dag = fsm.getDAG(trafficstate.getDAG());
            PSIDAG rdag = fsm.getDAG(trafficstate.getRDAG());
            log.info("dag: "+dag.getKey());

            for (PSINF nf: trafficstate.getNFs()){
                String nfName = nf.getName();
                log.info("initDAG: NF name "+nf.getName());
                if (PSIDAGModifier.getInstance().isIngress(nfName)){
                }
                else if (PSIDAGModifier.getInstance().isEgress(nfName)){
                }
                else{
                    nf.mbid = mbid;
                    int mbhostid = nf.mbhostid;
                    PSIDAGModifier.getInstance().execCMD("flowtag_control mb add -mbId "+mbid+" -hostId "+mbhostid+" -type NON_CONSUME -address 10."+mbhostid+".0.1 -mask 255.255.0.0");
                    mbid = mbid + 1;
                    if ((nf.getName()).startsWith("HON")){
                        PSIDAGModifier.getInstance().endSW(nf.sw);
                    }
                    else{
                        if (srcIP.startsWith("any"))
                        {
                            for (int j=1; j<fsmmap.size()+1; j++){
                                // traffic class
                                // set host
                                PSIFSM fsm_j = fsmmap.get("FSM"+String.valueOf(j));
                                PSITraffic traffic_j = fsm_j.getTraffic(0);
                                String srcIP_j = traffic_j.src.getIP();
                                String dstIP_j = traffic_j.dst.getIP();

                                if (!(srcIP_j.startsWith("any"))){
                                    PSIDAGModifier.getInstance().inlineSW(nf.sw, srcIP_j, dstIP_j);
                                }
                            }
                        } 
                        else{
                            PSIDAGModifier.getInstance().inlineSW(nf.sw, srcIP, dstIP);
                        }
                    }
                }
            }

            // install prefetch forwarding
            for (PSIFSMEdge edge: fsm.getFSMEdges(fsm.getCurState())){
                PSITrafficState nextstate = fsm.getTrafficState(edge.getDState());
                PSIDAG nextdag = fsm.getDAG(nextstate.getDAG());
                log.info("nextdag: "+nextdag.getKey());

                // install prefeched dags
                for (PSINF nf: nextstate.getNFs()){
                    String nfName = nf.getName();
                    log.info("initDAG: NF name "+nf.getName());
                    if (PSIDAGModifier.getInstance().isIngress(nfName)){
                    }
                    else if (PSIDAGModifier.getInstance().isEgress(nfName)){
                    }
                    else {
                        nf.mbid = mbid;
                        int mbhostid = nf.mbhostid;
                        PSIDAGModifier.getInstance().execCMD("flowtag_control mb add -mbId "+mbid+" -hostId "+mbhostid+" -type NON_CONSUME -address 10."+mbhostid+".0.1 -mask 255.255.0.0");
                        mbid = mbid + 1;
                        if ((nf.getName()).startsWith("HON")){
                            PSIDAGModifier.getInstance().endSW(nf.sw);
                        } else{
                            if (srcIP.startsWith("any"))
                            {
                                for (int j=1; j<fsmmap.size()+1; j++){
                                    // traffic class
                                    // set host
                                    PSIFSM fsm_j = fsmmap.get("FSM"+String.valueOf(j));
                                    PSITraffic traffic_j = fsm_j.getTraffic(0);
                                    String srcIP_j = traffic_j.src.getIP();
                                    String dstIP_j = traffic_j.dst.getIP();

                                    if (!(srcIP_j.startsWith("any"))){
                                        PSIDAGModifier.getInstance().inlineSW(nf.sw, srcIP_j, dstIP_j);
                                    }
                                }

                            }
                            else{
                                PSIDAGModifier.getInstance().inlineSW(nf.sw, srcIP, dstIP);
                            }
                        }
                    }
                }
            }

            // build forwarding based on dag
            for (PSINF nf: dag.getNFs()){
                String nfName = nf.getName();
                log.info("initDAG: NF name "+nf.getName());
                if (PSIDAGModifier.getInstance().isIngress(nfName)){
                }
                else if (PSIDAGModifier.getInstance().isEgress(nfName)){
                }
                else{
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
            }

            // build forwarding based on rdag
            for (PSINF nf: rdag.getNFs()){
                String nfName = nf.getName();
                log.info("initDAG: NF name "+nf.getName());
                if (PSIDAGModifier.getInstance().isIngress(nfName)){
                }
                else if (PSIDAGModifier.getInstance().isEgress(nfName)){
                }
                else{
                    // find nf with degree > 2
                    int j = 0;
                    while ( (rdag.getNeighbors(nf)).size() - j  > 1){
                        PSINF dnf = (rdag.getNeighbors(nf)).get(j);
                        PSIDAGModifier.getInstance().execCMD("flowtag_control tag add -tag "+String.valueOf(fsm.intag)+" -srcIP "+srcIP+" -next 1");
                        String mbid_str = String.valueOf(nf.mbid);
                        String dmbid_str = String.valueOf(dnf.mbid);
                        dnf.intag = fsm.intag;
                        PSIDAGModifier.getInstance().execCMD("flowtag_control out add -mbId "+mbid_str+" -state 0 -preTag "+String.valueOf(nf.intag)+" -newTag "+String.valueOf(dnf.intag)+" -next "+dmbid_str);
                        fsm.intag = fsm.intag + 1;
                        j = j + 1;
                    }

                }
            }
        }
    }

    public void handleEvent(PSIEvent event){
        log.info("handleEvent(PSIEvent event)"+event.getKey()+" "+e_f_map.get(event.getKey()).get(0));
        /*
         * map event to fsm
         * */
        for (String fsmkey : e_f_map.get(event.getKey())){
            PSIFSM fsm = fsmmap.get(fsmkey);
            // update current state in FSM
            PSITrans trans = fsm.handleEvent(event);

            // update switches
            // PSIDAGModifier.getInstance().handleTransition(fsm, trans);
        }

    }

    private void loadDevices()
    {
        log.info("loadDevices");
        log.info("loadDevices: "+ this.cy_devices.getNodes().length);
        for (PSICyNode node: this.cy_devices.getNodes()){
            String device_name = node.data.name;
            log.info("loadDevices: "+device_name);
            PSIDevice device = new PSIDevice(device_name);
            device.ip = node.data.ip_address;
            this.devicemap.put(device_name, device); 
        }
    }

    private void loadJson(int policy_id)
    {
        log.info("loadJson");
        //for (String elems_key: cy_elems_map.keySet()){
        String elems_key = "Policy"+policy_id;
        PSICyElements elems = cy_elems_map.get(elems_key);

        // create a fsm for each elems
        PSIFSM fsm = new PSIFSM();
        String fsmkey = CY_TYPE_FSM + policy_id;
        fsm.setKey(fsmkey);
        fsmmap.put(fsmkey, fsm);

        log.info("loadJson fsmkey+size "+fsmkey+"-"+fsmmap.size());

        List<String> e_f_List = new ArrayList<String>();

        // nodes
        log.info("loadJson getNodes");
        for (PSICyNode node: elems.getNodes()){
            // subject: device + traffic
            if (node.data.type.equals(CY_TYPE_DEVICE)){
                fsm.device = node.data.name;
            }
            if (node.data.type.equals(CY_TYPE_TRAFFIC)){
                log.info("loadJson CY_TYPE_TRAFFIC");

                // generate traffic key, e.g., Traffic1
                String traffickey = CY_TYPE_TRAFFIC + policy_id;
                String[] preds = node.data.predicate.split("\\s+");

                if (preds.length < 5)
                    log.info("predicate format error!");

                // format: sip, sport, dip, dport, proto
                PSIPredicates pred = new PSIPredicates(preds[0], preds[1], preds[2], preds[3], preds[4]);
                // traffic contains one pred 
                PSITraffic traffic = new PSITraffic(traffickey, pred);

                fsm.addTraffic(traffic);
                trafficmap.put(traffickey, traffic);
            }

            // fsm
            if (node.data.type.equals(CY_TYPE_STATE)){
                log.info("loadJson CY_TYPE_STATE");

                String state_name = node.data.shared_name;
                // State0 is the inital state
                if (state_name.equals(CY_TYPE_STATE+0)){
                    fsm.setCurState(state_name);
                }

                // add the scope of state to fsm
                String scope = node.data.scope;
                fsm.scope = scope;
                if (this.scopemap.get(scope) == null )
                {
                    PSIScope scope_ins = new PSIScope(scope);
                    this.scopemap.put(scope, scope_ins);
                    log.info("loadJson: new scope - "+scope);
                }

                // all the dags that belongs to this state
                String[] dags = node.data.DAGs.split("\\s+");
                PSITrafficState state = new PSITrafficState(state_name);
                fsm.addState(state);

                for (String dag: dags)
                {
                    state.daglist.add(dag);
                    fsm.setDAGToState(dag, state_name);
                }

            }

            // dag
            if (node.data.type.equals(CY_TYPE_NF)){
                log.info("loadJson CY_TYPE_NF");
                String nf_name = node.data.shared_name;
                String nf_config = node.data.state_to_context;
                String nf_dag_name = node.data.set;

                // check if NF exist
                PSINF nf = fsm.nfmap.get(nf_name);
                if (nf == null){
                    nf = new PSINF(nf_name, nf_config);
                    nf.type = node.data.nf_type;
                    fsm.nfmap.put(nf_name, nf);
                }

                // check if DAG exist
                PSIDAG dag = fsm.dagmap.get(nf_dag_name);
                if (dag == null){
                    dag = new PSIDAG(nf_dag_name);
                    // add dag to dagmap
                    fsm.dagmap.put(nf_dag_name, dag);
                }

                // add nf to dag
                dag.add(nf);
            }

        }

        // edges
        log.info("loadJson getEdges");
        for (PSICyEdge edge: elems.getEdges()){
            // fsm
            if (edge.data.type.equals(CY_TYPE_FSMEDGE)){
                log.info("loadJson CY_TYPE_FSMEDGE");

                String edge_name = edge.data.shared_name;
                String sState = edge_name.split("\\s+")[0];
                String dState = edge_name.split("\\s+")[2];
                String eventkey = edge.data.event_context;

                PSIFSMEdge fsmedge = new PSIFSMEdge(sState, dState, eventkey); 
                fsm.addEdge(sState, fsmedge);

                eventmap.put(eventkey, new PSIEvent(eventkey));
                e_f_List.add(fsmkey);
                this.e_f_map.put(eventkey, e_f_List);
            }
            // dag
            if (edge.data.type.equals(CY_TYPE_DAGEDGE)){
                log.info("loadJson CY_TYPE_DAGEDGE");

                String edge_name = edge.data.shared_name;
                String sNF_name = edge_name.split("\\s+")[0];
                String dNF_name = edge_name.split("\\s+")[2];
                String dag_name = edge.data.set;
                String context = edge.data.event_context;

                log.info("loadJson CY_TYPE_DAGEDGE map");
                PSIDAG dag = fsm.dagmap.get(dag_name);
                log.info("loadJson CY_TYPE_DAGEDGE map");
                PSINF sNF = fsm.nfmap.get(sNF_name);
                log.info("loadJson CY_TYPE_DAGEDGE map");
                PSINF dNF = fsm.nfmap.get(dNF_name);
                log.info("loadJson CY_TYPE_DAGEDGE map");
                if (dag == null || sNF == null || dNF == null || context == null)
                {
                    log.info("dag is null");
                }
                if (sNF == null || dNF == null)
                {
                    log.info("sNF dNF = "+sNF_name+dNF_name);
                }
                log.info("bf dag add");
                dag.add(sNF, dNF, context);
                log.info("loadJson CY_TYPE_DAGEDGE map");
            }
        }
        //}
    }

    private PSICyElements readJson(String jsonfile)
    {
        PSICyElements data = new PSICyElements();
        log.info("readJson");
        try{
            String content = new String(Files.readAllBytes(Paths.get(jsonfile)));
            Gson gson = new GsonBuilder().create();
            data = gson.fromJson(content, PSICyElements.class);
            log.info("gson from Json");
        }
        catch(IOException ex){
            log.info("readJson ex: "+ex.toString());
        }

        return data;
    }


    private void readPolicy(String policyfile)
    {
        log.info("readPolicy");
        PSIFSM fsm = new PSIFSM();
        List<String> e_f_List = new ArrayList<String>();
        BufferedReader br = null;
        try
        {
            String sCurrentLine;
            FileReader freader = new FileReader(policyfile);
            br = new BufferedReader(freader);

            /*
             * parse symbol
             * : + =
             * */
            while ((sCurrentLine = br.readLine()) != null)
            {
                // log.info("PolicySet CurrentLine="+sCurrentLine);
                String[] splited = sCurrentLine.split("\\s+");
                // log.info("PolicySet Token="+splited[0]);

                if (splited[0].equals("FSM"))
                {
                    String fsmkey = splited[1];
                    String curstate = splited[2];
                    String scope = splited[3];
                    String eventkey = splited[4];
                    fsm.setKey(fsmkey);
                    fsmmap.put(fsmkey, fsm);
                    eventmap.put(eventkey, new PSIEvent(eventkey));
                    e_f_List.add(fsmkey);
                    e_f_map.put(eventkey, e_f_List);
                    fsm.setCurState(curstate);
                    fsm.scope = scope;
                }
                else if (splited[0].equals("Traffic"))
                {
                    String traffickey = splited[1];

                    // Traffic
                    PSIPredicates sPred = new PSIPredicates(splited[2], splited[3]);
                    PSIPredicates dPred = new PSIPredicates(splited[4], splited[5]);
                    PSITraffic traffic = new PSITraffic(traffickey, sPred, dPred);
                    fsm.addTraffic(traffic);
                    trafficmap.put(traffickey, traffic);
                }
                else if (splited[0].equals("PSITrafficState")) 
                {
                    // add all states
                    for (int i=1;i<splited.length;i++){
                        String state_name = splited[i].split("\\:")[0];
                        String dag_name = (splited[i].split("\\:")[1]).split("\\=")[0];
                        String rdag_name = (splited[i].split("\\:")[1]).split("\\=")[1];
                        // check if the state already exists
                        fsm.addState(new PSITrafficState(state_name, dag_name, rdag_name));
                        // init map from DAG to State
                        fsm.setDAGToState(dag_name, state_name);
                        fsm.setDAGToState(rdag_name, state_name);
                    }
                }
                else if (splited[0].equals("DAG"))
                {
                    PSIDAG DAGtmp = new PSIDAG(splited[1]); 
                    String[] NFList = (splited[2].split("\\:")[1]).split("\\+");
                    String dagstate_name = fsm.getDAGToState(splited[1]);
                    PSITrafficState dagstate = fsm.getTrafficState(dagstate_name);

                    for (int i=0;i<NFList.length;i++){
                        String[] NFpara = NFList[i].split("\\="); 

                        PSINF NFtmp = dagstate.getNF(NFpara[0]);
                        if (NFtmp == null)
                        {
                            NFtmp = new PSINF(NFpara[0], NFpara[1]);
                            dagstate.addNF(NFtmp);
                        }

                        DAGtmp.add(NFtmp);
                    }
                    String[] NFEdgeList = (splited[3].split("\\:")[1]).split("\\+");
                    for (int i=0;i<NFEdgeList.length;i++){
                        String[] para = NFEdgeList[i].split("\\=");
                        PSINF sNFtmp = dagstate.getNF(para[0]);
                        PSINF dNFtmp = dagstate.getNF(para[1]);
                        DAGtmp.add(sNFtmp, dNFtmp, para[2]);
                    }
                    fsm.addDAG(DAGtmp);
                }
                else if (splited[0].equals("PSIFSMEdge")) 
                {

                    String[] state = (splited[1].split("\\:")[1]).split("\\=");
                    //log.info("sState " +state[0]);
                    //log.info("dState " +state[1]);

                    PSIFSMEdge fsmedge = new PSIFSMEdge(state[0], state[1], splited[2].split("\\:")[1]); 
                    fsm.addEdge(state[0],fsmedge);
                }
                else{
                }

            }
            freader.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        fsmmap.put(fsm.getKey(), fsm);
    }

    private static void helper(String cur)
    {
        String[] splited = cur.split("\\s+");
        sb.append(cur +"\n"); 
    }

}
