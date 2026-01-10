import time, sys, random, math

def animate_stationary():
    sys.stdout.write("\033[?25l") 
    print("\n\n\n") 
    start_time = time.time()
    
    try:
        while True:
            elapsed = time.time() - start_time
            # Modeling the Logic-RST state
            rst_armed = "TRUE" if random.random() > 0.1 else "TRIGGERED"
            jitter = "".join(random.choice([" ", "▂", "▃", "▄", "▅", "▆", "▇", "█"]) for _ in range(12))
            
            sys.stdout.write("\033[4A")
            # Layer 1: FIELD (Acoustic/Sovereign)
            sys.stdout.write(f"\r  \033[1;36m📡 FIELD:\033[0m    [MIC: ARMED] [TRUTH-ANCHOR: LOCKED] \033[K\n")
            # Layer 2: ACUITY (Logic Breaker)
            sys.stdout.write(f"\r  \033[1;35m🧠 ACUITY:\033[0m   [GASLIGHT-FILTER: ACTIVE] [RST: {rst_armed}] \033[K\n")
            # Layer 3: BLOWBACK (Kinetic Comeback)
            sys.stdout.write(f"\r  \033[1;31m🪃 BLOWBACK:\033[0m [COMEBACK-LIB: v462] [WIT: ENABLED] \033[K\n")
            # Layer 4: JOULE (Validation-Dissonance)
            sys.stdout.write(f"\r  \033[1;33m⚡ JOULE:\033[0m    [{jitter}] [D_ego: NULL] \033[K\n")
            
            sys.stdout.flush()
            time.sleep(0.08)
    except KeyboardInterrupt:
        sys.stdout.write("\033[?25h\n")

if __name__ == "__main__":
    animate_stationary()
