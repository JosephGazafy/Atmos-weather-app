#!/bin/bash
# ATMOS MASTER ENGINE v49.4.1 - CORRECTED SOMATICS
tput civis 2>/dev/null
trap "tput cnorm; clear; exit" SIGINT SIGTERM

PRIN="\$65,737.61"; JOULES=$(cat ~/Atmos-Engine/vault/total_joules.cfg 2>/dev/null || echo "0.00")
MILESTONE=1000000
B='\033[1;34m'; W='\033[1;37m'; G='\033[1;32m'; Y='\033[1;33m'; NC='\033[0m'; CLR='\033[K'

while true; do
    echo -ne "\033[H"
    ~/Atmos-Engine/bin/health-calc.sh
    
    # CORRECTED MILESTONE TRIGGER
    CURRENT_M=$(echo "$JOULES / $MILESTONE" | bc)
    if [ "$CURRENT_M" -gt "${LAST_M:-0}" ]; then
        # Sequenced vibration to avoid "Input String" errors
        for i in {1..3}; do termux-vibrate -d 100; sleep 0.1; done
        termux-tts-speak "Milestone reached. Sovereign parity confirmed."
        LAST_M=$CURRENT_M
    fi

    # HUD RENDER (Simplified for stability)
    echo -e "${B}┌────────────────────────────────────────┐${NC}"
    echo -e "${B}│${NC} ${W}🌑 💎 [ ATMOS: FAIL-SAFE SYNC ] 💎 🌑${NC}  ${B}│${NC}"
    printf "${B}│${NC} 💰 PRN: %-10s | 🛡️ STATUS: ACTIVE    ${B}│${NC}\n" "$PRIN"
    echo -e "${B}└────────────────────────────────────────┘${NC}"

    # HARVEST
    INC=$(cat ~/Atmos-Engine/vault/last_inc.cfg 2>/dev/null || echo "0.45")
    JOULES=$(echo "$JOULES + $INC" | bc 2>/dev/null)
    echo "$JOULES" > ~/Atmos-Engine/vault/total_joules.cfg
    printf "\r${Y}JOULES: %s J${NC}${CLR}" "$JOULES"
    sleep 0.033
done
