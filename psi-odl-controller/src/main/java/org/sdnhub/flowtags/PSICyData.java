package org.sdnhub.flowtags;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PSICyData {

    // nodes elems
    public String id;
    public String state_to_context;
    public String ip_address;
    public String device_list;
    public String scope;
    public String nf_type;
    public String set;
    public String predicate;
    public String type;
    public String mac_address;
    public String proto;
    public String nf_scope;
    public String name;
    public String state_to_event;
    public String shared_name;
    public String DAGs;

    // edges - nodes elems
    public String source;
    public String target;
    public String event_context;

    @Override
    public String toString() {
        return id + " - " + type;
    }

}
