#!/bin/bash
# ATMOS CORE v3.1 - SLOUGH-ALL CIRCUIT BREAKER
echo -e "\e[1;31m[!] INITIATING TOTAL SLOUGH PROTOCOL...\e[0m"

# 1. Kill Active Defense Processes
# We target by string to ensure all associated threads are severed
pkill -f stability_test.sh
pkill -f main
pkill -f play
pkill -f kinetic_kill.sh

# 2. Shred Forensic Evidence
# Flags: -n 7 (Overwrites 7 times), -z (Final zero-fill), -u (Remove file)
SHRED_CMD="shred -n 7 -z -u"

# Target sensitive logs and temporary vaults
[ -f ~/Atmos/stability_log.txt ] && $SHRED_CMD ~/Atmos/stability_log.txt
[ -f ~/Atmos/sentinel_heartbeat.log ] && $SHRED_CMD ~/Atmos/sentinel_heartbeat.log
[ -d ~/Atmos/Vault/tmp ] && find ~/Atmos/Vault/tmp -type f -exec $SHRED_CMD {} +

# 3. Unmount RAM-Disks
# Forces an immediate drop of the encrypted memory segment
if mount | grep -q "$HOME/Atmos/ShadowMount"; then
    umount -l ~/Atmos/ShadowMount
    echo "✅ ShadowMount Unlinked."
fi

# 4. Clear Notification History
termux-notification --remove all
termux-toast -c "ATMOS: SLOUGH COMPLETE. NODE IS COLD."

