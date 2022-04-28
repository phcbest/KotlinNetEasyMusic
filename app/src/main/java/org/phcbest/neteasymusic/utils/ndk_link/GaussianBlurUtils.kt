package org.phcbest.neteasymusic.utils.ndk_link

import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log


class GaussianBlurUtils {

    companion object {

        private const val TAG = "GaussianBlurUtils"
        private var gaussianBlurUtils = GaussianBlurUtils()

        fun newInstance(): GaussianBlurUtils {
            return gaussianBlurUtils
        }

    }


    fun getGaussBlurZoomBmp(resource: Bitmap, r: Int, targetWidth: Int, targetHeight: Int): Bitmap {
        val width = resource.width
        val height = resource.height

        var createBitmap =
            Bitmap.createBitmap(resource, 0, 0, width, height, null, false)
        //判断比例 宽:高
        val wThanH = targetWidth.toFloat() / targetHeight
        Log.i(TAG, "getGaussBlurZoomBmp 目标宽高比: $wThanH")
        //计算按照目标比例的原图宽高
        val proWidth = height * wThanH

        if (proWidth > width) {
            //比例宽度大于实际宽度，以实际宽度为基准重新计算比例高度
            val proHeight = width / wThanH
            Log.i(TAG, "getGaussBlurZoomBmp 比例高度: $proHeight")
            createBitmap =
                Bitmap.createBitmap(resource,
                    0,
                    (height / 2 - proHeight / 2).toInt(),
                    width,
                    proHeight.toInt(),
                    null,
                    false)
        }

        getGaussBlurBmpJNI(createBitmap, r)
        return createBitmap
    }


    /**
     * c++接口
     */
    external fun stringFromJNI(): String

    /**
     * 使用c++计算高斯图
     */
    external fun getGaussBlurBmpJNI(bitmap: Bitmap, r: Int)

}