#!/bin/bash
# ATMOS CORE v3.0 - PORT AUDIT
echo -e "\e[1;33m🔍 SCANNING LOCAL INTERFACES...\e[0m"
# List all listening ports without external tools
netstat -tuln | grep LISTEN
echo "----------------------------------------------------"
echo "⚠️  NOTE: Port 8022 is standard for SSH. If you see others,"
echo "ensure they are authorized or use 'pkill' to close them."

