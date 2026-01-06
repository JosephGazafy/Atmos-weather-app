#!/bin/bash
# ATMOS CORE v3.3 - SAFE-SHUTDOWN & HISTORY WIPE

echo -e "\e[1;31m[!] INITIATING VANISH PROTOCOL...\e[0m"

# 1. Terminate all Atmos-linked PIDs
pkill -f "python atmos_api.py"
pkill -f "./bridge"
pkill -f "health_check.sh"
pkill -f "main.go"

# 2. Haptic/Visual confirmation
termux-notification --title "ATMOS: SHUTDOWN" --content "All gates closed. Logic offline." --priority low
termux-vibrate -d 200

# 3. Memory & History Purge
history -c              # Clear session history
rm -f ~/.bash_history   # Shred history file
touch ~/.bash_history   # Recreate empty shell

echo -e "\e[1;32m[✓] ALL GATES CLOSED. SESSION PURGED.\e[0m"
sleep 1
clear
exit
