#!/bin/bash
# ATMOS CORE v3.1 - SELF-HEALING BIOMETRIC GATE

SOURCE="~/Atmos/bio_verify.cpp"
BINARY="~/Atmos/bio_verify"

echo -e "\e[1;34m[SYSTEM]: Initializing Hardware Handshake...\e[0m"

# 1. Check if the binary exists
if [ ! -f "$BINARY" ]; then
    echo -e "\e[1;33m[!] WARNING: Biometric Engine missing. Initiating Emergency Rebuild...\e[0m"
    
    # Attempt to compile
    if g++ -std=c++17 "$SOURCE" -o "$BINARY" -I./include -L./lib -lbiomeval 2>/dev/null; then
        echo -e "\e[1;32m[✓] REBUILD SUCCESSFUL.\e[0m"
        chmod +x "$BINARY"
    else
        echo -e "\e[1;31m[!] CRITICAL: Rebuild Failed. Source corrupted or compiler missing.\e[0m"
        echo -e "\e[1;33mAttempting Emergency Bypass...\e[0m"
        bash ~/Atmos/emergency.sh
        exit 1
    fi
fi

# 2. Execute Verification
echo -e "\e[1;36m[AUTHENTICATING]: Awaiting Sovereign Signature...\e[0m"
"$BINARY"

if [ $? -eq 0 ]; then
    echo -e "\e[1;32m[ACCESS GRANTED]: Welcome, Operator.\e[0m"
    bash ~/Atmos/autopilot.sh
else
    echo -e "\e[1;31m[ACCESS DENIED]: Biometric Mismatch.\e[0m"
    exit 1
fi
