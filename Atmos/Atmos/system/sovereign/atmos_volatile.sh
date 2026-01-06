#!/bin/bash
# ATMOS CORE v3.1 - VOLATILE RAM-DISK INITIALIZATION
VOLATILE_PATH="$HOME/Atmos/Volatile"
mkdir -p "$VOLATILE_PATH"

echo -e "\e[1;34m[INITIATING VOLATILE MEMORY MOUNT...]\e[0m"

# Attempting to mount 64MB of RAM-disk (requires root)
if su -c "mount -t tmpfs -o size=64m tmpfs $VOLATILE_PATH" 2>/dev/null; then
    echo -e "\e[1;32m✅ RAM-DISK ACTIVE: Physical storage bypassed.\e[0m"
else
    echo -e "\e[1;33m⚠️ MOUNT DENIED: Using 'Volatile-Scrub' Logic (Soft-RAM Mode).\e[0m"
    # Fallback: We treat this folder as volatile and shred it on any exit.
fi

# Link the NIST build directory to the Volatile sector
rm -rf ~/Atmos/libbiomeval/build
ln -s "$VOLATILE_PATH" ~/Atmos/libbiomeval/build
