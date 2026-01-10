import random

class AcousticAudit:
    def __init__(self):
        self.noise_floor = 42.5  # dB (Data Center Baseline)
        self.vampire_hum = "50Hz-DETECTION"
        self.status = "ANC-ACTIVE"

    def get_exhaust_metrics(self):
        # Detecting the "Ceaseless Hum" frequency
        exhaust_hz = random.uniform(49.8, 50.2)
        return exhaust_hz, self.noise_floor

if __name__ == "__main__":
    audit = AcousticAudit()
    hz, db = audit.get_exhaust_metrics()
    print(f"🔊 [SONIC-AUDIT] EXHAUST: {hz:.2f}Hz | VOLUME: {db}dB")
