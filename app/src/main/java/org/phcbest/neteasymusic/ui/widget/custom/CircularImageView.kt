package org.phcbest.neteasymusic.ui.widget.custom

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.ProxyFileDescriptorCallback
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
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

    private fun init() {
        mPaint.isAntiAlias = true
        mBitmap = getCircleBitmap(resources.getDrawable(R.drawable.sample_avatar, null).toBitmap())
    }

    private fun getCircleBitmap(bitmap: Bitmap): Bitmap {
        val circleBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(circleBitmap)
        val paint = Paint()
        val rect = Rect(0, 0, bitmap.width, bitmap.height)
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
        return circleBitmap
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val minLength = if (height > width) width else height
        mRect.set(0, 0, minLength, minLength)
        canvas?.drawBitmap(mBitmap!!, null, mRect, mPaint)
    }

    fun setImage(imageUrl: String) {
        if (imageUrl.contains("http")) {
            Glide.with(mContext!!).asBitmap().load(imageUrl).error(R.drawable.sample_avatar)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        mBitmap = getCircleBitmap(resource)
                        invalidate()
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                    }
                })
        }
    }

}