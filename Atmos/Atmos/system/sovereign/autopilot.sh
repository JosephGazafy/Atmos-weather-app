#!/bin/bash
# ATMOS CORE v3.1 - ADAPTIVE AUTOPILOT (SIGNAL-KILL ENABLED)
./logo.sh

echo -e "\e[1;34m[*] AUTOPILOT ENGAGED: MONITORING GEOGRAPHIC & SIGNAL INTEGRITY...\e[0m"

# Initial Anchor Discovery
./find_anchor.sh
LAST_IP=$(ip route get 1.1.1.1 | grep -oP 'src \K\S+')
TIMEOUT_COUNT=0

while true; do
    ANCHOR_IP=$(cat ~/Atmos/.current_anchor)
    CURRENT_IP=$(ip route get 1.1.1.1 | grep -oP 'src \K\S+')

    # 1. Network Connectivity Check
    if ! ping -c 1 -W 2 "$ANCHOR_IP" > /dev/null; then
        if [ -f "$HOME/Atmos/.safe_mode_active" ]; then echo -ne "\e[1;32m[*] SAFE MODE ACTIVE: BYPASSING SIGNAL-KILL\r\e[0m"; continue; fi
        ((TIMEOUT_COUNT++))
        echo -ne "\e[1;31m[!] SIGNAL LOST: ANCHOR UNREACHABLE ($TIMEOUT_COUNT/6)\r\e[0m"
        
        # 2. Trigger Slough-All after 60 seconds (6 loops * 10s)
        if [ $TIMEOUT_COUNT -ge 12 ]; then
            echo -e "\n\e[1;31m[!!!] CRITICAL SIGNAL FAILURE. TRIGGERING SLOUGH PROTOCOL...\e[0m"
            ~/Atmos/slough_all.sh
            exit 1
        fi
    else
        # Reset timeout if signal returns
        if [ $TIMEOUT_COUNT -gt 0 ]; then
            echo -e "\n\e[1;32m[✓] SIGNAL RESTORED. TIMEOUT RESET.\e[0m"
            TIMEOUT_COUNT=0
        fi
    fi

    # 3. Network Shift Discovery
    if [ "$CURRENT_IP" != "$LAST_IP" ] && [ ! -z "$CURRENT_IP" ]; then
        echo -e "\n\e[1;33m[!] NETWORK SHIFT: RE-ANCHORING...\e[0m"
        ./find_anchor.sh
        LAST_IP=$CURRENT_IP
        pkill -f "./main"
    fi

    # 4. Maintain Sentinel Process
    if ! pgrep -f "./main" > /dev/null; then
        ./main "$ANCHOR_IP" &
    fi

    sleep 10
done
