import time, sys, random, math

def animate_stationary():
    sys.stdout.write("\033[?25l") 
    print("\n\n\n") 
    start_time = time.time()
    
    try:
        while True:
            elapsed = time.time() - start_time
            # Modeling Alpha-Entrainment (8-10Hz)
            entrainment_hz = 8.5 + (0.5 * math.sin(elapsed * 0.1))
            jitter = "".join(random.choice([" ", "▂", "▃", "▄", "▅", "▆", "▇", "█"]) for _ in range(12))
            
            sys.stdout.write("\033[4A")
            # Layer 1: FIELD (Somatic/Acoustic)
            sys.stdout.write(f"\r  \033[1;36m📡 FIELD:\033[0m    [MIC: MONITORING] [ENTRAINMENT: ACTIVE] \033[K\n")
            # Layer 2: ACUITY (Alpha-Sync)
            sys.stdout.write(f"\r  \033[1;35m🧠 ACUITY:\033[0m   [TARGET: {entrainment_hz:.2f}Hz] [STATE: ALPHA] \033[K\n")
            # Layer 3: BLOWBACK (Sub-Zero RST)
            sys.stdout.write(f"\r  \033[1;31m🪃 BLOWBACK:\033[0m [VOICE: SUB-ZERO] [MODULATION: ON] \033[K\n")
            # Layer 4: JOULE (Biometric Equity)
            sys.stdout.write(f"\r  \033[1;33m⚡ JOULE:\033[0m    [{jitter}] [$65,737.61: BIT-PERFECT] \033[K\n")
            
            sys.stdout.flush()
            time.sleep(0.08)
    except KeyboardInterrupt:
        sys.stdout.write("\033[?25h\n")

if __name__ == "__main__":
    animate_stationary()
