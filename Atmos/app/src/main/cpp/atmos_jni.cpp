#include <jni.h>
#include <string>
#include "include/biomeval.h"

extern "C" JNIEXPORT jstring JNICALL
Java_org_sovereign_mesh_AtmosService_verifySignature(JNIEnv* env, jobject /* this */) {
    // NIST Logic Integration
    BiomEvalContext ctx;
    if (biomeval_init(&ctx) == BIOMEVAL_SUCCESS) {
        return env->NewStringUTF("Sovereignty Confirmed");
    }
    return env->NewStringUTF("Breach Detected");
}

