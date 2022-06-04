package org.phcbest.neteasymusic.ui.widget.playDisc

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView

class DiscRotateImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AppCompatImageView(context, attrs, defStyleAttr) {

    companion object {
        private const val TAG = "DiscRotateImageView"
    }

    private var mWidth: Int = 0
    private var mHeight: Int = 0
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = MeasureSpec.getSize(widthMeasureSpec)
        mWidth = MeasureSpec.getSize(heightMeasureSpec)
        Log.i(TAG, "onMeasure: 宽$mWidth  高$mHeight")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    /**
     * 生成混合disc的图片
     */
    fun generateDiscImage(discDrawable: Drawable) {
        val bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint()
        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL
    }

}