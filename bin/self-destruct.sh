#!/bin/bash
# ATMOS SELF-DESTRUCT (v46.2)
# Trigger: 5 failed attempts at Inner Gate decryption.
LOG_ENC=~/Atmos-Engine/vault/lattice_philosophy.log.enc
INNER_KEY=~/Atmos-Engine/vault/.inner_gate
FAIL_FILE=~/Atmos-Engine/vault/.inner_gate_fails

FAILS=$(cat "$FAIL_FILE")

if [ "$FAILS" -ge 5 ]; then
    echo -e "\033[1;31m!! [CRITICAL BREACH] !! INITIATING SALT-AND-BURN... \033[0m"
    
    # Securely shred the archive and the key
    [ -f "$LOG_ENC" ] && shred -u -n 7 -z "$LOG_ENC"
    [ -f "$INNER_KEY" ] && shred -u -n 7 -z "$INNER_KEY"
    
    # Notify Independence Node
    echo "[$(date)] SYSTEM_SELF_DESTRUCT: Philosophy Archive Erased." >> ~/Atmos-Engine/repair.log
    
    # Enter permanent lockdown of the Inner Gate logic
    echo "BURNED" > "$FAIL_FILE"
    
    # Notify mobile device
    TOPIC="joseph_atmos_$(hostname)"
    curl -s -H "Priority: urgent" -H "Tags: fire,skull" \
         -d "Inner Gate Compromised. Philosophy Archive has been destroyed." \
         ntfy.sh/$TOPIC
    exit 1
fi
