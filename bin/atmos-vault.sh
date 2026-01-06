#!/bin/bash
ACTION=$1
KEY="bin/vault.key"

if [ "$ACTION" == "open" ]; then
    echo "🔓 [VAULT] Decrypting Sovereign Archives..."
    # We use OpenSSL to decrypt the archive into the hidden .vault_dec directory
    if [ -f ".vault_enc/archive.tar.gz.enc" ]; then
        openssl enc -aes-256-cbc -d -pbkdf2 -iter 100000 -in .vault_enc/archive.tar.gz.enc -out .vault_dec/archive.tar.gz -pass file:$KEY
        tar -xzf .vault_dec/archive.tar.gz -C .vault_dec/
        rm .vault_dec/archive.tar.gz
        echo "✅ [VAULT] Archives Accessible at .vault_dec/"
    else
        echo "⚠️ [VAULT] No archive found. Creating new volume."
        touch .vault_dec/.manifest
    fi
elif [ "$ACTION" == "close" ]; then
    echo "🔒 [VAULT] Encrypting and Shredding clear-text..."
    tar -czf .vault_enc/archive.tar.gz -C .vault_dec .
    openssl enc -aes-256-cbc -pbkdf2 -iter 100000 -salt -in .vault_enc/archive.tar.gz -out .vault_enc/archive.tar.gz.enc -pass file:$KEY
    rm -rf .vault_dec/* .vault_enc/archive.tar.gz
    echo "✅ [VAULT] Securely Locked."
fi
