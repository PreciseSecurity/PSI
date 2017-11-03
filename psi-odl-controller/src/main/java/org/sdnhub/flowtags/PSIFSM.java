package org.sdnhub.flowtags;

import java.util.HashMap;
import java.util.Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * FSM for each Traffic
 * */
public class PSIFSM {
    protected static final Logger log = LoggerFactory.getLogger(PSIFSM.class);
    public String key;
    // to be deprecated
    public PSITraffic traffic;
    // to store a pair of traffic
    public List<PSITraffic> trafficlist = new ArrayList<PSITraffic>();
    /* current Traffic State */
    public String curState;
    /* string - vertex map */
    public Map<String, PSITrafficState> statemap = new HashMap<String, PSITrafficState>();
    /* vertex - edge map */
    public Map<String, List<PSIFSMEdge>> edgemap = new HashMap<String, List<PSIFSMEdge>>();
    /* dagmap */
    public Map<String, PSIDAG> dagmap = new HashMap<String, PSIDAG>();
    /* 
     * vertex - DAG map
     * every state correspond to one DAG 
     * */ 
    public Map<String, String> v_d_map = new HashMap<String, String>();
    // dag to state
    public Map<String,String> dag_state_map = new HashMap<String, String>();
    // nfmap
    public Map<String, PSINF> nfmap = new HashMap<String, PSINF>();

    // record the intag for FSM
    public int intag = 1;
    // scope notion
    public String scope = "local";
    // scope notion
    public String device;

    public String sw_in;
    public String sw_eg;
    public int port_in;
    public int port_eg;

    public PSIFSM(){
    }

    public PSITrans handleEvent(PSIEvent event){
        log.info("handleEvent(PSIEvent event)");
        /*
         * search all the edges of current state
         * */
        for(PSIFSMEdge edge : edgemap.get(curState)){
            log.info("for(PSIFSMEdge edge "+event.getKey()+" "+edge.getEvent());
            if ((event.getKey()).equals(edge.getEvent()))
            {
                log.info("if ((event.getKey()).equals(edge.getEvent()))");
                String sStateKey = (statemap.get(edge.getSState())).getKey();
                String dStateKey = (statemap.get(edge.getDState())).getKey();

                PSIDAG sDAG = dagmap.get(v_d_map.get(sStateKey));
                PSIDAG dDAG = dagmap.get(v_d_map.get(dStateKey));
                if (!(sStateKey.equals(dStateKey)))
                {
                    log.info("if (!(sStateKey.equals(dStateKey)))");
                    // Jump to next state
                    curState = dStateKey;
                    return new PSITrans(true, sDAG, dDAG, edge.getEvent());
                }
                else{
                    return new PSITrans(false, sDAG, dDAG, edge.getEvent());
                }
            }
        }
        return new PSITrans(false);
    }

    public void setDAGToState(String dag, String state){
        dag_state_map.put(dag, state);
    }

    public String getDAGToState(String s){
        return dag_state_map.get(s);
    }

    public List<PSIFSMEdge> getFSMEdges(String state){
        return edgemap.get(state); 
    }

    public PSIDAG getCurDAG(){
        return dagmap.get(v_d_map.get(curState)); 
    }

    public PSIDAG getDAG(String s){
        return dagmap.get(s);
    }

    public void setKey(String s){
        key = s;
    }

    public String getKey(){
        return key;
    }

    public void addTraffic(PSITraffic t){
        trafficlist.add(t);
    }

    public PSITraffic getTraffic(int i){
        return trafficlist.get(i);
    }

    public List<PSITraffic> getTrafficList(){
        return trafficlist;
    }

    public void setCurState(String s){
        curState = s;
    }

    public String getCurState(){
        return curState;
    }

    /**
     *      *      * Add a vertex to the graph.  Nothing happens if vertex is already in graph.
     *          */
    public boolean addState (PSITrafficState state) {
        if (statemap.containsKey(state.getKey())) return false;
        statemap.put(state.getKey(), state);
        edgemap.put(state.getKey(), new ArrayList<PSIFSMEdge>());
        return true;
    }

    public PSITrafficState getTrafficState (String statekey){
        return statemap.get(statekey);
    }

    /**
     *      *      * True iff graph contains vertex.
     *           *           */
    public boolean contains (PSITrafficState state) {
        return statemap.containsKey(state.getKey());
    }

    public void addEdge (String key, PSIFSMEdge edge){
       (edgemap.get(key)).add(edge);
    }

    public boolean addDAG (PSIDAG dag) {
        if (dagmap.containsKey(dag.getKey())) return false;
        dagmap.put(dag.getKey(), dag);
        return true;
    }

}
