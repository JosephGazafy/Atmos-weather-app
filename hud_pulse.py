import time, sys, random, math

def animate_stationary():
    sys.stdout.write("\033[?25l") 
    print("\n\n\n") 
    start_time = time.time()
    
    try:
        while True:
            elapsed = time.time() - start_time
            # Modeling Manipulation Intensity (M_int)
            m_int = random.uniform(0.1, 2.5) # Normal state
            jitter = "".join(random.choice([" ", "▂", "▃", "▄", "▅", "▆", "▇", "█"]) for _ in range(12))
            
            sys.stdout.write("\033[4A")
            # Layer 1: FIELD (Acoustic/Semantic)
            sys.stdout.write(f"\r  \033[1;36m📡 FIELD:\033[0m    [MIC: ACTIVE] [M_int: {m_int:.2f}] \033[K\n")
            # Layer 2: ACUITY (Epistemic Shield)
            sys.stdout.write(f"\r  \033[1;35m🧠 ACUITY:\033[0m   [TRUTH: BIT-PERFECT] [GASLIGHT-FILTER: ON] \033[K\n")
            # Layer 3: BLOWBACK (Adversarial RST)
            sys.stdout.write(f"\r  \033[1;31m🪃 BLOWBACK:\033[0m [RST: STANDBY] [BULLY-DETECT: ENABLED] \033[K\n")
            # Layer 4: JOULE (Validation-Dissonance)
            sys.stdout.write(f"\r  \033[1;33m⚡ JOULE:\033[0m    [{jitter}] [D_ego: NULL] \033[K\n")
            
            sys.stdout.flush()
            time.sleep(0.08)
    except KeyboardInterrupt:
        sys.stdout.write("\033[?25h\n")

if __name__ == "__main__":
    animate_stationary()
