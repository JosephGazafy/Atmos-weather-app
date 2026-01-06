#!/bin/bash
echo -e "\e[1;36m📊 ATMOS STATUS\e[0m"
pgrep -f "alert_sentinel.sh" > /dev/null && echo "Sentinel: ONLINE" || echo "Sentinel: OFFLINE"
pgrep -f "scuttle.sh" > /dev/null && echo "Scuttle: ACTIVE" || echo "Scuttle: INACTIVE"
