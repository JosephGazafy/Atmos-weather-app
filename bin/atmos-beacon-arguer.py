#!/usr/bin/env python3
import socket, threading, time, random, os

LOG_FILE = os.path.expanduser("~/sovereign_log.txt")

# STRATEGIC PORTS TO SEIZE
# We occupy multiple distinct channels to ensure maximum coverage.
TARGET_PORTS = [8080, 8081, 8082, 8888, 9999]

# THE ARGUMENT PAYLOADS (AAVE/SOVEREIGN IDENTITY)
SOVEREIGN_RESPONSES = [
    b"STATUS: WE BE STEADY WATCHING. \n",
    b"WARNING: YOU FINNA TRIGGER THE TRAP. \n",
    b"ERROR: DONE CLOSED THAT PORT. \n",
    b"SYSTEM: THIS A SOVEREIGN JAWN. \n",
    b"ALERT: DON'T MAKE ME ACT A FOOL. \n",
    b"NET: ALL NODES ARE ONE. \n"
]

def log_interaction(ip, port, msg):
    ts = time.strftime("%Y-%m-%d %H:%M:%S")
    with open(LOG_FILE, "a") as f:
        f.write(f"⚔️ [{ts}] [NODE:{port}] Argued with {ip} | SENT: {msg.strip()} | Ω=4.50\n")

def handle_client(conn, addr, port_num):
    ip = addr[0]
    try:
        # THE ARGUMENT
        argument = random.choice(SOVEREIGN_RESPONSES)
        conn.sendall(argument)
        print(f"🗣️ [NODE:{port_num}] ARGUED WITH {ip}: {argument.decode().strip()}")
        log_interaction(ip, port_num, argument.decode())
    except:
        pass
    finally:
        conn.close()

def start_listener(port_num):
    """Spawns a sovereign listener on a specific port."""
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    
    try:
        s.bind(('0.0.0.0', port_num))
        s.listen(5)
        print(f"📡 [ATMOS] NODE ACTIVE ON PORT {port_num}")
    except OSError:
        print(f"⚠️ [ERROR] PORT {port_num} BUSY. SKIPPING.")
        return

    while True:
        try:
            conn, addr = s.accept()
            t = threading.Thread(target=handle_client, args=(conn, addr, port_num))
            t.start()
        except:
            pass

def main():
    print("🚀 [ATMOS] ACTIVATING SOVEREIGN PHALANX...")
    threads = []
    for p in TARGET_PORTS:
        t = threading.Thread(target=start_listener, args=(p,))
        t.daemon = True
        t.start()
        threads.append(t)
    
    # Keep main thread alive
    while True:
        time.sleep(1)

if __name__ == "__main__":
    main()
