#!/bin/bash
# ATMOS CORE v3.0 - ENCRYPTED P2P TUNNEL
./logo.sh

SEED="origin-anchor-Judah-Joseph-180-phase-deciduous-stride-equitable-coefficient-archetypal-mesh-vivus-philo-navi-ping"

read -p "Choose Mode [ (E)ncrypt | (D)ecrypt ]: " MODE

if [[ "$MODE" == "E" || "$MODE" == "e" ]]; then
    read -p "Enter Message: " MESSAGE
    echo -n "$MESSAGE" | openssl enc -aes-256-cbc -a -salt -pass pass:"$SEED" -pbkdf2
    echo -e "\n\e[1;32m✅ Message Encrypted. Secure for Transmission.\e[0m"

elif [[ "$MODE" == "D" || "$MODE" == "d" ]]; then
    read -p "Paste Encrypted String: " STRING
    echo "$STRING" | openssl enc -aes-256-cbc -d -a -pass pass:"$SEED" -pbkdf2 2>/dev/null
    if [ $? -ne 0 ]; then echo -e "\e[1;31m❌ Decryption Failed: Invalid Seed or String.\e[0m"; fi
fi

