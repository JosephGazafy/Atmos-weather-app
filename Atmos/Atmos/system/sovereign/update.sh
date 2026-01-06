#!/bin/bash
# ATMOS CORE v3.0 - PEER INITIALIZATION
./logo.sh
echo -e "\e[1;33m🔄 SYNCING TO MASTER ANCHOR...\e[0m"

# Pull files...
# [Previous file sync logic here]

# THE HANDSHAKE TRIGGER
chmod +x registry.sh
./registry.sh

if [ -f "welcome.txt" ]; then
    cat welcome.txt
fi

