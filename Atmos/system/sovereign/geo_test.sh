#!/bin/bash
# ATMOS CORE v3.0 - GEOGRAPHIC LATENCY TEST
./logo.sh
echo -e "\e[1;36m🌍 ANALYZING GLOBAL ANCHOR POINTS...\e[0m"
echo "----------------------------------------------------"

# Define Global Regional Targets
declare -A REGIONS=(
    ["Americas (US-West)"]="8.8.8.8"
    ["Americas (US-East)"]="1.1.1.1"
    ["Europe (Frankfurt)"]="de.pool.ntp.org"
    ["Asia (Tokyo)"]="jp.pool.ntp.org"
    ["Oceania (Sydney)"]="au.pool.ntp.org"
)

BEST_RTT=999
BEST_REGION=""

for REGION in "${!REGIONS[@]}"; do
    TARGET=${REGIONS[$REGION]}
    echo -n "📍 Testing $REGION... "
    
    # Extract average RTT from 3 pings
    RTT=$(ping -c 3 $TARGET 2>/dev/null | tail -1 | awk '{print $4}' | cut -d '/' -f 2)
    
    if [ -z "$RTT" ]; then
        echo -e "\e[1;31mTIMEOUT\e[0m"
    else
        echo -e "\e[1;32m${RTT}ms\e[0m"
        
        # Determine the lowest latency
        if (( $(echo "$RTT < $BEST_RTT" | bc -l) )); then
            BEST_RTT=$RTT
            BEST_REGION=$REGION
        fi
    fi
done

echo "----------------------------------------------------"
echo -e "\e[1;33m🏆 RECOMMENDED ANCHOR: $BEST_REGION (${BEST_RTT}ms)\e[0m"
echo -e "Update your 'main.go' target to optimize your HWM."

