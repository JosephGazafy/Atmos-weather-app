#!/bin/bash
# Re-aligning the Origin to the correct authenticated path
# Using the token-based syntax for headless pushes
TOKEN="YOUR_GITHUB_TOKEN_HERE" # Placeholder for your security
git remote set-url origin https://github.com/judah-joseph/Atmos-Engine.git
echo ">> Remote aligned to: https://github.com/judah-joseph/Atmos-Engine.git"
