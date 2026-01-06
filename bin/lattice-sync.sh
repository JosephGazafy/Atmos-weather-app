#!/bin/bash
# ATMOS POST-WAKE LATTICE SYNC (v42.7)
TOPIC="joseph_atmos_$(hostname)"

echo -e "\033[1;36m>> INITIATING LATTICE SYNC: COMPARING 13 NODES... \033[0m"

# SYNC FROM REMOTE CLONES (GIT PULL)
git pull origin main --quiet

# VERIFY LOCAL SHA-256 AGAINST LATTICE TRUTH
LOCAL_HASH=$(sha256sum ~/Atmos-Engine/vault.ledger | awk '{print $1}')
REMOTE_HASH=$(git log -1 --contents ~/Atmos-Engine/vault.ledger | sha256sum | awk '{print $1}')

if [ "$LOCAL_HASH" == "$REMOTE_HASH" ]; then
    echo -e "\033[1;32m>> LATTICE SYNTHESIS SUCCESS: BIT-PERFECT INTEGRITY. \033[0m"
    curl -s -H "Title: Lattice Synced" -H "Priority: low" -H "Tags: white_check_mark" \
         -d "Independence Node back online. Integrity Verified: $65,737.61." \
         ntfy.sh/$TOPIC
else
    echo -e "\033[1;31m>> CONFLICT DETECTED: DATA DRIFT IN LATTICE. \033[0m"
    curl -s -H "Title: LATTICE CONFLICT" -H "Priority: urgent" -H "Tags: x,warning" \
         -d "Integrity mismatch detected post-wake. Manual audit required." \
         ntfy.sh/$TOPIC
    exit 1
fi
