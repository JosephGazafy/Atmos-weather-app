import numpy as np
import sounddevice as sd
import time

# ATMOS CORE v3.0 - ACOUSTIC AUDIT
# Detecting side-channel ultrasonic pings

FS = 44100  # Sample rate
DURATION = 2  # Seconds per audit

def callback(indata, frames, time, status):
    if status:
        print(status)
    # Perform Fast Fourier Transform (FFT)
    magnitude = np.abs(np.fft.rfft(indata[:, 0]))
    freqs = np.fft.rfftfreq(len(indata), 1.0/FS)
    
    # Check for intensity in the 18kHz-22kHz range
    ultrasonic_zone = magnitude[(freqs > 18000) & (freqs < 22000)]
    if np.max(ultrasonic_zone) > 0.5:  # Threshold for detection
        print("⚠️ WARNING: HIGH-FREQUENCY ACOUSTIC TRACING DETECTED")

print("📡 Acoustic Shield Active. Auditing Soundscape...")
with sd.InputStream(callback=callback):
    while True:
        time.sleep(1)

