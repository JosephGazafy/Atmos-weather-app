#!/bin/bash

# Canonical Path to the Bridge
BRIDGE_CMD="python ~/judah-joseph-Atmos-Engine/carbon-interface/bridge.py"

echo "🛰️ [ATMOS] DAEMON INITIALIZED. MONITORING BRIDGE..."

while true; do
    # Check if the bridge is already running
    if ! pgrep -f "bridge.py" > /dev/null; then
        echo "⚠️ [ATMOS] BRIDGE OFFLINE. RE-INITIALIZING..."
        nohup $BRIDGE_CMD > ~/Atmos-Engine/logs/bridge_output.log 2>&1 &
    fi
    # Wait for 60 seconds before next heartbeat check
    sleep 60
done
