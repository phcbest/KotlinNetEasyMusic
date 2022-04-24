#include <jni.h>
#include <string>
#include <android/bitmap.h>
#include <android/log.h>
#include "stackblur.h"


#define TAG "net_qiujuer_genius_blur_StackNative"
//打印log的宏定义
#define LOG_D(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__)

extern "C"
JNIEXPORT jstring JNICALL
Java_org_phcbest_neteasymusic_utils_ndk_1link_GaussianBlurUtils_stringFromJNI(JNIEnv *env,
                                                                              jobject thiz) {
    std::string hello = "Hello from C++ by Phc";
    return env->NewStringUTF(hello.c_str());
}


extern "C"
JNIEXPORT void JNICALL
Java_org_phcbest_neteasymusic_utils_ndk_1link_GaussianBlurUtils_getGaussBlurBmp(JNIEnv *env,
                                                                                jobject thiz,
                                                                                jobject bitmapIn,
                                                                                jint r) {
    AndroidBitmapInfo infoIn;
    void *pixels;
    //获得图片信息
    if (AndroidBitmap_getInfo(env, bitmapIn, &infoIn) != ANDROID_BITMAP_RESULT_SUCCESS) {
        LOG_D("AndroidBitmap_获得图片信息失败!");
        return;
    }
    //检查图片
    if (infoIn.format != ANDROID_BITMAP_FORMAT_RGBA_8888 &&
        infoIn.format != ANDROID_BITMAP_FORMAT_RGB_565) {
        LOG_D("只支持 ANDROID_BITMAP_FORMAT_RGBA_8888 and ANDROID_BITMAP_FORMAT_RGB_565");
        return;
    }
    //锁定所有图片
    if (AndroidBitmap_lockPixels(env, bitmapIn, &pixels) != ANDROID_BITMAP_RESULT_SUCCESS) {
        LOG_D("AndroidBitmap_锁定Pixels失败!");
        return;
    }
    //图片宽高
    int h = infoIn.height;
    int w = infoIn.width;

    //开始高斯模糊
    if (infoIn.format == ANDROID_BITMAP_FORMAT_RGBA_8888) {
        pixels = blur_ARGB_8888((int *) pixels, w, h, r);
    } else if (infoIn.format == ANDROID_BITMAP_FORMAT_RGB_565) {
        pixels = blur_RGB_565((short *) pixels, w, h, r);
    }

    //解锁所有资源
    AndroidBitmap_unlockPixels(env, bitmapIn);
}