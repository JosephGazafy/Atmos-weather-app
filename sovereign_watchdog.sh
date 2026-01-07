#!/bin/bash
# Atmos-Engine: Watchdog Auto-Recovery (v153.0)

while true; do
  if ! pgrep -f "sovereign_command.py" > /dev/null; then
    timestamp=$(date "+%Y-%m-%d %H:%M:%S")
    echo "[$timestamp] WATCHDOG: Sovereign Command Offline. Re-initializing..." >> watchdog_recovery.log
    
    # Re-pulling state to ensure Bit-Perfect Sync
    git pull origin master
    
    # Restarting the Primary Command Core
    nohup python sovereign_command.py > /dev/null 2>&1 &
    
    # Vocalize Recovery if Termux-API is active
    termux-tts-speak "Watchdog Alert. Sovereign Command recovered at $timestamp."
  fi
  sleep 300 # 5-minute audit interval
done
