#!/bin/sh
flowtag_control fw clear
flowtag_control fw add -swId 0x0000000000010001 -hostId 1 -connectorId 1
flowtag_control fw add -swId 0x0000000000010001 -hostId 2 -connectorId 2
flowtag_control fw add -swId 0x0000000000010001 -hostId 5 -connectorId 3
flowtag_control fw add -swId 0x0000000000010001 -hostId 11 -connectorId 3
flowtag_control fw add -swId 0x0000000000010001 -hostId 10 -connectorId 4
flowtag_control fw add -swId 0x0000000000010002 -hostId 1 -connectorId 1
flowtag_control fw add -swId 0x0000000000010002 -hostId 2 -connectorId 1
flowtag_control fw add -swId 0x0000000000010002 -hostId 5 -connectorId 3
flowtag_control fw add -swId 0x0000000000010002 -hostId 11 -connectorId 2
flowtag_control fw add -swId 0x0000000000010002 -hostId 10 -connectorId 1
