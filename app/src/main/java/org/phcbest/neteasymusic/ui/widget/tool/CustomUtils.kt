package org.phcbest.neteasymusic.ui.widget.tool

import android.graphics.*

class CustomUtils {
    //单例
    companion object {
        private var instance: CustomUtils? = null
        fun getInstance(): CustomUtils {
            if (instance == null) {
                instance = CustomUtils()
            }
            return instance!!
        }
    }

    /**
     * 绘制圆形bitmap
     */
    fun getCircleBitmap(bitmap: Bitmap): Bitmap? {
        //创建bitmap,宽高为 传入的bitmap的宽高,每个像素存在8个字节上,8位的精度
        val circleBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        //创建画板,画板对象为bitmap
        val canvas = Canvas(circleBitmap)
        val paint = Paint()
        //创建矩形,大小为传入矩形的大小
        val rect = Rect(0, 0, bitmap.width, bitmap.height)
        //将rect矩形转换为rectf对象 浮点类型
        val rectF = RectF(rect)
        //roundRa为判断宽高大小,取最小值除以2,也就是算出圆心
        val roundRa = if (bitmap.width > bitmap.height) {
            bitmap.height / 2.0f
        } else {
            bitmap.width / 2.0f
        }
        //抗锯齿
        paint.isAntiAlias = true
        //用rgb颜色填充画布,这里绘制为透明
        canvas.drawARGB(0, 0, 0, 0)
        //画笔颜色设置为灰色
        paint.color = Color.GRAY
        //绘制一个圆形的矩形 (矩形大小,x圆心,y圆心,画笔)
        canvas.drawRoundRect(rectF, roundRa, roundRa, paint)
        //图像混合模式 SRC_IN 只在源图像和目标图像相交的地方绘制源图像
        paint.xfermode =
            PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        //使用画笔添加的是源图像,该bitmap会在叠加的地方绘制
        canvas.drawBitmap(bitmap, rect, rect, paint)
        return circleBitmap
    }

}