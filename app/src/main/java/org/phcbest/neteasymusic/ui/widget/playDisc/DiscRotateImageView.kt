package org.phcbest.neteasymusic.ui.widget.playDisc

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.ui.widget.tool.CustomUtils

class DiscRotateImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AppCompatImageView(context, attrs, defStyleAttr) {

    companion object {
        private const val TAG = "DiscRotateImageView"
    }

    private var mFrontRect: Rect = Rect()
    private var mFrontPaint: Paint = Paint()
    private var frontBitmap: Bitmap? = null

    val objectAnimator = ObjectAnimator.ofFloat(this, "rotation", 0f, 360f)

    init {
        //设置画笔属性
        mFrontPaint.let {
            it.isAntiAlias = true
            it.isDither = true
            it.isFilterBitmap = true
        }

        //设置默认前景
        frontBitmap = CustomUtils.getInstance()
            .getCircleBitmap(ResourcesCompat.getDrawable(resources,
                R.drawable.color_gray_bg,
                null)!!.toBitmap())

        //设置动画属性
        objectAnimator.duration = 30000
        objectAnimator.repeatCount = ObjectAnimator.INFINITE
        objectAnimator.interpolator = LinearInterpolator()
        objectAnimator.start()
        objectAnimator.pause()

    }

    private var mWidth: Int = 0
    private var mHeight: Int = 0
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = MeasureSpec.getSize(widthMeasureSpec)
        mHeight = MeasureSpec.getSize(heightMeasureSpec)
        Log.i(TAG, "onMeasure: 宽$mWidth  高$mHeight")

        //设置矩形尺寸，根据图片cd背景设置需要17%的偏移
        val shifting = (mWidth * 0.17f).toInt()
        Log.i(TAG, "onMeasure: 偏移量${shifting}")
        mFrontRect.set(0 + shifting, 0 + shifting, mWidth - shifting, mHeight - shifting)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //绘制前景，背景已经通过src设置了
        canvas?.drawBitmap(frontBitmap!!, null, mFrontRect, mFrontPaint)
    }

    fun setFrontBitmap(imageUrl: String) {
        if (imageUrl.isEmpty()) return

        Glide.with(context).asBitmap()
            .load(imageUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {
                    Log.i(TAG, "onLoadCleared: ")
                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    frontBitmap = CustomUtils.getInstance().getCircleBitmap(resource)
                    invalidate()
                }
            })
    }
}