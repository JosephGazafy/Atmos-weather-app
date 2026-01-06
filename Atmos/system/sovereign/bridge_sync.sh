#!/bin/bash
# ATMOS CORE v3.1 - HARDWARE INTEROP BRIDGE
SOURCE_DIR="$HOME/Atmos"
TARGET_DIR="/sdcard/Android/data/org.sovereign.mesh.atmos/files/logic"

echo -e "\e[1;34m[*] ESTABLISHING HARDWARE-TO-LOGIC BRIDGE...\e[0m"

# Files required by the C++ Sentinel for response actions
SCRIPTS=("slough_all.sh" "stability_test.sh" "heartbeat_jitter.sh")

for script in "${SCRIPTS[@]}"; do
    if [ -f "$SOURCE_DIR/$script" ]; then
        cp "$SOURCE_DIR/$script" "$TARGET_DIR/"
        # Ensure the script remains executable in the shared space
        chmod +x "$TARGET_DIR/$script"
        echo -e "\e[1;32m[✓] Linked: $script -> Sentinel Bridge\e[0m"
    else
        echo -e "\e[1;31m[!] Missing: $script\e[0m"
    fi
done

echo -e "\e[1;36m[*] BRIDGE SYNCHRONIZED. NATIVE SENTINEL IS NOW ARMED.\e[0m"

