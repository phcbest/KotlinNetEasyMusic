package org.phcbest.neteasymusic.ui.widget.playBar

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import androidx.core.graphics.drawable.toBitmap
import org.phcbest.neteasymusic.R
import kotlin.math.log


private const val TAG = "PlayBarProgressButton"

class PlayBarProgressButton : View {
    private var mContext: Context? = null

    private var mCirclePaint: Paint = Paint()
    private var mProgressPaint: Paint = Paint()


    private var mBmRect: Rect = Rect()
    private var mBmPaint: Paint = Paint()
    private var mBm: Bitmap? = null
    private var mPlayBm: Bitmap? = null
    private var mPauseBm: Bitmap? = null

    private var mSweepAngle: Float = 0F

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        this.mContext = context
        init()
    }

    private fun init() {
        //设置圆形画笔
        mCirclePaint.isAntiAlias = true
        mCirclePaint.color = Color.parseColor("#88888888")
        mCirclePaint.style = Paint.Style.STROKE
        mCirclePaint.strokeWidth = 3f
        //设置进度弧形画笔
        mProgressPaint.isAntiAlias = true //抗锯齿
        mProgressPaint.color = Color.parseColor("#000000")
        mProgressPaint.style = Paint.Style.STROKE
        mProgressPaint.strokeWidth = 4f
        //初始化bitmap,设置默认状态为暂停
        mPlayBm = resources.getDrawable(R.drawable.ic_play_bar_play, null).toBitmap()
        mPauseBm = resources.getDrawable(R.drawable.ic_play_bar_pause, null).toBitmap()
        pause()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size = MeasureSpec.getSize(widthMeasureSpec)
        Log.i(TAG, "onMeasure: $size")
        //描述一个矩形位置
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val widthHalf = (width / 2).toFloat()
        val heightHalf = (height / 2).toFloat()
        val radius = (width.coerceAtMost(height) / 2) - 3.toFloat()//因为画笔自带3f厚度,需要减掉
        Log.i(TAG, "onDraw: $widthHalf,$heightHalf,$radius")
        //绘制圆圈
        canvas.drawCircle(
            widthHalf,
            heightHalf,
            radius,
            mCirclePaint
        )

        //绘制进度弧形,计算矩形大小使用公式(宽度)
        canvas.drawArc(
            widthHalf - radius,
            heightHalf - radius,
            width - (widthHalf - radius),
            height - (heightHalf - radius),
            -90F,
            mSweepAngle,
            false,
            mProgressPaint
        )
        //绘制状态按钮,需要矩形和view的2/3大
        mBmRect.set(
            (widthHalf / 2).toInt() + 5,
            (heightHalf / 2).toInt(),
            (widthHalf + widthHalf / 2).toInt(),
            (heightHalf + heightHalf / 2).toInt()
        )
        mBmPaint.isAntiAlias = true
        canvas.drawBitmap(mBm!!, null, mBmRect, mBmPaint)
    }

    /**
     * 播放
     */
    fun play() {
        mBm = mPlayBm
        invalidate()
    }

    /**
     * 暂停
     */
    fun pause() {
        mBm = mPauseBm
        invalidate()
    }

    /**
     * 进度更新
     */
    fun updateProgress(@IntRange(from = 0, to = 100) progress: Int) {
        this.mSweepAngle = (3.6 * progress).toFloat()
        Log.i(TAG, "updateProgress: ${(3.6 * progress).toFloat()}")
        invalidate()
    }

    /**
     * dp转换为px
     */
    private

    fun dp2px(dp: Float): Int {
        val density = mContext!!.resources.displayMetrics.density
        return (dp * density + 0.5f).toInt()
    }

    /**
     * px转换为dp
     */
    private fun px2dp(px: Float): Int {
        val scaledDensity = mContext!!.resources.displayMetrics.scaledDensity
        return (px / scaledDensity + 0.5f).toInt()
    }


}