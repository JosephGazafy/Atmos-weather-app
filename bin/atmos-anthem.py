#!/usr/bin/env python3
import time, os, subprocess

LOG_FILE = "sovereign_log.txt"

# Frequency Map for the Anthem (Hz)
# Sentry (8000s) -> Low Bass (110Hz)
# Logic (8050s)  -> Mid Pulse (440Hz)
# Swarm (8100s)  -> High Chord (880Hz)
# Core (8150s)   -> Sovereign Siren (1760Hz)

def play_note(freq, duration):
    # Uses 'play' from the 'soaps' package or simple beep logic
    # If 'play' is missing, it defaults to a Terminal Bell sequence
    try:
        subprocess.run(["play", "-n", "synth", str(duration), "sine", str(freq)], 
                       capture_output=True, timeout=1)
    except:
        print("\a", end="", flush=True)

def compose_anthem():
    print("🎼 [ATMOS] COMPOSING SOVEREIGN ANTHEM FROM LIVE INTERCEPTS...")
    try:
        with open(LOG_FILE, "r") as f:
            lines = f.readlines()[-20:] # Analyze last 20 events
            for line in lines:
                if "8000" in line: play_note(110, 0.2)
                elif "8050" in line: play_note(440, 0.2)
                elif "8100" in line: play_note(880, 0.3)
                elif "8150" in line: play_note(1760, 0.5)
                time.sleep(0.1)
    except Exception as e:
        print(f"⚠️ [ANTHEM] Waiting for more data: {e}")

if __name__ == "__main__":
    compose_anthem()
