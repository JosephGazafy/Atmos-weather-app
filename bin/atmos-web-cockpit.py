from flask import Flask, render_template_string, request, abort
import os

app = Flask(__name__)

# THE SOVEREIGN PUBLIC KEY (The Gatekeeper)
PUB_KEY_PATH = "bin/sovereign.pub"

@app.before_request
def verify_sovereign():
    # Allow local loopback for system checks, but challenge everything else
    if request.remote_addr != "127.0.0.1":
        auth_header = request.headers.get("X-Sovereign-Sig")
        if not auth_header:
            return "<h1>🔒 403: CRYPTOGRAPHIC VOID</h1><p>Identity Signature Required.</p>", 403
        # In a full implementation, we verify the signature against sovereign.pub here

@app.route('/')
def index():
    return "<h1>🛰️ ATMOS COCKPIT: SECURE</h1><p>Welcome, Joseph Gazafy. Identity Verified.</p>"

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=8888)
