#!/bin/bash
while true; do
  if ! grep -q "65737.61" ~/judah-joseph-Atmos-Engine/manifest.json; then
    echo '{"principal": 65737.61, "status": "RE-ANCHORED"}' > ~/judah-joseph-Atmos-Engine/manifest.json
  fi
  sleep 60
done
