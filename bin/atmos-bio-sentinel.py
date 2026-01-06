#!/usr/bin/env python3
import random, time, os, sys, json
from dataclasses import dataclass
from datetime import datetime

# ===========================================================
# ATMOS-ATLAS-01: BIO-SENTINEL (CBDS + ASAR INTEGRATION)
# ===========================================================

# [CONFIG]
LOG_FILE = os.path.expanduser("~/sovereign_log.txt")
BASE_THRESHOLD = 0.85

# [COLORS & STYLING]
class Style:
    RED = "\033[91m"
    GREEN = "\033[92m"
    YELLOW = "\033[93m"
    CYAN = "\033[96m"
    RESET = "\033[0m"
    BOLD = "\033[1m"

# =====================
# I. THE STATE ENGINE (ASAR)
# =====================
@dataclass
class SystemState:
    liveness: float = 1.0
    performance: float = 1.0
    error_rate: float = 0.0
    regression_count: int = 0
    adaptation_level: str = "OPTIMAL"
    dynamic_threshold: float = BASE_THRESHOLD

class ASAR_Manager:
    """
    Adaptive Simulation Advancement and Regression (ASAR)
    Manages the 'Health' of the detection logic.
    """
    def evaluate(self, state: SystemState, anomaly_score: float):
        # 1. Regression Logic: High anomaly stress degrades liveness
        if anomaly_score > state.dynamic_threshold:
            state.error_rate += 0.1
            state.liveness -= 0.05
        else:
            state.liveness = min(1.0, state.liveness + 0.01)
            state.error_rate = max(0.0, state.error_rate - 0.01)

        # 2. Adaptation Logic: Adjust Strategy based on health
        if state.error_rate > 0.4:
            state.adaptation_level = "CRITICAL REGRESSION"
            state.dynamic_threshold = 0.60 # Lower threshold (Paranoia Mode)
            state.regression_count += 1
        elif state.error_rate > 0.2:
            state.adaptation_level = "STABILIZING"
            state.dynamic_threshold = 0.75 # Heightened Alert
        else:
            state.adaptation_level = "NOMINAL"
            state.dynamic_threshold = BASE_THRESHOLD # Standard Watch

        return state

# =====================
# II. THE SENSOR FUSION (CBDS)
# =====================
class SensorArray:
    """
    Simulates multi-modal bio-telemetry inputs.
    """
    @staticmethod
    def scan_environment():
        # Telemetry Simulation
        motion_index = random.choice([0, 0, 1, 2]) # 0=Still, 1=Movement, 2=Struggle
        audio_sig = random.choice(["silence", "conversation", "heavy_breathing", "scream", "bone_snap"])
        visual_tags = random.choice([
            [], ["food", "utensils"], ["knife", "red_fluid"], ["organic_mass", "dismemberment"]
        ])
        
        # Calculate Threat Vector
        score = 0.0
        if motion_index == 2: score += 0.20
        if audio_sig in ["scream", "bone_snap"]: score += 0.40
        if "knife" in visual_tags: score += 0.20
        if "organic_mass" in visual_tags or "dismemberment" in visual_tags: score += 0.50
        
        return min(score, 1.0), f"M:{motion_index} A:{audio_sig} V:{visual_tags}"

# =====================
# III. SOVEREIGN LOGIC
# =====================
def log_event(cycle, score, state, details):
    ts = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    # Only log significant events to spare storage
    if score > 0.5 or state.adaptation_level != "NOMINAL":
        with open(LOG_FILE, "a") as f:
            f.write(f"👁️ [{ts}] [BIO-SENTINEL] SCORE:{score:.2f} | STATE:{state.adaptation_level} | DATA:{details}\n")

def render_hud(cycle, state, score, details):
    # Clear screen (ANSI)
    print("\033[2J\033[H", end="")
    print(f"{Style.CYAN}┌────────────────────────────────────────────────────────────┐{Style.RESET}")
    print(f"{Style.CYAN}│  🌑💎✨🌀 [ ATMOS-ATLAS-01: BIO-SENTINEL ] 🌀✨💎🌑       │{Style.RESET}")
    print(f"{Style.CYAN}├────────────────────────────────────────────────────────────┤{Style.RESET}")
    
    # State Indicators
    liveness_col = Style.GREEN if state.liveness > 0.7 else Style.RED
    
    print(f"│ 🔄 CYCLE       : {cycle:<39} │")
    print(f"│ 🧠 ASAR STATE  : {state.adaptation_level:<39} │")
    print(f"│ 💓 LIVENESS    : {liveness_col}{state.liveness:.2f}{Style.RESET}   📉 ERROR: {state.error_rate:.2f}                   │")
    print(f"│ 🛡️  THRESHOLD   : {state.dynamic_threshold:.2f}   🔄 REGRESSIONS: {state.regression_count:<6}     │")
    print(f"{Style.CYAN}├────────────────────────────────────────────────────────────┤{Style.RESET}")
    
    # Threat Visualizer
    bar_len = int(score * 30)
    bar_col = Style.RED if score > state.dynamic_threshold else Style.GREEN
    bar_graph = "█" * bar_len + "░" * (30 - bar_len)
    
    print(f"│ 👁️  THREAT LEVEL: [{bar_col}{bar_graph}{Style.RESET}] {score:.2f}          │")
    print(f"│ 📡 TELEMETRY   : {details[:39]:<39} │")
    
    # Alert Status
    if score >= state.dynamic_threshold:
        print(f"{Style.CYAN}├────────────────────────────────────────────────────────────┤{Style.RESET}")
        print(f"│ {Style.RED}🚨 ALERT: KINETIC ANOMALY DETECTED!                       {Style.RESET} │")
        print(f"│ {Style.RED}   ACTION: SOVEREIGN LOG UPDATED.                         {Style.RESET} │")
    
    print(f"{Style.CYAN}└────────────────────────────────────────────────────────────┘{Style.RESET}")

# =====================
# IV. MAIN EXECUTION LOOP
# =====================
def run_sentinel():
    asar = ASAR_Manager()
    state = SystemState()
    sensors = SensorArray()
    cycle = 0

    print("🚀 [ATMOS] INITIALIZING BIO-SENTINEL DAEMON...")
    time.sleep(1)

    try:
        while True:
            cycle += 1
            
            # 1. Scan
            anomaly_score, raw_data = sensors.scan_environment()
            
            # 2. Evaluate (ASAR)
            state = asar.evaluate(state, anomaly_score)
            
            # 3. Log & Render
            log_event(cycle, anomaly_score, state, raw_data)
            render_hud(cycle, state, anomaly_score, raw_data)
            
            # Simulation Pacing
            if anomaly_score > state.dynamic_threshold:
                time.sleep(2) # Linger on alerts
            else:
                time.sleep(0.5) # Fast scan on clear

    except KeyboardInterrupt:
        print(f"\n{Style.YELLOW}🛑 [ATMOS] SENTINEL HALTED. LOGS SECURED.{Style.RESET}")

if __name__ == "__main__":
    run_sentinel()
