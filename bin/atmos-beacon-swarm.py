#!/usr/bin/env python3
import socket, threading, subprocess, time

# FREQUENCY MAP
# 8000-8049: Sentry (Low C - 130.81Hz)
# 8050-8099: Logic (Mid E - 329.63Hz)
# 8100-8149: Swarm (Mid G - 392.00Hz)
# 8150-8200: Core (High C - 523.25Hz)

def play_live_note(port):
    freq = 130.81
    if 8050 <= port <= 8099: freq = 329.63
    elif 8100 <= port <= 8149: freq = 392.00
    elif 8150 <= port <= 8200: freq = 523.25
    
    # Trigger Haptic & Acoustic in parallel
    try:
        # Acoustic Synthesis (Duration 0.1s)
        subprocess.Popen(["play", "-q", "-n", "synth", "0.1", "sine", str(freq)], 
                        stdout=subprocess.DEVNULL, stderr=subprocess.DEVNULL)
        # Haptic Vibration (Duration 50ms)
        subprocess.run(["termux-vibrate", "-d", "50"], capture_output=True)
    except:
        # Fallback to terminal bell if sox/play is not installed
        print("\a", end="", flush=True)

def handle_client(client_socket, addr, port):
    try:
        play_live_note(port)
        cmd = "python3 bin/atmos-maxim.py --random"
        maxim = subprocess.check_output(cmd, shell=True).decode().strip()
        payload = f"\n[ATMOS-ANTHEM-LIVE]\nMAXIM: {maxim}\n"
        client_socket.send(payload.encode())
        client_socket.close()
        with open("sovereign_log.txt", "a") as f:
            f.write(f"🎵 [LIVE-ANTHEM] Intercept at {port} - Frequency Triggered\n")
    except: pass

def start_node(port):
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    try:
        server.bind(('0.0.0.0', port))
        server.listen(5)
        while True:
            client, addr = server.accept()
            threading.Thread(target=handle_client, args=(client, addr, port)).start()
    except: pass

if __name__ == "__main__":
    print("🎼 [ATMOS] LIVE ANTHEM SWARM ACTIVE (8000-8200)")
    for port in range(8000, 8201):
        threading.Thread(target=start_node, args=(port,), daemon=True).start()
    while True: time.sleep(10)
