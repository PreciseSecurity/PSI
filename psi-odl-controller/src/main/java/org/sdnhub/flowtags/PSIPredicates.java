package org.sdnhub.flowtags;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PSIPredicates {

    public String IP;// = PSIUtil.STR;
    public String port;
    public String Group;// = PSIUtil.STR;

    public String sip;
    public String sport;
    public String dip;
    public String dport;
    public String proto;

    public PSIPredicates(String sip){
        IP = sip;
    }

    public PSIPredicates(String sip, String p){
        IP = sip;
        port = p;
    }

    public PSIPredicates(String s_ip, String s_port, String d_ip, String d_port, String protocol) {
        sip = s_ip;
        sport = s_port;
        dip = d_ip;
        dport = d_port;
        proto = protocol;
    }

    public void setIP(String s){
        IP = s;
    }

    public String getIP(){
        return IP;
    }

}
