#!/bin/bash
# ATMOS CORE v3.0 - SILENT WATCH (HWM VARIANCE)
BASELINE=32.4
THRESHOLD=15.0

while true; do
  # Sample current latency
  CURRENT=$(ping -c 1 1.1.1.1 | grep 'time=' | awk -F'time=' '{print $2}' | awk '{print $1}')
  
  if (( $(echo "$CURRENT > ($BASELINE + $THRESHOLD)" | bc -l) )); then
    termux-notification --title "⚠️ HWM VARIANCE ALERT" \
                        --content "Latency: ${CURRENT}ms. Threshold exceeded." \
                        --id 202 --priority urgent --led-color FF0000
  fi
  
  # Heartbeat to the Ghost Registry every 4 hours via background sync
  sleep 60
done
