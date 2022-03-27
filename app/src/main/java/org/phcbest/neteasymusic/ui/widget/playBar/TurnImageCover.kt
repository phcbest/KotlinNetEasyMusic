package org.phcbest.neteasymusic.ui.widget.playBar

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import org.phcbest.neteasymusic.R


private const val TAG = "TurnImageCover"

class TurnImageCover : View {
    //上下文
    private var mContext: Context? = null

    //图片
    private var mBackBm: Bitmap? = null
    private var mFrontBm: Bitmap? = null

    //rect位置
    private var mBackRect = Rect()
    private var mFrontRect = Rect()

    //画笔
    private var mBackPaint = Paint()
    private var mFrontPaint = Paint()

    //常量
    private var HALF = 0.5F
    private var QUARTER = 0.15F

    constructor(context: Context?) : this(context, null)

    //在xml中调用使用的是这个方法
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        this.mContext = context
        init()
    }

    private fun init() {
        //初始化前后背景bitmap防止空对象
        mBackBm = resources.getDrawable(R.drawable.cd_bg, null).toBitmap()
        mFrontBm = getCircleBitmap(resources.getDrawable(R.drawable.cd_fg_sample, null).toBitmap())
        //矩形和图画参数
        mBackPaint.isAntiAlias = true //抗锯齿
        mFrontPaint.isAntiAlias = true //抗锯齿
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val mSize = MeasureSpec.getSize(widthMeasureSpec)
        val hSize = MeasureSpec.getSize(heightMeasureSpec)
        Log.i(TAG, "onMeasure: $mSize $hSize")
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.i(TAG, "onMeasure: $height $width $left $right $top $bottom ")
        //绘制cd
        mBackRect.set(0, 0, width, height)
        canvas?.drawBitmap(mBackBm!!, null, mBackRect, mBackPaint)
        //绘制圆形封面
        mFrontRect.set(
            (width * QUARTER).toInt(),
            (height * QUARTER).toInt(),
            (width - (width * QUARTER)).toInt(),
            (height - (height * QUARTER)).toInt()
        )
        if (mFrontBm != null) canvas?.drawBitmap(mFrontBm!!, null, mFrontRect, mFrontPaint)


    }

    /**
     * @param back 这个参数是背景,如果为-1,就使用默认背景
     */
    fun setBackAndFrontGround(back: Int, front: String): TurnImageCover {
        //设置背景
        if (back == -1) {
            mBackBm = resources.getDrawable(R.drawable.cd_bg, null).toBitmap()
            invalidate()
        } else {
            mBackBm = resources.getDrawable(back, null).toBitmap()
            invalidate()
        }
        //设置前景
        Glide.with(mContext!!).asBitmap().load("$front?param=130y130")
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    mFrontBm = getCircleBitmap(resource)
                    //刷新
                    invalidate()
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })

        return this
    }


    /**
     * 绘制圆形bitmap
     */
    private fun getCircleBitmap(bitmap: Bitmap): Bitmap? {
        val circleBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(circleBitmap)
        val paint = Paint()
        val rect = Rect(0, 0, bitmap.width, bitmap.height)
        val rectF = RectF(rect)
        val roundRa = if (bitmap.width > bitmap.height) {
            bitmap.height / 2.0f
        } else {
            bitmap.width / 2.0f
        }
        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = Color.GRAY
        canvas.drawRoundRect(rectF, roundRa, roundRa, paint)
        paint.xfermode =
            PorterDuffXfermode(PorterDuff.Mode.SRC_IN)//图像混合模式 SRC_IN 只在源图像和目标图像相交的地方绘制源图像
        canvas.drawBitmap(bitmap, rect, rect, paint)
        return circleBitmap
    }

}