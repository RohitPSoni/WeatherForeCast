#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_weatherforecast_remote_AppIdInterceptor_apiKey(JNIEnv *env, jobject thiz) {
    std::string api_key = "65d00499677e59496ca2f318eb68c049";
    return env->NewStringUTF(api_key.c_str());
}