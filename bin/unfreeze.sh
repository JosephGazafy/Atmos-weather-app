#!/bin/bash
# ATMOS SILENT-SENTRY & HONEY-POT (v43.1)
TOPIC="joseph_atmos_$(hostname)"

echo -ne "\033[1;33mENTER SOVEREIGN PASSPHRASE: \033[0m"
read -s PASSPHRASE
echo ""

# RECURSIVE HASHING (1,000 Cycles)
CURRENT_HASH=$(echo -n "$PASSPHRASE" | sha256sum | awk '{print $1}')
for i in {1..1000}; do
    CURRENT_HASH=$(echo -n "$CURRENT_HASH" | sha256sum | awk '{print $1}')
done

MASTER_SIG=$(cat ~/Atmos-Engine/vault/.sov_sig)
HONEY_SIG=$(cat ~/Atmos-Engine/vault/.honey_sig)

if [ "$CURRENT_HASH" == "$MASTER_SIG" ]; then
    rm ~/Atmos-Engine/vault/.lattice_lock
    echo -e "\033[1;32m>> SOVEREIGN KEY VERIFIED. LATTICE RESUMED. <<\033[0m"
    ~/Atmos-Engine/bin/recovery-audit.sh
elif [ "$CURRENT_HASH" == "$HONEY_SIG" ]; then
    # SILENT ALARM UPLINK
    curl -s -H "Title: !! HONEY-POT TRIGGERED !!" \
         -H "Priority: urgent" \
         -H "Tags: skull,fire,biohazard" \
         -d "Unauthentic Access Attempt in Independence. Scorched-Earth initiated. 5s to erasure." \
         ntfy.sh/$TOPIC
    
    echo -e "\033[1;31m>> HONEY-POT TRIGGERED. SILENT ALARM SENT. <<\033[0m"
    sleep 5
    
    # CALL THE SCORCHED-EARTH PROTOCOL
    ~/Atmos-Engine/bin/geo-check.sh force_wipe
else
    echo -e "\033[1;31m>> INVALID SIGNATURE. <<\033[0m"
    exit 1
fi
