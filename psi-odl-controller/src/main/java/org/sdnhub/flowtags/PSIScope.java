package org.sdnhub.flowtags;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PSIScope {

    public String name;
    public String in_sw;
    public String e_sw;

    // local
    public Map<String, String> device_port_map = new HashMap<String, String>();

    // other scope
    public String scope_port;
    public String other_port;

    public PSIScope(){
    }

    public PSIScope(String name_str){
        this.name = name_str;
    }


}
