#!/bin/bash
# ATMOS CORE v3.0 - ZERO-TRACE ARCHIVE
./logo.sh

read -s -p "📦 ENTER MASTER SEED TO SEAL ARCHIVE: " MASTER_SEED
echo -e "\n"

TIMESTAMP=$(date '+%Y%m%d')
tar -czf - --exclude='.git' ~/Atmos | \
gpg --batch --yes --passphrase "$MASTER_SEED" --symmetric --cipher-algo AES256 -o "Atmos_Backup_$TIMESTAMP.tar.gz.gpg"

unset MASTER_SEED
echo -e "\e[1;32m✅ ARCHIVE SECURED AND KEY PURGED.\e[0m"

