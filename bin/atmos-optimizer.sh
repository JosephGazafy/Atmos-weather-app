#!/bin/bash
# ATMOS EVOLUTIONARY OPTIMIZER (v51.0)
# Purging legacy logs and re-aligning SHA-256 root
find ~/Atmos-Engine/history/ -type f -mtime +30 -delete
sha256sum ~/Atmos-Engine/vault.ledger > ~/Atmos-Engine/vault/root_parity.cfg
