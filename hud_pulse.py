import time, sys, random, math

def animate_stationary():
    bars = [" ", "▂", "▃", "▄", "▅", "▆", "▇", "█"]
    sys.stdout.write("\033[?25l") # Hide Cursor
    print("\n\n\n") # Void setup
    
    start_time = time.time()
    
    try:
        while True:
            elapsed = time.time() - start_time
            # Modeling the 7.83Hz Schumann Resonance + 0.005 Decay
            schumann = 7.83 + (0.1 * math.sin(elapsed))
            cool_factor = 100.0 * math.exp(-0.005 * (elapsed % 3600))
            jitter = "".join(random.choice(bars) for _ in range(12))
            
            # Absolute Positioning: Up 4 lines, start of line, clear line
            sys.stdout.write("\033[4A")
            
            # Layer 1: FIELD (SHAZAM / MXene)
            sys.stdout.write(f"\r  \033[1;36m📡 FIELD:\033[0m    [SATELLITE: SYNC] [MXene: >50dB] \033[K\n")
            
            # Layer 2: ACUITY (LyMOI / Bodily Hertz)
            sys.stdout.write(f"\r  \033[1;35m🧠 ACUITY:\033[0m   [COOLING: {cool_factor:.2f}%] [7.83Hz: SYNC] \033[K\n")
            
            # Layer 3: BLOWBACK (Joule Thief / RST)
            sys.stdout.write(f"\r  \033[1;31m🪃 BLOWBACK:\033[0m [RST: ARMED] [SHIELD: ACTIVE] \033[K\n")
            
            # Layer 4: JOULE (Metabolic / $65,737.61)
            sys.stdout.write(f"\r  \033[1;33m⚡ JOULE:\033[0m    [{jitter}] [η: 0.39] [$65,737.61] \033[K\n")
            
            sys.stdout.flush()
            time.sleep(0.08)
    except KeyboardInterrupt:
        sys.stdout.write("\033[?25h\n")

if __name__ == "__main__":
    animate_stationary()
