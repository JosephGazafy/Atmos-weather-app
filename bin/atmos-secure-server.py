#!/usr/bin/env python3
import base64, os, sys
from http.server import HTTPServer, SimpleHTTPRequestHandler

# --- CONFIGURATION ---
# USERNAME: atmos
# PASSWORD: atlas (You can change this here)
AUTH_STRING = "atmos:atlas"
ENCODED_AUTH = base64.b64encode(AUTH_STRING.encode()).decode()

class SecureHandler(SimpleHTTPRequestHandler):
    def do_AUTHHEAD(self):
        self.send_response(401)
        self.send_header('WWW-Authenticate', 'Basic realm="Atmos-Atlas Sovereign Gateway"')
        self.send_header('Content-type', 'text/html')
        self.end_headers()

    def do_GET(self):
        auth_header = self.headers.get('Authorization')
        if auth_header is None or auth_header != f"Basic {ENCODED_AUTH}":
            self.do_AUTHHEAD()
            self.wfile.write(b'<html><body><h1>401 Unauthorized</h1><p>Sovereign Token Required.</p></body></html>')
        else:
            # Allow access to the public directory
            os.chdir(os.path.expanduser("~/judah-joseph-Atmos-Engine/public"))
            super().do_GET()

if __name__ == "__main__":
    port = 8888
    print(f"🔐 [ATMOS] SECURE GATEWAY STARTING ON PORT {port}...")
    print(f"👤 USER: atmos | 🔑 PASS: atlas")
    server = HTTPServer(('0.0.0.0', port), SecureHandler)
    try:
        server.serve_forever()
    except KeyboardInterrupt:
        print("\n🛑 GATEWAY SHUTDOWN.")
