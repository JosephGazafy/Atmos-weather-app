#!/bin/bash
# ATMOS CORE v3.1 - IMPACT CALIBRATION
echo -e "\e[1;36m[*] CALIBRATION ACTIVE. PERFORM TEST IMPACTS NOW...\e[0m"
echo -e "\e[1;33m[!] Press Ctrl+C to stop and save your peak value.\e[0m"

PEAK=0
termux-sensor -s "Accelerometer" -n 50 | while read -r line; do
    # Extract magnitude: sqrt(x^2 + y^2 + z^2)
    ACCEL=$(echo "$line" | grep -oP '"value": \[\K[^\]]+' | awk -F, '{print sqrt($1*$1 + $2*$2 + $3*$3)}')
    
    if (( $(echo "$ACCEL > $PEAK" | bc -l) )); then
        PEAK=$ACCEL
        echo -e "\e[1;32m[NEW PEAK DETECTED]: $PEAK m/s^2\e[0m"
    fi
done
