for i in range(1,2):
    filename = "policy"+str(i)
    target = open(filename, 'w')
    
    line1 = "FSM FSM"+str(i)+" Event"+str(i)+" TrafficState1"
    line2 = "Traffic Traffic"+str(i)+" 192.168.123.1 20 192.168.123.2 80"
    line3 = "DAG DAG1 PSINF:Ingress=config_in+IDS=config_ids+IPS=config_ips+Egress=config_e PSINFEdge:Ingress=IDS=all+IDS=IPS=suspicous+IDS=Egress=normal+IPS=Egress=normal"
    line4 = "DAG DAG2 PSINF:Ingress=config_in+IDS=config_ids2+IPS=config_ips2+Egress=config_e PSINFEdge:Ingress=IDS=all+IDS=IPS=suspicous+IDS=Egress=normal+IPS=Egress=normal"
    line5 = "PSITrafficState TrafficState1:DAG1 TrafficState2:DAG2"
    line6 = "PSIFSMEdge State:TrafficState1=TrafficState2 Event:Event"+str(i)
    line7 = "PSIFSMEdge State:TrafficState2=TrafficState1 Event:Event"+str(i)

    target.write(line1);
    target.write("\n");
    target.write(line2);
    target.write("\n");
    target.write(line3);
    target.write("\n");
    target.write(line4);
    target.write("\n");
    target.write(line5);
    target.write("\n");
    target.write(line6);
    target.write("\n");
    target.write(line7);
