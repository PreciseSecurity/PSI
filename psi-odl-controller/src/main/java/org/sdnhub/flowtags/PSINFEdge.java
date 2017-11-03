package org.sdnhub.flowtags;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PSINFEdge {

    public int id;
    public PSINF sNF; // from node
    public PSINF dNF; // to node
    public String tNote; // traffic annotation

    public String name;
    public String snf;
    public String dnf;
    public String context;
    public int tag;

    public PSINFEdge(PSINF s, PSINF d, String t){
        sNF = s;
        dNF = d;
        tNote = t;
    }

    public PSINFEdge(String name_str, String snf_str, String dnf_str, String context_str){
        name = name_str;
        snf = snf_str;
        dnf = dnf_str;
        context = context_str;
    }

}
