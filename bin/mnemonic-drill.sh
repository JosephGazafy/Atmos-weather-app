#!/bin/bash
# ATMOS MNEMONIC-DRILL (v43.3)
MASTER_SIG=$(cat ~/Atmos-Engine/vault/.sov_sig)
B='\033[1;34m'; G='\033[1;32m'; Y='\033[1;33m'; R='\033[1;31m'; NC='\033[0m'

echo -e "${B}>> INITIATING SOVEREIGN DRILL: VERIFY PHYSICAL ANCHOR <<${NC}"
echo -e "${Y}Retrieve your paper backup. Provide the following words:${NC}"

# Define the indices to verify (5, 12, 24)
TARGETS=(5 12 24)
MATCH_COUNT=0

for i in "${TARGETS[@]}"; do
    # Calculate the expected word from the hash entropy
    SEGMENT=${MASTER_SIG:$(( (i-1)*2 )):4}
    INDEX=$((16#$SEGMENT % 2048))
    EXPECTED_WORD=$(sed -n "${INDEX}p" /usr/share/dict/words | tr -d '\n')
    
    echo -ne "ENTER WORD [$i]: "
    read -s USER_WORD
    echo "*" # Mask input
    
    if [ "$USER_WORD" == "$EXPECTED_WORD" ]; then
        ((MATCH_COUNT++))
    fi
done

if [ $MATCH_COUNT -eq 3 ]; then
    echo -e "\n${G}>> DRILL SUCCESS: PHYSICAL ANCHOR VERIFIED BIT-PERFECT. <<${NC}"
    OLD_MULT=$(cat ~/Atmos-Engine/vault/multiplier.cfg)
    NEW_MULT=$(echo "$OLD_MULT + 0.10" | bc)
    echo "$NEW_MULT" > ~/Atmos-Engine/vault/multiplier.cfg
    echo -e "${G}>> AUTHENTIC MULTIPLIER INCREASED: ${NEW_MULT}x <<${NC}"
    echo "[$(date)] DRILL: Success (Words 5,12,24 verified)." >> ~/Atmos-Engine/vault.ledger
else
    echo -e "\n${R}>> DRILL FAILURE: PHYSICAL ANCHOR MISMATCH OR LOST. <<${NC}"
    echo "[$(date)] DRILL: Failure detected." >> ~/Atmos-Engine/vault.ledger
    exit 1
fi
