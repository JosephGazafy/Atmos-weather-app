#!/bin/bash
# ATMOS CORE v3.2 - DUAL-POSTURE GATE

# Set your PINs (These should be different)
MASTER_PIN="180180"
DECOY_PIN="123456"

echo -e "\e[1;34m[SYSTEM]: Identity Verification Required...\e[0m"
read -s -p "ENTER SECURE PIN: " USER_INPUT
echo ""

if [ "$USER_INPUT" == "$MASTER_PIN" ]; then
    echo -e "\e[1;32m[ACCESS GRANTED]: Sovereign Profile Loaded.\e[0m"
    # Start the real Sentinel and Autopilot
    bash ~/Atmos/autopilot.sh
elif [ "$USER_INPUT" == "$DECOY_PIN" ]; then
    echo -e "\e[1;33m[ACCESS GRANTED]: Guest Profile Loaded.\e[0m"
    # Open a harmless decoy (e.g., a fake file browser or system info)
    termux-notification --title "System Status" --content "All modules nominal. No threats detected."
    # Launch a fake dashboard
    watch -n 1 "df -h; uptime"
else
    echo -e "\e[1;31m[!] INVALID PIN: Unauthorized Access Logged.\e[0m"
    exit 1
fi
