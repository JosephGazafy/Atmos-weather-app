import numpy as np
from scipy.optimize import fsolve

# Atmos-Engine: Geospatial Triangulation (v128.0)
# Speed of Sound (v) = 343 m/s

def calculate_origin(sensor_coords, timestamps):
    v = 343.0
    
    # sensor_coords: list of (x, y) coordinates
    # timestamps: list of arrival times
    
    def equations(p):
        x, y, t0 = p
        # t0 is the unknown time of the actual shot
        return [
            np.sqrt((x - sensor_coords[i][0])**2 + (y - sensor_coords[i][1])**2) - v * (timestamps[i] - t0)
            for i in range(len(sensor_coords))
        ]

    # Starting guess (average of sensor positions)
    x_guess = np.mean([c[0] for c in sensor_coords])
    y_guess = np.mean([c[1] for c in sensor_coords])
    
    solution = fsolve(equations, (x_guess, y_guess, min(timestamps) - 0.1))
    return solution[0], solution[1]

# Example Sensor Locations: Independence, MO
sensors = [(39.0997, -94.5786), (39.0911, -94.5800), (39.1050, -94.5600)]
arrivals = [0.0012, 0.0025, 0.0031] # Sensed Timestamps

x, y = calculate_origin(sensors, arrivals)
print(f"--- TRIANGULATION COMPLETE ---")
print(f"Sovereign Origin Located: {x:.4f}, {y:.4f}")
