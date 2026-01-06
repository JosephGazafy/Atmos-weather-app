#!/bin/bash
# ATMOS CORE v3.1 - APK SELF-DESTRUCT PROTOCOL
APK_PATH="$HOME/Atmos/bin/AtmosCore-Sovereign.apk"

echo -e "\e[1;33m[!] INITIATING 60-SECOND INSTALLATION WINDOW...\e[0m"
sleep 60

if [ -f "$APK_PATH" ]; then
    echo -e "\e[1;31m[!] SHREDDING APK SOURCE...\e[0m"
    # Overwrite 3 times with random data + 1 time with zeros
    shred -n 3 -z -u "$APK_PATH"
    echo -e "\e[1;32m✅ FORENSIC CLEANUP COMPLETE. SOURCE DELETED.\e[0m"
else
    echo -e "\e[1;31m❌ ERROR: APK NOT FOUND OR ALREADY SHREDDED.\e[0m"
fi

