#!/bin/bash
# ATMOS CORE v3.1 - GEOGRAPHIC ANCHOR DISCOVERY

# Regional Candidate List
declare -A ANCHORS=(
    ["US-East"]="1.1.1.1"
    ["US-West"]="8.8.8.8"
    ["US-Central"]="4.2.2.1"
    ["Europe"]="9.9.9.9"
    ["Asia"]="208.67.222.222"
)

echo -e "\e[1;34m[*] DISCOVERING OPTIMAL GEOGRAPHIC ANCHOR...\e[0m"

BEST_LATENCY=999
BEST_REGION=""

for REGION in "${!ANCHORS[@]}"; do
    IP=${ANCHORS[$REGION]}
    # Quick ping to test latency
    LATENCY=$(ping -c 3 -q $IP | tail -1 | awk -F '/' '{print $5}' | cut -d. -f1)
    
    if [ ! -z "$LATENCY" ] && [ "$LATENCY" -lt "$BEST_LATENCY" ]; then
        BEST_LATENCY=$LATENCY
        BEST_REGION=$REGION
        BEST_IP=$IP
    fi
    echo -e "  - $REGION ($IP): ${LATENCY}ms"
done

echo -e "\e[1;32m[✓] ANCHOR LOCKED: $BEST_REGION ($BEST_IP) at ${BEST_LATENCY}ms\e[0m"
echo "$BEST_IP" > ~/Atmos/.current_anchor
