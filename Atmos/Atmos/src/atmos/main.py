import argparse
import json
import os
from datetime import datetime

def main():
    parser = argparse.ArgumentParser(description="Atmos: Sovereign Physics Engine")
    parser.add_argument("-a", "--altitude", type=float, required=True, help="Altitude in meters")
    parser.add_argument("-j", "--json", action="store_true", help="Export data to JSON")
    
    args = parser.parse_args()
    
    # Physics Calculation (Standard Atmosphere Model)
    temp = 288.15 - (0.0065 * args.altitude)
    pressure = 101325 * (temp / 288.15)**5.25588
    density = pressure / (287.05 * temp)
    
    print(f"--- Atmos Physics Output ---")
    print(f"Altitude: {args.altitude}m")
    print(f"Density:  {density:.4f} kg/m³")
    
    if args.json:
        data = {
            "timestamp": datetime.now().isoformat(),
            "altitude": args.altitude,
            "pressure": pressure,
            "density": density
        }
        
        # Absolute path for Termux stability
        path = "/data/data/com.termux/files/home/Atmos/Atmos/Atmos/data.json"
        with open(path, "a") as f:
            f.write(json.dumps(data) + "\n")
        print(f"✅ Data logged to {path}")

if __name__ == "__main__":
    main()

