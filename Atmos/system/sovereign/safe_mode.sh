#!/bin/bash
# ATMOS CORE v3.1 - SAFE MODE TOGGLE
LOCK_FILE="$HOME/Atmos/.safe_mode_active"

if [ -f "$LOCK_FILE" ]; then
    rm "$LOCK_FILE"
    echo -e "\e[1;31m[!] SAFE MODE DEACTIVATED. SIGNAL-KILL RE-ARMED.\e[0m"
    termux-notification --title "ATMOS: SIGNAL-KILL ARMED" --content "Network monitor is live." --priority high
else
    touch "$LOCK_FILE"
    echo -e "\e[1;32m[✓] SAFE MODE ACTIVE. SIGNAL-KILL SUSPENDED.\e[0m"
    termux-notification --title "ATMOS: SAFE MODE" --content "Signal-Kill paused for travel." --priority low
fi
