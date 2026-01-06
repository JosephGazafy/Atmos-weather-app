#!/bin/bash
# ATMOS CORE v3.1 - KINETIC KILL-SWITCH
THRESHOLD=28.0

echo "[$(date)] Kinetic Listener Engaged. Monitoring for high-G events..."

termux-sensor -s "Accelerometer" -n 1000 | while read -r line; do
    # Extracting the absolute acceleration value
    # We look for a "total" acceleration spike exceeding the threshold
    ACCEL=$(echo "$line" | grep -oP '"value": \[\K[^\]]+' | awk -F, '{print sqrt($1*$1 + $2*$2 + $3*$3)}')
    
    if (( $(echo "$ACCEL > $THRESHOLD" | bc -l) )); then
        echo "[!] CRITICAL KINETIC EVENT DETECTED ($ACCEL m/s^2)"
        ~/Atmos/slough.sh
        exit 0
    fi
done
