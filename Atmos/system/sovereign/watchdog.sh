#!/bin/bash
# ATMOS CORE v3.0 - PERSISTENT WATCHDOG

echo "🛡️ Watchdog Daemon Started. Monitoring Kill Switch & Storage..."

while true; do
    ./killswitch.sh
    ./rotate_vault.sh  # <--- Added Rotation Logic
    
    if ! pgrep -f "alert_sentinel.sh" > /dev/null; then
        ./alert_sentinel.sh &
    fi
    
    sleep 300
done

