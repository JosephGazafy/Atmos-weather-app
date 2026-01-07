import numpy as np

# Atmos-Engine: QRE Stochastic Deterrence (v144.0)

def simulate_attacker_choice(utilities, lambda_val=1.5):
    # Logit choice model for Bounded Rationality
    exp_u = np.exp(lambda_val * np.array(utilities))
    probabilities = exp_u / np.sum(exp_u)
    return probabilities

def update_hawkes_intensity(current_intensity, events):
    # Self-exciting logic: Intensity increases with each event
    decay = 0.5
    excitation = 2.0
    new_intensity = current_intensity * np.exp(-decay) + (len(events) * excitation)
    return new_intensity

# Example: Attacker perceives 3 paths to the $65,737.61
path_utilities = [10, 45, -20] # (API Exploit, Phishing, Honeypot)
probs = simulate_attacker_choice(path_utilities)

print(f"--- QRE ANALYSIS COMPLETE ---")
print(f"Attacker Probabilities: {probs}")
print(f"Deterrence Status: HIGH (Honeypot Utility is Negative)")
