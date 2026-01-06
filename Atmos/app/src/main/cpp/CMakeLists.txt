cmake_minimum_required(VERSION 3.22.1)
project("atmos_native")

# 1. Link the NIST Biometric Library
add_library(biomeval SHARED IMPORTED)
set_target_properties(biomeval PROPERTIES IMPORTED_LOCATION 
             ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libbiomeval.so)

# 2. Build the Atmos JNI Bridge
add_library(atmos_jni SHARED atmos_jni.cpp bio_verify.cpp)

# 3. Include Android System Libraries (Log, Math)
find_library(log-lib log)

# 4. Final Linkage
target_link_libraries(atmos_jni biomeval ${log-lib})

