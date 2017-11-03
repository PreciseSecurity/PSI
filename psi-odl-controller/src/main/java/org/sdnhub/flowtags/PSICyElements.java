package org.sdnhub.flowtags;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PSICyElements {

    private PSICyNode[] nodes;
    private PSICyEdge[] edges;

    public PSICyNode[] getNodes(){
        return nodes;
    }

    public PSICyEdge[] getEdges(){
        return edges;
    }

    @Override
    public String toString() {
        // return nodes.length + " - "+ edges.length;
        return nodes[0] + " - "+ edges[0];
    }

}
