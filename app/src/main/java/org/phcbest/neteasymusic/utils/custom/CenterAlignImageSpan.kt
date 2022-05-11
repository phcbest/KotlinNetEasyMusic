package org.phcbest.neteasymusic.utils.custom

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.style.ImageSpan

class CenterAlignImageSpan : ImageSpan {
    constructor(b: Bitmap) : super(b)
    constructor(drawable: Drawable) : super(drawable)

    override fun draw(
        canvas: Canvas,
        text: CharSequence?,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint,
    ) {
        val fm = paint.fontMetricsInt
        //计算y轴位移的方向
        val transY = (y + fm.descent + y + fm.ascent) / 2 - drawable.bounds.bottom / 2
        canvas.save()
        canvas.translate(x, transY.toFloat())
        drawable.draw(canvas)
        canvas.restore()
    }

}