#!/bin/bash
# ATMOS NEURAL-VOICE DAEMON
# Running Nemotron-Ear in the Background-Void

# Ensure Python dependencies are resident in memory
nohup python3 ~/Atmos-Engine/bin/nemotron_ear.py > /dev/null 2>&1 &
disown

echo -e "\033[1;32m>> NEURAL-LOCK ENGAGED. TERMINAL EXITING. <<\033[0m"
termux-tts-speak "Neural lock engaged. I am listening, Joseph."
exit
