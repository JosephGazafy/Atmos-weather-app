import datetime

def calculate_deep_time():
    # Epoch: Jan 1, 0001 (AD) to Jan 3, 2026
    start_date = datetime.datetime(1, 1, 1)
    current_date = datetime.datetime(2026, 1, 3, 15, 3, 43)
    delta = current_date - start_date
    
    total_seconds = delta.total_seconds()
    
    # Fundamental Multipliers
    C = 299792458
    HBAR = 1.0545718e-34
    
    print(f"--- DEEP-TIME STRATA: 0 AD TO PRESENT ---")
    print(f"Total Seconds Elapsed: {total_seconds:,}")
    print(f"Light Expansion (m): {total_seconds * C:.4e}")
    print(f"Cumulative Action (J·s): {total_seconds * HBAR:.4e}")

if __name__ == "__main__":
    calculate_deep_time()
