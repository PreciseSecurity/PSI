package org.sdnhub.flowtags;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PSIDevice {

    public String name;
    public String gateway;
    public String gw_port;
    public String ip;

    public PSIDevice(){
    }

    public PSIDevice(String name_str){
        this.name = name_str;
    }


}
