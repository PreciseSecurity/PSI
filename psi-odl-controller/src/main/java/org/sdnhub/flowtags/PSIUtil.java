package org.sdnhub.flowtags;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PSIUtil {

    protected static final Logger log = LoggerFactory.getLogger(PSIEvent.class);

    public static String STR = "DEFAULT_STRING";

    public static String CONTROL_SWITCH     = "control_sw";
    public static String CONTROL_NW_NETMASK = "255.255.255.0";
    public static String CONTROL_IF         = "eth1";
    public static String NET_IF             = "eth4";
    public static String CONTROLLER_ADDRESS = "192.168.123.201:6633";
    public static String HOST_DOMAIN_PREFIX     = "host";
    public static String HOST_PREFIX            = "h";
    public static String HOST_AGENT_PREFIX      = "ha";
    public static String HOST_AGENT_NET_PORT    = "2";
    public static String HOST_AGENT_HOST_PORT   = "1";
    public static String SW_PREFIX              = "s";
    public static String VSW_PREFIX             = "v";
    public static String NET                    = "net";
    //MIN_VSW_DPID           = 0x100
    //MIN_SW_DPID            = 0x10000
    //INITIAL_VM_CAPACITY    = 10
    //INITIAL_SLICE_CAPACITY = 10 

    public static final String PSI_GATEWAY = "s1";

    /*
    public void execCMD(String s)
    {
        Process p;
        try {
            p = Runtime.getRuntime().exec(s);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null)
                log.info("line:" + s);
            p.waitFor();
            log.info("exit:" + p.exitValue());
            p.destroy();
        } catch (Exception e) {}
    }
    */
}
