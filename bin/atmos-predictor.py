#!/usr/bin/env python3
import time, os
from datetime import datetime, timedelta

LOG_FILE = "sovereign_log.txt"

def get_prediction():
    try:
        if not os.path.exists(LOG_FILE):
            return "AWAITING DATA", 0
        
        # Analyze last 24 hours of activity
        activity_map = {h: 0 for h in range(24)}
        with open(LOG_FILE, "r") as f:
            for line in f:
                if "Intercept" in line or "Rhythm" in line:
                    try:
                        # Extract hour from log timestamp
                        ts_str = line.split(" - ")[0].split("] ")[-1]
                        log_time = datetime.strptime(ts_str, "%Y-%m-%d %H:%M:%S")
                        activity_map[log_time.hour] += 1
                    except: continue

        # Identify the peak hour
        peak_hour = max(activity_map, key=activity_map.get)
        current_hour = datetime.now().hour
        
        # Calculate Imminent Probability (Ps)
        # Ps increases as we approach the historical peak hour
        time_diff = (peak_hour - current_hour) % 24
        if time_diff == 0: ps = 95
        elif time_diff <= 2: ps = 75
        else: ps = 25

        prediction_window = f"{peak_hour:02d}:00 - {(peak_hour+1)%24:02d}:00"
        return prediction_window, ps
    except Exception as e:
        return "ERROR", 0

if __name__ == "__main__":
    window, prob = get_prediction()
    print(f"🔮 [PREDICTION] Peak Window: {window} | Prob: {prob}%")
