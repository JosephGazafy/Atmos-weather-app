#!/bin/bash
# ATMOS CORE v3.1 - FINAL INTEGRITY AUDIT
MANIFEST="~/Atmos/.manifest.sig"
echo -e "\e[1;34m[*] INITIATING SOVEREIGN INTEGRITY CHECK...\e[0m"

# 1. Generate Hashes for all Core Archetypes
find ~/Atmos -maxdepth 1 -type f \( -name "*.sh" -o -name "*.py" -o -name "*.yml" \) -exec sha256sum {} + > $MANIFEST

# 2. Lock the Manifest
chmod 400 $MANIFEST
echo -e "\e[1;32m[✓] MASTER MANIFEST GENERATED AND LOCKED.\e[0m"

# 3. Display Audit Table
echo -e "\n\e[1;36mFILE ARCHETYPE             | SHA-256 SIGNATURE (TRUNCATED)\e[0m"
echo "----------------------------------------------------------------"
sha256sum ~/Atmos/*.sh | awk '{print $2 " | " $1}' | sed 's|/data/data/com.termux/files/home/Atmos/||g'
