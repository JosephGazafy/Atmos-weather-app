#!/bin/bash
# ATMOS CORE v3.0 - VAULT ROTATION PROTOCOL

VAULT=".atmos_vault"
MAX_SIZE=500  # Size in Kilobytes
BACKUP_LIMIT=3

if [ -f "$VAULT" ]; then
    SIZE=$(du -k "$VAULT" | cut -f1)
    
    if [ "$SIZE" -ge "$MAX_SIZE" ]; then
        echo "🔄 Vault size limit reached ($SIZE KB). Rotating logs..."
        
        # Cycle through existing backups (3 -> delete, 2 -> 3, 1 -> 2)
        rm -f "${VAULT}.3"
        [ -f "${VAULT}.2" ] && mv "${VAULT}.2" "${VAULT}.3"
        [ -f "${VAULT}.1" ] && mv "${VAULT}.1" "${VAULT}.2"
        mv "$VAULT" "${VAULT}.1"
        
        touch "$VAULT"
        chmod 400 "$VAULT"
        echo "✅ Rotation complete. New vault initialized."
    fi
fi

