package org.sdnhub.flowtags;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PSISw {

    public String name;
    public String mbport;
    // for nf
    public Map<String, String> inports = new HashMap<String, String>(); 
    public Map<String, String> outports = new HashMap<String, String>(); 
    // for Ingress/Egress
    // key is fsm, value is fsm port
    public Map<String, String> fsm_inports = new HashMap<String, String>(); 
    public Map<String, String> fsm_outports = new HashMap<String, String>(); 
    // public Map<String, String> fsmports = new HashMap<String, String>(); 

    public int curPort = 0;

    public PSISw(){
    }

    public PSISw(String name_str){
        this.name = name_str;
    }

    @Override
    public String toString() {
        return "PSISw: " + name + " - " + mbport;
    }


}
