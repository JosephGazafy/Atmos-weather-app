import matplotlib.pyplot as plt
import numpy as np
from atmos.core import calculate_pressure

def generate_graph():
    print("Generating atmospheric pressure curve...")
    
    # 1. Create a range of altitudes from 0 to 15,000 meters
    altitudes = np.linspace(0, 15000, 100)
    
    # 2. Calculate pressure for each altitude using your core logic
    pressures = [calculate_pressure(alt) for alt in altitudes]
    
    # 3. Create the plot
    plt.figure(figsize=(10, 6))
    plt.plot(altitudes, pressures, color='blue', linewidth=2)
    
    # 4. Add "finer" styling
    plt.title("Atmospheric Pressure vs. Altitude", fontsize=14)
    plt.xlabel("Altitude (meters)", fontsize=12)
    plt.ylabel("Pressure (Pascals)", fontsize=12)
    plt.grid(True, linestyle='--', alpha=0.7)
    
    # 5. Save the result
    output = "pressure_graph.png"
    plt.savefig(output)
    print(f"Graph successfully saved as {output}")

if __name__ == "__main__":
    generate_graph()

