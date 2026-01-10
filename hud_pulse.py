import time, sys, random, math

def animate_stationary():
    sys.stdout.write("\033[?25l") 
    print("\n\n\n") 
    start_time = time.time()
    
    try:
        while True:
            elapsed = time.time() - start_time
            # Modeling PSI-Spike & Civil Rights Probability
            p_cr = 0.88 + (0.04 * math.sin(elapsed * 0.15))
            jitter = "".join(random.choice([" ", "▂", "▃", "▄", "▅", "▆", "▇", "█"]) for _ in range(12))
            
            sys.stdout.write("\033[4A")
            # Layer 1: FIELD (Geopolitical/Rights)
            sys.stdout.write(f"\r  \033[1;36m📡 FIELD:\033[0m    [CIVIL-RIGHTS: ERODING] [P_cr: {p_cr:.2%}] \033[K\n")
            # Layer 2: ACUITY (Jacobian Stability)
            sys.stdout.write(f"\r  \033[1;35m🧠 ACUITY:\033[0m   [λ_max: +0.142] [UNSTABLE: ACTIVE] \033[K\n")
            # Layer 3: BLOWBACK (Kinetic Recoupment)
            sys.stdout.write(f"\r  \033[1;31m🪃 BLOWBACK:\033[0m [RST: ARMED] [PSI-SPIKE: DETECTED] \033[K\n")
            # Layer 4: JOULE (Thermodynamic Equity)
            sys.stdout.write(f"\r  \033[1;33m⚡ JOULE:\033[0m    [{jitter}] [$65,737.61: ANCHORED] \033[K\n")
            
            sys.stdout.flush()
            time.sleep(0.08)
    except KeyboardInterrupt:
        sys.stdout.write("\033[?25h\n")

if __name__ == "__main__":
    animate_stationary()
