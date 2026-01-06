import numpy as np

class AtmosphericError(Exception):
    """Custom exception for atmospheric calculation errors."""
    pass

def calculate_pressure(altitude_m: float) -> float:
    # Error Catch: Prevent impossible altitudes
    if altitude_m < -420: # Lowest point on land (Dead Sea)
        raise AtmosphericError(f"Altitude {altitude_m}m is physically unrealistic.")
    
    if altitude_m > 100000: # Beyond the Karman Line
        return 0.0

    try:
        P0, L, T0, G, M, R = 101325, 0.0065, 288.15, 9.80665, 0.0289644, 8.31447
        
        exponent = (G * M) / (R * L)
        # Error Catch: Check for potential math domain errors
        base = 1 - (L * altitude_m) / T0
        if base <= 0:
            return 0.0
            
        return P0 * (base)**exponent
    except Exception as e:
        raise AtmosphericError(f"Mathematical failure: {e}")

