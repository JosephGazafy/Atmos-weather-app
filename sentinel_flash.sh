#!/bin/bash
# High-contrast visual strobe for 10 seconds
for i in {1..40}
do
    echo -e "\033[?5h" # Screen Inverse On
    sleep 0.1
    echo -e "\033[?5l" # Screen Inverse Off
    sleep 0.1
done
echo "🚨 [SENTINEL] HEARTBEAT WINDOW IS OPEN. RESET REQUIRED."
