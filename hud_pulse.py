import time, sys, random, math

def animate_stationary():
    sys.stdout.write("\033[?25l") 
    print("\n\n\n") 
    start_time = time.time()
    
    try:
        while True:
            elapsed = time.time() - start_time
            # Modeling the Vampire Hum Cancellation
            noise_db = 42.5 + math.sin(elapsed * 0.5)
            anc_status = "LOCKED" if random.random() > 0.05 else "SYNCING"
            jitter = "".join(random.choice([" ", "▂", "▃", "▄", "▅", "▆", "▇", "█"]) for _ in range(12))
            
            sys.stdout.write("\033[4A")
            # Layer 1: FIELD (Acoustic Exhaust)
            sys.stdout.write(f"\r  \033[1;36m📡 FIELD:\033[0m    [EXHAUST: {noise_db:.1f}dB] [HUM: DETECTED] \033[K\n")
            # Layer 2: ACUITY (Sonic ANC)
            sys.stdout.write(f"\r  \033[1;35m🧠 ACUITY:\033[0m   [ANC: {anc_status}] [7.83Hz: PURIFIED] \033[K\n")
            # Layer 3: BLOWBACK (Sonic Deterrence)
            sys.stdout.write(f"\r  \033[1;31m🪃 BLOWBACK:\033[0m [RST: ARMED] [PHASE-INVERT: ON] \033[K\n")
            # Layer 4: JOULE (Thermal/Equity)
            sys.stdout.write(f"\r  \033[1;33m⚡ JOULE:\033[0m    [{jitter}] [$65,737.61: SILENT] \033[K\n")
            
            sys.stdout.flush()
            time.sleep(0.08)
    except KeyboardInterrupt:
        sys.stdout.write("\033[?25h\n")

if __name__ == "__main__":
    animate_stationary()
