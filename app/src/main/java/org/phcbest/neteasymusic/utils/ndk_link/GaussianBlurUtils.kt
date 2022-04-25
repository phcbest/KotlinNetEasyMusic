package org.phcbest.neteasymusic.utils.ndk_link

import android.graphics.Bitmap
import android.graphics.Color
import android.icu.number.IntegerWidth
import kotlin.math.exp
import kotlin.math.round
import kotlin.math.sqrt


class GaussianBlurUtils {

    companion object {

        private var gaussianBlurUtils = GaussianBlurUtils()

        fun newInstance(): GaussianBlurUtils {
            return gaussianBlurUtils
        }

    }


    /**
     * c++接口
     */
    external fun stringFromJNI(): String

    /**
     * 使用c++计算高斯图
     */
    external fun getGaussBlurBmp(bitmap: Bitmap, r: Int)

}