package org.sdnhub.flowtags;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PSITrafficState {
    private int id;
    private String key;
    // a pair of DAGs
    private String DAG;
    private String rDAG;
    public List<String> daglist = new ArrayList<String>();

    private String curState = "default";
    private Map<String, String> map = new HashMap<String, String>();

    public PSITraffic traffic;
    public String DAGdif;
    public int s1_port = 0;
    public int s2_port = 0;
    public int s3_port = 0;

    // all node 
    private Map<String,PSINF> nfmap = new HashMap<String,PSINF>();
    private List<PSINF> nflist = new ArrayList<PSINF>();

    public PSITrafficState(){
    }

    public PSITrafficState(String s){
        key = s;
    }
    
    public PSITrafficState(String s, String d){
        key = s;
        DAG = d;
    }

    public PSITrafficState(String s, String d, String rd){
        key = s;
        DAG = d;
        rDAG = rd;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String s){
        key = s;
    }

    public void addNF(PSINF nf){
        nflist.add(nf);
        nfmap.put(nf.str, nf);
    }

    public PSINF getNF(String s){
        return nfmap.get(s);
    }

    public List<PSINF> getNFs(){
        return nflist;
    }

    public String getDAG()
    {
        return DAG;
    }

    public void setDAG(String s){
        DAG = s;
    }

    public String getRDAG()
    {
        return rDAG;
    }

    public void setRDAG(String s){
        rDAG = s;
    }

}
