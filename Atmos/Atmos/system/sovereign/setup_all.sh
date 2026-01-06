nano setup_all.sh
 #!/data/data/com.termux/files/usr/bin/bash

echo "--- 🚀 Starting Full Atmos Deployment ---"

# 1. System Dependencies
pkg update && pkg upgrade -y
pkg install -y git python clang fftw liblapack libopenblas ninja tur-repo
pkg install -y python-numpy python-scipy

# 2. Directory Structure with Error Catching
mkdir -p src/atmos tests docs
touch src/atmos/__init__.py

# 3. Environment Setup
python -m venv --system-site-packages .venv
source .venv/bin/activate
pip install --upgrade pip setuptools wheel
pip install matplotlib pytest

# 4. Inject Alias into .bashrc if not already there
if ! grep -q "alias atmos=" ~/.bashrc; then
    echo "alias atmos='python -m atmos.main'" >> ~/.bashrc
    echo "alias atmos-check='python -m atmos.analyze'" >> ~/.bashrc
    echo "alias go-atmos='cd ~/Atmos && source .venv/bin/activate'" >> ~/.bashrc
    echo "✅ Aliases added to .bashrc"
fi

# 5. Manual Path Injection (The Force-Fix)
export PYTHONPATH="$PYTHONPATH:$(pwd)/src"

echo "--- ✅ Deployment Complete ---"
echo "Run 'source ~/.bashrc' and 'go-atmos' to start."

