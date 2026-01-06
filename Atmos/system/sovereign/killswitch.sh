#!/bin/bash
# ATMOS CORE v3.0 - REMOTE KILL SWITCH

# Replace [USER] with JosephGazafy
STATUS=$(curl -s https://raw.githubusercontent.com/JosephGazafy/Atmos/main/killswitch.txt)

if [ "$STATUS" == "ACTIVE" ]; then
    echo "🔥 REMOTE KILL SIGNAL RECEIVED. SHREDDING CORE..."
    cd ~
    rm -rf ~/Atmos
    history -c
    exit 0
fi

