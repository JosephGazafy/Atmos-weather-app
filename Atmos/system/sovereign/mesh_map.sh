#!/bin/bash
# ATMOS CORE v3.0 - LOCAL MESH SUMMARY
./logo.sh
echo -e "\e[1;36m🗺️  CURRENT MESH TOPOLOGY\e[0m"
echo "----------------------------------------------------"
echo "Active Anchor: US-East (1.1.1.1)"
echo "Local Node ID: $(md5sum <<< "$(hostname)" | cut -c 1-8)"
echo "----------------------------------------------------"
echo "Check your Discord Webhook for the full peer list."

