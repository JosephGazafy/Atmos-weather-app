[app]
# (str) Title of your application
title = Atmos Core

# (str) Package name
package.name = atmos_core

# (str) Package domain (Sovereign identifier)
package.domain = org.sovereign.mesh

# (str) Source code where the main.py or logic resides
source.dir = .

# (list) Source files to include (include the NIST binary and Go sentinel)
source.include_exts = py,png,jpg,kv,atlas,so,cpp,h,go,aar

# (list) Application requirements
# Including jnius to allow Python to talk to the Java/Go bridge
requirements = python3,kivy,pyjnius,plyer

# (str) Supported orientations
orientation = portrait

# (bool) Indicate if the application should be fullscreen
fullscreen = 1

# (list) Permissions
# Required for the Kinetic Kill-Switch and Acoustic Shield
android.permissions = RECORD_AUDIO, WAKE_LOCK, VIBRATE, INTERNET, BODY_SENSORS

# (list) Shared libraries (.so)
# LINKING NIST libbiomeval and Go sentinel logic
android.add_libs_armeabi_v7a = libbiomeval.so
android.add_libs_arm64_v8a = libbiomeval.so

# (int) Android API level (2025 Standard: 34 or higher)
android.api = 34
android.minapi = 21
android.sdk = 34

# (str) Android NDK version (Required for C++ compilation)
android.ndk = 25b

# (list) Services to run in the background
# This ensures the Sentinel and Shield are immune to OS dozing
services = AtmosSentinel:background_service.py:sticky

[buildozer]
# (int) Log level (0 = error only, 1 = info, 2 = debug)
log_level = 2

# (int) Display warning on buildozer if run as root
warn_on_root = 1
o

