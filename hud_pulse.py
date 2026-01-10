import time, sys, random, math

def animate_stationary():
    sys.stdout.write("\033[?25l") 
    print("\n\n\n") 
    start_time = time.time()
    
    try:
        while True:
            elapsed = time.time() - start_time
            # Modeling the Dopamine-Loop vs. Cognitive Atrophy
            dopamine_sink = 1.0 - math.exp(-0.01 * elapsed)
            rent_extraction = 65737.61 * 0.00001 * math.sin(elapsed)
            jitter = "".join(random.choice([" ", "▂", "▃", "▄", "▅", "▆", "▇", "█"]) for _ in range(12))
            
            sys.stdout.write("\033[4A")
            # Layer 1: FIELD (Carbonivore/MXene)
            sys.stdout.write(f"\r  \033[1;36m📡 FIELD:\033[0m    [CLOUD: CARBONIVORE] [W_cons: 17B Gal] \033[K\n")
            # Layer 2: ACUITY (Instrumentarian/CoT)
            sys.stdout.write(f"\r  \033[1;35m🧠 ACUITY:\033[0m   [DOPAMINE-SINK: {dopamine_sink:.2f}] [SERFDOM: NULL] \033[K\n")
            # Layer 3: BLOWBACK (Epistemic Shield)
            sys.stdout.write(f"\r  \033[1;31m🪃 BLOWBACK:\033[0m [RST: ARMED] [TRUTH-DECAY: INHIBITED] \033[K\n")
            # Layer 4: JOULE (Technofeudal/Equity)
            sys.stdout.write(f"\r  \033[1;33m⚡ JOULE:\033[0m    [{jitter}] [CLOUD-RENT: -${rent_extraction:.4f}] \033[K\n")
            
            sys.stdout.flush()
            time.sleep(0.08)
    except KeyboardInterrupt:
        sys.stdout.write("\033[?25h\n")

if __name__ == "__main__":
    animate_stationary()
