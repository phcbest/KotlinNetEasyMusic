package org.phcbest.neteasymusic.utils.ndk_link

import android.graphics.Bitmap
import android.graphics.Color
import android.icu.number.IntegerWidth
import kotlin.math.exp
import kotlin.math.round
import kotlin.math.sqrt


class GaussianBlurUtils {

    companion object {

        const val sigma = 1f
        const val r = 5

        private var gaussianBlurUtils = GaussianBlurUtils()

        fun newInstance(): GaussianBlurUtils {
            return gaussianBlurUtils
        }
    }


//    /**
//     * 边界处理的函数
//     * 在高斯模糊中,每个像素点的新值都是由附近像素的平均值得出的
//     * 如果矩形区域超出像素范围,就会产生黑边,为了消除这个影响需要使用对称方法
//     * 将矩形的超出部分用内部的对应点代替。
//     * @param x 中心位置
//     * @param i 偏移量
//     * @param w 宽度
//     */
//    fun edge(x: Int, i: Int, w: Int): Int {
//        val intx = x + i
//        if (intx < 0 || intx >= w)
//            return x - i
//        return intx
//    }
//
//    /**
//     * 一维高斯
//     */
//    fun gaussFun1D(x: Int): Double {
//        val A = 1 / (sqrt(2 * 3.141592653) * sigma)
//        val index = -1.0 * (x * x) / (2 * sigma * sigma)
//        return A * exp(index)
//    }
//
//    /**
//     * 获取线性的权值空间
//     */
//
//    fun getKernal(): List<Double> {
//        val weiget: MutableList<Double> = mutableListOf()
//        var sun: Double = 0.0
//        //获取权值空间
//        for (i: Int in 0 until (2 * r + 1)) {
//            weiget[i] = gaussFun1D(i - r)
//            sun += weiget[i]
//        }
//        //归一化
//        for (i: Int in 0 until (2 * r + 1)) {
//            weiget[i] /= sun
//        }
//
//        return weiget
//    }
//
//    //进行两个方向的高斯模糊
//    fun getBlurImage(originImg: Bitmap): Bitmap {
//        val width = originImg.width
//        val height = originImg.height
//        //第一次方向处理
//        val tmpImg = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
//        //第二次处理
//        val newImg = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
//
//        val weight = getKernal()
//        //横向相加
//        for (y in 0 until height) {
//            for (x in 0 until width) {
//                var red = 0.0
//                var green = 0.0
//                var blue = 0.0
//                for (i in -r..r) {
//                    // 边界处理后的对应的权值矩阵实际值
//                    val inx = edge(x, i, width)
//                    val color = originImg.getPixel(inx, y)
//                    //返回颜色的色彩分量后计算权重
//                    red += Color.red(color) * weight[r + i]
//                    green += Color.green(color) * weight[r + i]
//                    blue += Color.blue(color) * weight[r + i]
//                }
//                val color = Color.rgb("${round(red)}".toInt(),
//                    "${round(green)}".toInt(),
//                    "${round(blue)}".toInt())
//            }
//        }
//        //纵向相加
//        for (y in 0 until height) {
//
//        }
//        return newImg
//    }


    /**
     * c++接口
     */
    external fun stringFromJNI(): String

    /**
     * 使用c++计算高斯图
     */
    external fun getGaussBlurBmp(bitmap: Bitmap, r: Int)

}