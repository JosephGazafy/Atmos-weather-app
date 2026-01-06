#!/bin/bash
# ATMOS CORE v3.0 - PEER STATUS MONITOR
./logo.sh
echo -e "\e[1;36m🌐 SCANNING MESH PEERS...\e[0m"
echo "----------------------------------------------------"

# Note: In a decentralized setup, this queries your local 
# handshake log or a shared registry file.
if [ -f ".mesh_peers" ]; then
    cat .mesh_peers | while read -r line; do
        NODE_ID=$(echo $line | cut -d',' -f1)
        LAST_SEEN=$(echo $line | cut -d',' -f2)
        echo -e "Node: \e[1;32m$NODE_ID\e[0m | Last Active: $LAST_SEEN"
    done
else
    echo "⚠️ No peers detected in local registry yet."
    echo "Direct a peer to scan your QR code to begin."
fi
echo "----------------------------------------------------"

