#!/bin/bash
# ATMOS SOVEREIGN ANALYTICS (v48.8)
B='\033[1;34m'; G='\033[1;32m'; Y='\033[1;33m'; R='\033[1;31m'; W='\033[1;37m'; NC='\033[0m'

REPAIRS=$(grep -c "REPAIR" ~/Atmos-Engine/vault/resurrection.ledger)
JOULES=$(cat ~/Atmos-Engine/vault/total_joules.cfg 2>/dev/null || echo "10000")

# Logic for Grading
if [ "$REPAIRS" -eq 0 ]; then GRADE="[A] ABSOLUTE"; COLOR=$G
elif [ "$REPAIRS" -le 2 ]; then GRADE="[B] STABLE"; COLOR=$G
elif [ "$REPAIRS" -le 5 ]; then GRADE="[C] VOLATILE"; COLOR=$Y
else GRADE="[D] DEGRADED"; COLOR=$R
fi

echo -e "${B}┌────────────────────────────────────────┐${NC}"
echo -e "${B}│${NC} ${W}🌑 💎 [ ATMOS: SOVEREIGN GRADE ] 💎 🌑${NC}  ${B}│${NC}"
echo -e "${B}├────────────────────────────────────────┤${NC}"
printf "${B}│${NC} ${W}EFFICIENCY GRADE:${NC} %-18b ${B}│${NC}\n" "${COLOR}${GRADE}${NC}"
printf "${B}│${NC} ${W}TOTAL REPAIRS:   ${NC} %-18s ${B}│${NC}\n" "$REPAIRS"
echo -e "${B}└────────────────────────────────────────┘${NC}"

# VOCALIZED GRADE
termux-tts-speak "Current Sovereign Efficiency is $GRADE. Principal is bit-perfect."
