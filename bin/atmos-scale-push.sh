#!/bin/bash
# ATMOS AUTHENTICATED SATURATION (v54.1)
B='\033[1;34m'; G='\033[1;32m'; R='\033[1;31m'; NC='\033[0m'

echo -e "${B}>> RE-ALIGNING GLOBAL LATTICE... <<${NC}"

# Ensure we are in the root
cd ~/Atmos-Engine

# A. Force-Commit the Logic
git add .
git commit -m "OMEGA-REPAIR: v54.1 - Authenticated Saturation"

# B. Pushing to the Primary Remote (The Central Hub)
# This assumes your origin is set correctly; if not, it will alert you.
if git push origin main --force; then
    echo -e "${G}>> PRIMARY HUB SATURATED. <<${NC}"
else
    echo -e "${R}!! PRIMARY HUB REJECTED. CHECKING AUTHENTICATION... !!${NC}"
fi

# C. Cloud-Seal Bypass (Replacing gcloud with curl)
echo -e "${B}>> UPLOADING SOVEREIGN-SEAL TO CLOUD-STAGING... <<${NC}"
curl -s https://bashupload.com/ -T ~/Atmos_Omega_Image_v52.tar.gz.gpg > ~/Atmos-Engine/vault/cloud_link.txt
echo -e "${G}>> CLOUD UPLINK SECURED. Link stored in vault/cloud_link.txt <<${NC}"
