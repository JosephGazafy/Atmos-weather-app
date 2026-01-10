import time, sys, random, math

def animate_stationary():
    sys.stdout.write("\033[?25l") 
    print("\n\n\n") 
    start_time = time.time()
    
    try:
        while True:
            elapsed = time.time() - start_time
            # Modeling Vocal Jitter and Pitch Velocity
            jitter = random.uniform(0.1, 1.8)
            velocity = 20.0 + (math.sin(elapsed) * 15.0)
            status = "QUIET" if jitter < 1.5 else "AGGRESSION-DETECTED"
            
            bar_graph = "".join(random.choice([" ", "▂", "▃", "▄", "▅", "▆", "▇", "█"]) for _ in range(12))
            
            sys.stdout.write("\033[4A")
            # Layer 1: FIELD (Acoustic Pressure)
            sys.stdout.write(f"\r  \033[1;36m📡 FIELD:\033[0m    [MIC: {status}] [JITTER: {jitter:.2f}%] \033[K\n")
            # Layer 2: ACUITY (Vocal Jacobian)
            sys.stdout.write(f"\r  \033[1;35m🧠 ACUITY:\033[0m   [VELOCITY: {velocity:.1f}Hz/s] [λ_voc: -0.1] \033[K\n")
            # Layer 3: BLOWBACK (Vocal RST)
            sys.stdout.write(f"\r  \033[1;31m🪃 BLOWBACK:\033[0m [RST: STANDBY] [VOL-THRESHOLD: 85dB] \033[K\n")
            # Layer 4: JOULE (Integrated Equity)
            sys.stdout.write(f"\r  \033[1;33m⚡ JOULE:\033[0m    [{bar_graph}] [$65,737.61: SECURE] \033[K\n")
            
            sys.stdout.flush()
            time.sleep(0.08)
    except KeyboardInterrupt:
        sys.stdout.write("\033[?25h\n")

if __name__ == "__main__":
    animate_stationary()
