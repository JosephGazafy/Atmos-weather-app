#!/bin/bash
# ATMOS CORE v3.0 - VAULT PURGE PROTOCOL
VAULT_DIR="$HOME/Atmos/Vault"
RETENTION_DAYS=90

echo "[$(date)] Scanning Vault for archives older than $RETENTION_DAYS days..."

# Find and delete gpg archives older than the threshold
find "$VAULT_DIR" -name "*.gpg" -type f -mtime +$RETENTION_DAYS -delete

echo "[$(date)] Vault cleanup complete. Storage optimized."
