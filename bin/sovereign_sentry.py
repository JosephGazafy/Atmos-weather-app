#!/usr/bin/env python3
import subprocess, json, math, time, sys, datetime

# --- CONFIGURATION: THE INDEPENDENCE FIREWALL ---
LOG_FILE = "sovereign_log.txt"
CALIBRATION_SAMPLES = 50
GRAVITY = 9.80665
NOISE_FLOOR = 0.0

def get_sensor_data():
    try:
        result = subprocess.run(['termux-sensor', '-s', 'accelerometer', '-n', '1'], capture_output=True, text=True)
        data = json.loads(result.stdout)
        return data['accelerometer']['values'] if 'accelerometer' in data else None
    except: return None

def calculate_magnitude(coords):
    return math.sqrt(sum(c**2 for c in coords))

print("🌑💎 [ATMOS-PHYSICAL] CALIBRATING ZERO-POINT...")
time.sleep(1)
readings = []
for _ in range(CALIBRATION_SAMPLES):
    data = get_sensor_data()
    if data: readings.append(abs(calculate_magnitude(data) - GRAVITY))
    time.sleep(0.05)

NOISE_FLOOR = (sum(readings)/len(readings)) * 2.5 if readings else 0.5
print(f"✅ [ATMOS] BASELINE SET: {NOISE_FLOOR:.4f} m/s²")

try:
    while True:
        data = get_sensor_data()
        if data:
            dev = abs(calculate_magnitude(data) - GRAVITY)
            if dev > (NOISE_FLOOR * 5.0):
                print(f"🚨 [{datetime.datetime.now().strftime('%H:%M:%S')}] KINETIC BREACH DETECTED: {dev:.4f}")
            elif dev > NOISE_FLOOR:
                print(f"⚠️  KINETIC ACTIVITY: {dev:.4f}")
        time.sleep(0.5)
except KeyboardInterrupt:
    print("\n[!] SENTRY DISENGAGED.")
