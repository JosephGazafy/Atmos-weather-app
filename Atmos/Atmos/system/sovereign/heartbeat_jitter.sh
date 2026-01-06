#!/bin/bash
while true; do
#!/bin/bash
while true; do
#!/bin/bash
# ATMOS CORE v3.1 - HEARTBEAT JITTER (Visual Deterrent)

# Colors for the Cipher-Stream
GREEN='\e[1;32m'
CYAN='\e[1;36m'
NC='\e[0m' # No Color

clear
echo -e "${CYAN}[*] ATMOS HEARTBEAT JITTER ACTIVE [SOVEREIGNTY 120/100]${NC}"

while true; do
    # 1. Generate a "Cipher-Block"
    TIMESTAMP=$(date +"%Y-%m-%d %H:%M:%S")
    RAND_HASH=$(head /dev/urandom | tr -dc A-Za-z0-9 | head -c 48)
    LOAD=$(uptime | awk -F'load average:' '{ print $2 }')

    # 2. Display the Jitter
    echo -e "${GREEN}[$TIMESTAMP] PUSH -> ${NC}$RAND_HASH ${CYAN}LOAD:$LOAD${NC}"
    
    # 3. Randomize the interval (Jitter)
    # This prevents behavioral fingerprinting of the CPU cycles
    SLEEP_TIME=$((RANDOM % 300 + 30)) # Wait between 30 and 330 seconds
    sleep $SLEEP_TIME
done

done
done
