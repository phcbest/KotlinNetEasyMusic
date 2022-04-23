package org.phcbest.neteasymusic.utils

import android.R.attr
import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur


class GaussianBlurUtils {

    companion object {

        private var gaussianBlurUtils = GaussianBlurUtils()

        fun newInstance(): GaussianBlurUtils {
            return gaussianBlurUtils
        }

    }

    fun blurBitmap(context: Context, bitmap: Bitmap): Bitmap {
        //创建空的bitmap
        val outBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val rs = RenderScript.create(context)
        val blurScript = ScriptIntrinsicBlur.create(rs, android.renderscript.Element.U8_4(rs))
        val allIn = Allocation.createFromBitmap(rs, bitmap)
        val allOut = Allocation.createFromBitmap(rs, outBitmap)
        //设定模糊度(注：Radius最大只能设置25.f)
        blurScript.setRadius(15f)
        blurScript.setInput(allIn)
        blurScript.forEach(allOut)
        allOut.copyTo(outBitmap)
        rs.destroy()
        return outBitmap
    }

}