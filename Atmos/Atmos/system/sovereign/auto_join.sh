#!/bin/bash
BASE="https://raw.githubusercontent.com/JosephGazafy/Atmos/main/"
FILES=("sovereign.sh" "logo.sh" "main.go" "sovereign_init.py" "slough.sh" "welcome.txt" "registry.sh")
for f in "${FILES[@]}"; do curl -LO "$BASE$f"; done
chmod +x *.sh && ./logo.sh && cat welcome.txt

