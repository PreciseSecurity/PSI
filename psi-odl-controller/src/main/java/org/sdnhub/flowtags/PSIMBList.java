package org.sdnhub.flowtags;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PSIMBList {
    public String MBType;
    public List<String> MBList;
    public int nextMB = 0;

    public PSIMBList(String type){
        MBType = type;
        MBList = new ArrayList<String>();
    }

    public void addMB(String mb)
    {
        MBList.add(mb);
    }

    public String getNextMB(){
        String temp = MBList.get(nextMB);
        nextMB = nextMB + 1;
        return temp;
    }
        
}
