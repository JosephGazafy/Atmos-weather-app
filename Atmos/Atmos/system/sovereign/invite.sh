#!/bin/bash
# ATMOS CORE v3.0 - FAIL-SAFE INVITATION
./logo.sh

INVITE_URL="https://github.com/JosephGazafy/Atmos"
INVITE_CMD="pkg install curl -y && curl -LO $INVITE_URL/raw/main/update.sh && chmod +x update.sh && ./update.sh"

echo -e "\e[1;35m📡 GENERATING MESH INVITATION...\e[0m"

# TRY 1: QR CODE (High-Visibility Terminal Render)
if command -v qrencode &> /dev/null; then
    echo "$INVITE_CMD" | qrencode -t ANSI256
    echo -e "\e[1;32m✅ QR Code Generated.\e[0m"
else
    # TRY 2: PHONE NOTIFICATION (Fallback A)
    termux-notification --title "🔗 ATMOS INVITE READY" --content "Tap to copy the join command." --button1 "COPY" --button1-action "termux-clipboard-set $INVITE_CMD"
    
    # TRY 3: URL POPUP (Fallback B)
    termux-open-url "$INVITE_URL"
    echo -e "\e[1;33m⚠️ QR Engine Missing. Fallbacks Engaged.\e[0m"
fi

