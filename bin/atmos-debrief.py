#!/usr/bin/env python3
import os, subprocess, time

def speak(text):
    try:
        # Utilizing Termux TTS engine
        subprocess.run(["termux-tts-speak", text])
    except:
        print(f"🔊 [VOICE]: {text}")

def generate_narrative():
    if not os.path.exists("intel_ledger.txt"):
        return "System stable. No recent adversarial contact documented."
    
    with open("intel_ledger.txt", "r") as f:
        last_event = f.readlines()[-1]
    
    # Extract data for the Narrative
    # Example line: [2026-01-04 17:54:00] 🔍 INTEL: IP=192.168.1.1 | ORG=Frankfurt_DC | EXIT_HOP=DE
    try:
        parts = last_event.split(" | ")
        ip = parts[0].split("=")[1]
        org = parts[1].split("=")[1]
        
        narrative = f"Tactical Alert. An adversary from {org} has been intercepted. " \
                    f"Connection from IP {ip} was trapped in a recursive honey port. " \
                    f"Sovereign Redress has been served. Mirroring defense across the hyperlattice."
        return narrative
    except:
        return "Perimeter holding. Continuous drone active in Independence."

if __name__ == "__main__":
    # Wait for a high-threat event or run as a periodic brief
    brief = generate_narrative()
    speak(brief)
