#!/bin/bash
# ATMOS CORE v3.1 - DEAD MAN'S SWITCH PULSE
cd ~/Atmos
echo "$(date +%s)" > .last_pulse
git add .last_pulse
git commit -m "HEARTBEAT: Pulse Detected"
git push origin main
echo -e "\e[1;32m[✓] PULSE SENT. DEAD MAN'S SWITCH RESET FOR 7 DAYS.\e[0m"
