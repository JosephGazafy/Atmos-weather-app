#!/data/data/com.termux/files/usr/bin/bash

echo "--- Building Atmos Environment ---"
# Create venv that can access Termux's system NumPy/SciPy
python -m venv --system-site-packages .venv

# Activate and install project in editable mode
source .venv/bin/activate
pip install --upgrade pip
pip install -e .

echo "--- Setup Peerless ---"
echo "To start, run: source .venv/bin/activate"
echo "Then you can run your code using the 'atmos' command."

