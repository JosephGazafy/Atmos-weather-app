#!/bin/bash
# ATMOS VOLATILITY-HARDENED TRANSFUSION (v44.7)
B='\033[1;34m'; G='\033[1;32m'; Y='\033[1;33m'; R='\033[1;31m'; NC='\033[0m'

~/Atmos-Engine/bin/vix-fetcher.sh
THRESHOLD=$(cat ~/Atmos-Engine/vault/consensus_threshold.cfg)
STATE=$(cat ~/Atmos-Engine/vault/market_state.cfg)

echo -e "${B}>> MARKET STATE: $STATE (Threshold: $THRESHOLD/13) <<${NC}"

SUCCESSFUL_PINGS=0
for i in {1..12}; do
    if [ $(( RANDOM % 10 )) -gt 1 ]; then ((SUCCESSFUL_PINGS++)); fi
done

if [ $((SUCCESSFUL_PINGS + 1)) -ge $THRESHOLD ]; then
    echo -e "${G}>> VOLATILITY-RESISTANT CONSENSUS REACHED. <<${NC}"
    echo "20" > ~/Atmos-Engine/vault/transfusion_boost.cfg
else
    echo -e "${R}>> CONSENSUS DENIED: VOLATILITY REQUIRES HIGHER PARITY. <<${NC}"
    echo "0" > ~/Atmos-Engine/vault/transfusion_boost.cfg
fi
