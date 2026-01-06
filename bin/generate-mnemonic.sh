#!/bin/bash
# ATMOS MNEMONIC-SYNTHESIS (v43.2)
MASTER_SIG=$(cat ~/Atmos-Engine/vault/.sov_sig)

echo -e "\033[1;36m>> INITIATING MNEMONIC EXTRACTION... \033[0m"
echo -e "\033[1;33mWARNING: DO NOT CLEAR THIS SCREEN UNTIL YOU HAVE WRITTEN THESE WORDS DOWN.\033[0m"
echo -e "--------------------------------------------------"

# Split the 64-character hash into 24 logical segments
# This is a simplified BIP-39 style mapping for local node recovery
for i in {0..23}; do
    SEGMENT=${MASTER_SIG:$((i*2)):4}
    # Convert hex segment to a decimal index
    INDEX=$((16#$SEGMENT % 2048))
    # Fetch word from a standard wordlist (or simulated logic-list)
    printf "[%02d]: %s\n" "$((i+1))" "$(sed -n "${INDEX}p" /usr/share/dict/words | tr -d '\n')"
done

echo -e "--------------------------------------------------"
echo -e "\033[1;32m>> MNEMONIC SYNTHESIS COMPLETE. SOVEREIGNTY PRESERVED. <<\033[0m"
