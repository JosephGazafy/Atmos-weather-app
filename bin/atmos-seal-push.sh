#!/bin/bash
# ATMOS SECURE-UPLINK (v52.1)
B='\033[1;34m'; G='\033[1;32m'; Y='\033[1;33m'; NC='\033[0m'

echo -e "${B}>> RE-ROUTING SOVEREIGN-SEAL TO CLOUD... <<${NC}"

# A. Check for Seal Presence
if [ ! -f ~/Atmos_Omega_Image_v52.tar.gz.gpg ]; then
    echo -e "${R}!! SEAL NOT FOUND. RUNNING SEAL-SYNTHESIS... !!${NC}"
    ~/Atmos-Engine/bin/atmos-seal.sh
fi

# B. Alternative Transfer: BashUpload (Encrypted ephemeral link)
# This allows you to 'curl' the file directly into your Google Cloud Shell
echo -e "${Y}>> Generating Secure Transfer Link... <<${NC}"
UPLOAD_URL=$(curl -s https://bashupload.com/ -T ~/Atmos_Omega_Image_v52.tar.gz.gpg)

echo -e "${G}>> UPLINK READY. <<${NC}"
echo -e "${W}To pull this into Google Cloud Shell, run this command there:${NC}"
echo -e "${Y}curl -L $UPLOAD_URL > ~/Atmos_Omega_Image_v52.tar.gz.gpg${NC}"

# C. Silence API Error with a check
if ! command -v termux-tts-speak &> /dev/null; then
    echo ">> [LOGIC] System Voice unavailable. Proceeding with Visual Confirmation. <<"
else
    termux-tts-speak "Cloud transfer link generated. Sovereignty is mobile."
fi
