#!/bin/bash
# FILE: soma_heartbeat.sh
# PROJECT: Judah-Joseph-Atmos-Engine
# MISSION: Monitor the 'data_vault' anchor and physical constant integrity

VAULT="./data_vault"
INTERVAL=5 # Pulse every 5 seconds

echo "--- SOMA ENGINE: HEARTBEAT MONITOR INITIALIZED ---"
echo "Monitoring anchor: $VAULT"

while true; do
    TIMESTAMP=$(date +"%Y-%m-%d %H:%M:%S")
    
    if [ -L "$VAULT" ] && [ -d "$VAULT" ]; then
        # Check for the existence of a core scaling file
        if [ -f "$VAULT/atmos_physics_update.json" ]; then
            STATUS="ALIVE (NOMINAL)"
            COLOR="\033[0;32m" # Green
        else
            STATUS="WARNING (DATA MISSING)"
            COLOR="\033[0;33m" # Yellow
        fi
    else
        STATUS="DEAD (LINK BROKEN)"
        COLOR="\033[0;31m" # Red
    fi

    echo -e "[$TIMESTAMP] Status: ${COLOR}${STATUS}\033[0m"
    
    # Optional: Log errors to a file for Atmos debugging
    if [[ "$STATUS" != "ALIVE (NOMINAL)" ]]; then
        echo "[$TIMESTAMP] Engine Anchor Error" >> soma_error.log
    fi

    sleep $INTERVAL
done
