#!/bin/bash
# ATMOS CORE v3.0 - TOTAL PURGE PROTOCOL

echo -e "\e[1;31m[!] INITIATING TOTAL SYSTEM PURGE...\e[0m"

# 1. Kill background mesh services
pkill -f "main"           # Sentinel
pkill -f "play -n"        # Acoustic Shield
pkill -f "ghost_heartbeat" # Decoy Heartbeat

# 2. Scrub volatile artifacts
rm -rf ~/Atmos/tmp_* rm -rf ~/Atmos/*.jpg
history -c && history -w  # Clear session command history

# 3. Release hardware
termux-wake-unlock

echo -e "\e[1;32m[✓] ALL SERVICES TERMINATED. SESSION CLEAN.\e[0m"
