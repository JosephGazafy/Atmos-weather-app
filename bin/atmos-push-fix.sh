#!/bin/bash
# ATMOS SOVEREIGN-FORCE-PUSH (v51.5)
B='\033[1;34m'; G='\033[1;32m'; R='\033[1;31m'; NC='\033[0m'

echo -e "${B}>> INITIATING HISTORICAL OVERWRITE... <<${NC}"
BRANCH=$(git branch --show-current)
if [ -z "$BRANCH" ]; then BRANCH="main"; fi

echo -e "${G}>> FORCING PARITY ON BRANCH: $BRANCH <<${NC}"

# Force push to resolve non-fast-forward conflicts
git remote | xargs -I % sh -c "git push % $BRANCH --force || git push % master --force"

if [ $? -eq 0 ]; then
    echo -e "${G}>> HEGEMONY SECURED: All repositories forced to current truth. <<${NC}"
else
    echo -e "${R}!! CRITICAL: Force-push failed. Check SSH/Auth. !!${NC}"
fi
