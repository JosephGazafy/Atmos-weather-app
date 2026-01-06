#!/bin/bash
# ATMOS CORE v3.1 - STABILITY GUARDIAN
PACKAGE_NAME="org.sovereign.mesh.atmos"
SERVICE_NAME="org.sovereign.mesh.AtmosService"

echo -e "\e[1;34m[*] STABILITY GUARDIAN INITIALIZED [2025-12-20]\e[0m"

while true; do
    # Check for the process ID of the APK
    PID=$(pidof $PACKAGE_NAME)

    if [ -z "$PID" ]; then
        echo -e "\e[1;31m[!] ALERT: ATMOS SERVICE DROPPED. RE-INITIALIZING...\e[0m"
        
        # Fire high-priority Termux Notification
        termux-notification --title "⚠️ ATMOS BREACH: SERVICE DEAD" \
                            --content "The Sentinel has stopped. Restart Atmos APK immediately." \
                            --priority high \
                            --vibrate 500,500,500 \
                            --led-color ff0000
        
        # Optional: Attempt to force-restart the service (requires ADB)
        # am startservice -n $PACKAGE_NAME/$SERVICE_NAME
    else
        echo -e "\e[1;32m[✓] SENTINEL PULSE DETECTED (PID: $PID) - $(date)\e[0m"
    fi

    # Wait 60 minutes (3600 seconds)
    sleep 3600
done

