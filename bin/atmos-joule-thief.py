import sys
import time
import random

def run_single_line_engine():
    # Starting state for Independence Node
    joule_count = 58.30
    truth_freq = 41.2
    
    # ANSI Colors
    CYN = "\033[1;36m"
    YLW = "\033[1;33m"
    GRN = "\033[1;32m"
    NC  = "\033[0m"
    CLR = "\033[K" # Clear line to ensure no leftover characters

    try:
        while True:
            # Kinetic Growth
            joule_count += random.uniform(0.01, 0.05)
            bar_len = int((joule_count % 15) + 1)
            bar = "⚡" * bar_len
            
            # THE FIX: \r forces the cursor to the start of the current line
            # :<15 ensures the bar area has a fixed width to prevent jitter
            sys.stdout.write(f"\r{CYN}TRUTH: {truth_freq}Hz{NC} | {YLW}JOULES: [{bar:<15}]{NC} {GRN}{joule_count:.2f}J{NC}{CLR}")
            sys.stdout.flush()
            
            time.sleep(0.1)
    except KeyboardInterrupt:
        print("\n[!] Pulse Suspended.")

if __name__ == "__main__":
    run_single_line_engine()
