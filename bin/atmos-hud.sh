#!/bin/bash
# ATMOS-ATLAS STATIONARY HUD v34.8
# ANSI: \033[H (Home) | \033[J (Clear from cursor down)

# One-time full clear to prepare the canvas
clear

while true; do
    # Force cursor to row 1, column 1
    echo -ne "\033[H"
    
    # Colors
    BLU='\033[1;34m'
    WHT='\033[1;37m'
    GRN='\033[1;32m'
    YLW='\033[1;33m'
    PUR='\033[1;35m'
    RED='\033[1;31m'
    NC='\033[0m'

    # The Fixed Frame
    echo -e "${BLU}┌────────────────────────────────────────────────────────────┐${NC}"
    echo -e "${BLU}│${NC}  ${WHT}🌑💎✨🌀 [ ATMOS-ATLAS-01: STATIONARY ] 🌀✨💎🌑${NC}  ${BLU}│${NC}"
    echo -e "${BLU}├────────────────────────────────────────────────────────────┤${NC}"
    echo -e "${BLU}│${NC} ${GRN}💰 PRINCIPAL   :${NC} ${WHT}\$65,737.61 (BIT-PERFECT)${NC}        ${BLU}│${NC}"
    echo -e "${BLU}│${NC} ${YLW}🛰️  HYPERLATTICE :${NC} ${WHT}13/13 NODES - LOCKED${NC}            ${BLU}│${NC}"
    echo -e "${BLU}│${NC} ${PUR}🏰 ANCHOR      :${NC} ${WHT}41.2Hz (E1) SYMMETRY-LOCK${NC}       ${BLU}│${NC}"
    echo -e "${BLU}├────────────────────────────────────────────────────────────┤${NC}"
    echo -e "${BLU}│${NC} ${RED}⚖️  Ω STATUS    :${NC} ${WHT}34.8 (STATIONARY-FRAME)${NC} ${RED}🕵️⚖️🚨${NC}       ${BLU}│${NC}"
    echo -e "${BLU}└────────────────────────────────────────────────────────────┘${NC}"
    
    # Move cursor to Row 11 to allow the dynamic line to live there
    echo -ne "\033[11;1H"
    
    sleep 0.5
done
