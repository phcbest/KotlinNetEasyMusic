package org.phcbest.neteasymusic.ui.widget.custom

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.ProxyFileDescriptorCallback
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.IntRange
import androidx.annotation.IntegerRes
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.toRectF
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import org.phcbest.neteasymusic.R

class CircularImageView : View {

    private var mBitmap: Bitmap? = null
    private var mContext: Context? = null
    private var mRect = Rect()
    private var mPaint = Paint()

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        mContext = context
        init()
    }

    companion object {
        private const val TAG = "CircularImageView"
    }

    private fun init() {
        mPaint.isAntiAlias = true
//        mBitmap = getCircleBitmap(resources.getDrawable(R.drawable.sample_avatar, null).toBitmap())
        mBitmap =
            getCircleBitmap(ResourcesCompat.getDrawable(resources, R.drawable.sample_avatar, null)
            !!.toBitmap())
    }

    private fun getCircleBitmap(bitmap: Bitmap): Bitmap {
        return getCircleBitmap(bitmap, false)
    }

    private fun getCircleBitmap(bitmap: Bitmap, outline: Boolean): Bitmap {
        return getCircleBitmap(bitmap, outline, "#ED7B7B")
    }

    private fun getCircleBitmap(
        bitmap: Bitmap,
        outline: Boolean,
        outlineColor: String,
    ): Bitmap {

        val circleBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(circleBitmap)
        val paint = Paint()
        //留出和view的pending
        val rect = Rect(10, 10, bitmap.width - 10, bitmap.height - 10)
        val rectF = RectF(rect)
        //按长宽最短的设定圆心位置
        val roundRa = if (bitmap.width > bitmap.height) {
            bitmap.height / 2F
        } else {
            bitmap.width / 2f
        }

        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = Color.GRAY
        canvas.drawRoundRect(rectF, roundRa, roundRa, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)
        //渲染外边框
        if (outline) {
            //计算x和y轴坐标
            val p1 = Paint()
            p1.isAntiAlias = true
            p1.style = Paint.Style.STROKE
            p1.strokeWidth = 5f
            p1.color = Color.parseColor(outlineColor)
            //需要去除strokeWidth的宽度
            val rectF1 =
                RectF(3F, 3F, bitmap.width - 3F, bitmap.height - 3F)
            canvas.drawArc(rectF1, 0F, 360F, false, p1)
        }
        return circleBitmap
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val minLength = if (height > width) width else height
        mRect.set(0, 0, minLength, minLength)
        canvas?.drawBitmap(mBitmap!!, null, mRect, mPaint)
    }

    fun setImage(imageUrl: String, sizeLimit: Int, stroke: Boolean) {
        if (imageUrl.contains("http")) {
            var url = imageUrl
            if (sizeLimit != 0) {
                url = "$imageUrl?param=$sizeLimit" + "y$sizeLimit"
            }
//            Log.i(TAG, "onResourceReady: 加载新图像 $url")
            Glide.with(mContext!!).asBitmap().load(url).error(R.drawable.sample_avatar)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?,
                    ) {
                        mBitmap = getCircleBitmap(resource, stroke)
                        invalidate()
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                    }
                })
        }
    }

}