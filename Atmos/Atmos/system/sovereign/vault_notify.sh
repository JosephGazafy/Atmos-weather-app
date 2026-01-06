#!/bin/bash
# ATMOS CORE v3.0 - ROTATION WATCHDOG

# Wait for the rotation script to finish
~/Atmos/vault_rotate.sh

if [ $? -eq 0 ]; then
    termux-notification --title "ATMOS: VAULT SYNC" \
                        --content "Friday Rotation Complete. AES-256 Encryption Verified." \
                        --id 101 --priority high \
                        --led-color 00FF00
else
    termux-notification --title "ATMOS: VAULT ERROR" \
                        --content "Sync Failed. Check ~/Atmos/logs/error.log" \
                        --id 101 --priority urgent \
                        --led-color FF0000
fi
