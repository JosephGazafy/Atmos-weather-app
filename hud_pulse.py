import time, sys, random, math

def animate_stationary():
    sys.stdout.write("\033[?25l") 
    print("\n\n\n") 
    start_time = time.time()
    
    try:
        while True:
            elapsed = time.time() - start_time
            # PSI Fluctuates based on "Geopolitical Disintegration"
            psi = 1.071 + (0.05 * math.sin(elapsed * 0.1))
            jitter = "".join(random.choice([" ", "▂", "▃", "▄", "▅", "▆", "▇", "█"]) for _ in range(12))
            
            sys.stdout.write("\033[4A")
            # Layer 1: FIELD (Geopolitical)
            sys.stdout.write(f"\r  \033[1;36m📡 FIELD:\033[0m    [ORDER: DISSOLVING] [PSI: {psi:.3f}] \033[K\n")
            # Layer 2: ACUITY (Epistemic)
            sys.stdout.write(f"\r  \033[1;35m🧠 ACUITY:\033[0m   [TRUTH: FRACTURED] [LIAR-DIVIDEND: ON] \033[K\n")
            # Layer 3: BLOWBACK (Structural)
            sys.stdout.write(f"\r  \033[1;31m🪃 BLOWBACK:\033[0m [RST: ARMED] [ELITE-CONFLICT: HIGH] \033[K\n")
            # Layer 4: JOULE (Economic)
            sys.stdout.write(f"\r  \033[1;33m⚡ JOULE:\033[0m    [{jitter}] [APARTHEID-INDEX: 0.9] \033[K\n")
            
            sys.stdout.flush()
            time.sleep(0.08)
    except KeyboardInterrupt:
        sys.stdout.write("\033[?25h\n")

if __name__ == "__main__":
    animate_stationary()
