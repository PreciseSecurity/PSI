package org.sdnhub.flowtags;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PSITraffic {
    public String key;
    public PSIPredicates src;
    public PSIPredicates dst;
    
    public PSIPredicates pred;

    public PSITraffic(String k, PSIPredicates s){
        key = k;
        pred = s;
    }
    public PSITraffic(PSIPredicates s, PSIPredicates d){
        src = s;
        dst = d;
    }
    public PSITraffic(String k, PSIPredicates s, PSIPredicates d){
        key = k;
        src = s;
        dst = d;
    }
}
