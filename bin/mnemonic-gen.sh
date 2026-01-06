#!/bin/bash
# ATMOS PHILOSOPHICAL-MNEMONIC (v46.4)
# Goal: Re-seed the Philosophical Archive after a Salt-and-Burn event.

WORDS=(
    "sovereign" "independence" "dialectic" "entropy" 
    "noumenon" "authenticity" "parity" "lattice" 
    "principal" "missouri" "synthesis" "dasein"
    "joule" "harvest" "stasis" "logic" 
    "freedom" "vault" "guardian" "spirit"
)

echo -e "\033[1;36m>> GENERATING SOVEREIGN RECOVERY MNEMONIC... \033[0m"
echo -e "\033[1;33mWARNING: MEMORIZE THESE WORDS. DO NOT STORE DIGITALLY. \033[0m"
echo "--------------------------------------------------"

# Shuffle and pick 12
SELECTED=($(printf "%s\n" "${WORDS[@]}" | shuf -n 12))

for i in "${!SELECTED[@]}"; do
    printf "%2d: %-15s" "$((i+1))" "${SELECTED[$i]}"
    if [ $(( (i+1) % 3 )) -eq 0 ]; then echo ""; fi
done

echo "--------------------------------------------------"
echo -e "\033[1;32m>> MNEMONIC GENERATED. RESURRECTION PATH SECURED. <<\033[0m"
