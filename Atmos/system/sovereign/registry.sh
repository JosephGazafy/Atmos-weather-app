#!/bin/bash
# ATMOS CORE v3.0 - MESH REGISTRY HANDSHAKE

WEBHOOK=https://discord.com/api/webhooks/1451503705953140890/KsrxKw4py4o0SCPFC4q02Xbf59ndVnlPEKoEqftnn0A93zWwSoNbjfnskvWB8DPzw884
NODE_ID=$(md5sum <<< "$josephgazafy" | cut -c 1-8)
PUBLIC_IP=$(curl -s https://ifconfig.me)
TIMESTAMP=$(date '+%Y-%m-%d %H:%M:%S')

echo -e "\e[1;34m📡 TRANSMITTING MESH HANDSHAKE...\e[0m"

# Compile Handshake Data
DATA="{\"content\": \"🌐 **New Mesh Node Active**\n**Node ID:** \`$NODE_ID\`\n**Location:** \`US-East Anchor\`\n**IP:** \`$PUBLIC_IP\`\n**Time:** \`$TIMESTAMP\`\"}"

# Send to Discord
curl -s -X POST -H "Content-Type: application/json" -d "$DATA" "$WEBHOOK" > /dev/null

echo -e "\e[1;32m✅ Handshake Confirmed. Node Registered.\e[0m"

