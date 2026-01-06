#!/bin/bash
# ATMOS CORE - DECOY DASHBOARD (Visual Deterrent)
clear
echo -e "\e[1;32m[+] ATMOS NETWORK MONITOR v3.2 - GUEST ACCESS\e[0m"
echo "----------------------------------------------------"

# Scrolling "Fake" Activity
while true; do
    TIMESTAMP=$(date +"%H:%M:%S")
    RANDOM_IP="192.168.1.$((RANDOM%255))"
    PORT=$((RANDOM%65535))
    
    # Mix real system stats with fake log entries
    echo -e "[$TIMESTAMP] \e[1;34mINFO:\e[0m Incoming handshake from $RANDOM_IP:$PORT... \e[1;32mACCEPTED\e[0m"
    echo -e "[$TIMESTAMP] \e[1;36mSTAT:\e[0m CPU Load: $(uptime | awk -F'load average:' '{ print $2 }')"
    echo -e "[$TIMESTAMP] \e[1;33mWARN:\e[0m Minor jitter detected in US-Central Anchor."
    
    sleep $((RANDOM%3 + 1))
done
