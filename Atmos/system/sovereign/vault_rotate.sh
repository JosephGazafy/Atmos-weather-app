#!/bin/bash
# ATMOS CORE v3.0 - SECURE LOG ROTATION
TIMESTAMP=$(date +"%Y-%m-%d")
LOG_DIR="$HOME/Atmos/logs"
VAULT_DIR="$HOME/Atmos/Vault"

# Ensure vault exists
mkdir -p "$VAULT_DIR"

echo "[$(date)] Initiating Friday Vault Rotation..."

# Find all .log files, compress, and encrypt
for logfile in "$LOG_DIR"/*.log; do
    if [ -f "$logfile" ]; then
        BASENAME=$(basename "$logfile")
        # Encrypt log with symmetric key (you will be prompted for a passphrase once)
        # Note: In a full-auto setup, use --passphrase-file if preferred.
        gpg --batch --yes --symmetric --cipher-algo AES256 \
            --output "$VAULT_DIR/$BASENAME.$TIMESTAMP.gpg" "$logfile"
        
        # Clear the original log to restart tracking (Sovereign Clean)
        cat /dev/null > "$logfile"
    fi
done

echo "[$(date)] US-East Mesh Logs rotated to Vault."


