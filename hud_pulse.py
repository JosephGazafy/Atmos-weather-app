import time, sys, random, math

def animate_spectral_stack():
    bars = [" ", "▂", "▃", "▄", "▅", "▆", "▇", "█"]
    sys.stdout.write("\033[?25l") 
    print("\n\n\n") 
    
    start_time = time.time()
    
    try:
        while True:
            elapsed = time.time() - start_time
            jitter = "".join(random.choice(bars) for _ in range(12))
            
            # Simulate the U-Shaped Threshold Curve
            # Peak Sensitivity at 250Hz (Pacinian Corpuscle)
            acuity_hz = 250 + (50 * math.sin(elapsed))
            
            sys.stdout.write("\033[4A")
            # Layer 1: FIELD (Somatic Environment)
            sys.stdout.write(f"\r  \033[1;36m📡 FIELD:\033[0m    [WBV: 4-8Hz RES] [HAV: ACTIVE] \033[K\n")
            # Layer 2: ACUITY (Mechanoreceptor Tuning)
            sys.stdout.write(f"\r  \033[1;35m🧠 ACUITY:\033[0m   [PACINIAN: {acuity_hz:.2f}Hz] [η: 1.0] \033[K\n")
            # Layer 3: BLOWBACK (Threshold Defense)
            sys.stdout.write(f"\r  \033[1;31m🪃 BLOWBACK:\033[0m [RST: ARMED] [ISO-2631: STANDBY] \033[K\n")
            # Layer 4: JOULE (Integrated Analytics)
            sys.stdout.write(f"\r  \033[1;33m⚡ JOULE:\033[0m    [{jitter}] [r:0.00] \033[K\n")
            
            sys.stdout.flush()
            time.sleep(0.08)
    except KeyboardInterrupt:
        sys.stdout.write("\033[?25h\n")

if __name__ == "__main__":
    animate_spectral_stack()
