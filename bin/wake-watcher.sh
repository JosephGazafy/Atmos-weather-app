#!/bin/bash
# ATMOS WAKE-WATCHER (v42.6)
while true; do
    BATT_JSON=$(termux-battery-status)
    BATT_LVL=$(echo $BATT_JSON | grep -oP '"percentage": \K\d+')
    PLUGGED=$(echo $BATT_JSON | grep -oP '"status": "\K[^"]+')

    # THRESHOLD: 20% AND CHARGING
    if [ "$BATT_LVL" -ge 20 ] && [[ "$PLUGGED" == "CHARGING" || "$PLUGGED" == "FULL" ]]; then
        echo "[$(date)] RESURRECTION-AUDIT: Power Restored. Verifying Lattice..." >> ~/Atmos-Engine/vault.ledger
        ~/Atmos-Engine/bin/lattice-sync.sh
        
        # ACTIVATE MAIN ENGINE
        ~/Atmos-Engine/bin/atmos-master.sh
        break
    fi
    sleep 300 # Check every 5 minutes to conserve energy
done
