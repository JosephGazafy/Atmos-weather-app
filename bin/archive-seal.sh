#!/bin/bash
# ATMOS ARCHIVE-SEAL (v46.1)
LOG_FILE=~/Atmos-Engine/vault/lattice_philosophy.log
ENC_FILE=~/Atmos-Engine/vault/lattice_philosophy.log.enc
KEY_FILE=~/Atmos-Engine/vault/.inner_gate

# Ensure an Inner Key exists
if [ ! -f "$KEY_FILE" ]; then
    echo "Creating Inner Gate Key..."
    openssl rand -base64 32 > "$KEY_FILE"
    chmod 600 "$KEY_FILE"
fi

# Encrypt the log
openssl enc -aes-256-cbc -salt -in "$LOG_FILE" -out "$ENC_FILE" -pass file:"$KEY_FILE" -pbkdf2

# Verify and Shred the plaintext
if [ -f "$ENC_FILE" ]; then
    rm "$LOG_FILE"
    touch "$LOG_FILE"
    echo "[$(date)] ARCHIVE SEALED: Noumenal state active." >> "$LOG_FILE"
fi

echo -e "\033[1;35m>> ARCHIVE SEALED WITH INNER GATE KEY <<\033[0m"
