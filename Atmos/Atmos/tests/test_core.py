import pytest
from atmos.core import calculate_pressure

def test_sea_level_pressure():
    """Verify that 0m altitude returns standard sea level pressure."""
    # Standard sea level pressure is approx 101325 Pa
    result = calculate_pressure(0)
    assert round(result) == 101325

def test_high_altitude_pressure():
    """Verify that pressure decreases as altitude increases."""
    p_low = calculate_pressure(0)
    p_high = calculate_pressure(1000)
    assert p_high < p_low

def test_input_types():
    """Ensure the function handles float-like inputs correctly."""
    assert isinstance(calculate_pressure(500.5), float)

