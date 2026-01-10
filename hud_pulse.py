import time, sys, random, math

def animate_stationary():
    bars = [" ", "▂", "▃", "▄", "▅", "▆", "▇", "█"]
    sys.stdout.write("\033[?25l") 
    print("\n\n\n") 
    
    start_time = time.time()
    
    try:
        while True:
            # Temporal Decay (λ = 0.005) & Schumann Sync
            elapsed = time.time() - start_time
            cool_factor = 100.0 * math.exp(-0.005 * (elapsed % 3600))
            jitter = "".join(random.choice(bars) for _ in range(12))
            
            # Absolute Positioning: Move cursor UP 4 lines (Zero-Scroll)
            sys.stdout.write("\033[4A")
            
            # Layer 1: FIELD (Carbonivore/SHAZAM)
            sys.stdout.write(f"\r  \033[1;36m📡 FLEET:\033[0m    [NODES: 11] [SYNC: AUTHENTICATED] \033[K\n")
            # Layer 2: ACUITY (Instrumentarian/LyMOI)
            sys.stdout.write(f"\r  \033[1;35m🧠 ACUITY:\033[0m   [DOPAMINE-SINK: NULL] [7.83Hz: SYNC] \033[K\n")
            # Layer 3: BLOWBACK (Epistemic/RST)
            sys.stdout.write(f"\r  \033[1;31m🪃 BLOWBACK:\033[0m [RST: ARMED] [DE-FEUDAL: ON] \033[K\n")
            # Layer 4: JOULE (Technofeudal/Equity)
            sys.stdout.write(f"\r  \033[1;33m⚡ JOULE:\033[0m    [{jitter}] [EQUITY: $65,737.61] \033[K\n")
            
            sys.stdout.flush()
            time.sleep(0.08)
    except KeyboardInterrupt:
        sys.stdout.write("\033[?25h\n")

if __name__ == "__main__":
    animate_stationary()
