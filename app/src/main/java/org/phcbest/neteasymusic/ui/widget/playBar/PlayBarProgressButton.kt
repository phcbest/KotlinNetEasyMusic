package org.phcbest.neteasymusic.ui.widget.playBar

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.annotation.FloatRange
import androidx.core.content.ContextCompat
import org.phcbest.neteasymusic.R


class PlayBarProgressButton(context: Context, attributeSet: AttributeSet) :
    View(context, attributeSet) {

    private var mContext: Context = context

    private val DEFAULT_SIZE: Int = 40
    private val HALF = 0.5f
    private val ONE_TENTH = 0.1f
    private val DEFAULT_START_ANGLE = -90f
    private var defaultSize = 0
    private var mWidth = 0
    private var mHeight = 0
    private var mBgPaint: Paint? = null
    private var mProgressPaint: Paint? = null

    // 背景颜色和进度条颜色
    private var mBgColor = 0
    private var mProgressColor = 0

    // 背景圆
    private var radius = 0
    private var circlePoint = 0

    // 播放暂停按钮
    private var mPlayBm: Bitmap? = null
    private var mPauseBm: Bitmap? = null
    private var mBitmap: Bitmap? = null
    private var mDstRect: Rect? = null
    private var mBmOffset = 0

    // 进度条
    private var mProgressRectF: RectF? = null
    private var mProgressWidth = 0

    // 进度条开始结束角度
    private var mStartAngle = 0f
    private var mSweepAngle = 0f

    init {
        init()
    }


    private fun init() {
        //计算默认sizi
        defaultSize = dp2px(DEFAULT_SIZE.toFloat())
        //计算进度条宽度
        mProgressWidth = (defaultSize * ONE_TENTH).toInt()
        //计算圆形的半径
        radius = (defaultSize * HALF - mProgressWidth * HALF).toInt()
        circlePoint = (defaultSize * HALF).toInt()

        mBgColor = ContextCompat.getColor(mContext!!, R.color.progress_bg_color)
        mProgressColor = ContextCompat.getColor(mContext!!, R.color.progress_color)

        //暂停和播放按钮的绘制区域
        mBmOffset = (defaultSize * HALF * HALF).toInt()
        mDstRect = Rect(mBmOffset, mBmOffset, defaultSize - mBmOffset, defaultSize - mBmOffset)
        mPlayBm = BitmapFactory.decodeResource(mContext!!.resources, R.drawable.ic_play_bar_play)
        mPauseBm = BitmapFactory.decodeResource(mContext!!.resources, R.drawable.ic_play_bar_pause)
        //设置默认状态为暂停
        mBitmap = mPlayBm

        mBgPaint = Paint()
        mBgPaint?.isAntiAlias = true
        mBgPaint?.color = mBgColor

        mProgressPaint = Paint()
        mProgressPaint?.isAntiAlias = true
        mProgressPaint?.color = mProgressColor
        mProgressPaint?.strokeWidth = mProgressWidth.toFloat()
        mProgressPaint?.style = Paint.Style.STROKE

        //进度条绘制
        RectF(0F, 0F, defaultSize.toFloat(), defaultSize.toFloat()).also { mProgressRectF = it }
        //起始角度
        this.mStartAngle = DEFAULT_START_ANGLE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        this.mWidth = w
        this.mHeight = h
        if (height > 0) {
            //修改进度条的宽度
            mProgressWidth = (mWidth * ONE_TENTH).toInt()
            mProgressPaint?.strokeWidth = mProgressWidth.toFloat()
            //修改背景圆的半径
            radius = (mWidth * HALF - mProgressWidth * HALF).toInt()
            //修改圆心
            circlePoint = (mWidth * HALF).toInt()
            //暂停、播放按钮的绘制区域
            mBmOffset = (mWidth * HALF * HALF).toInt()
            mDstRect = Rect(mBmOffset, mBmOffset, mWidth - mBmOffset, mWidth - mBmOffset)
            val size = mWidth - mProgressWidth * HALF
            //进度条
            mProgressRectF = RectF(mProgressWidth * HALF, mProgressWidth * HALF, size, size)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        /**
         * 此方法必须由 onMeasure(int, int) 调用以存储测量的宽度和测量的高度。否则将在测量时触发异常。
         */
        setMeasuredDimension(getSize(widthMeasureSpec), getSize(heightMeasureSpec))
    }

    private fun getSize(measureSpec: Int): Int {
        var result = 0
        val mode = MeasureSpec.getMode(measureSpec)
        val size = MeasureSpec.getSize(measureSpec)
        if (mode == MeasureSpec.EXACTLY) {
            //确切大小并将给到的尺寸给view
            result = size
        } else if (mode == MeasureSpec.AT_MOST) {
            //默认为40dp，这里结合父控件给子控件留的大小，采用最小值
            result = Math.min(defaultSize, size)
        } else {
            result = defaultSize
        }
        return result
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //绘制背景圆
        canvas.drawCircle(
            circlePoint.toFloat(),
            circlePoint.toFloat(),
            radius.toFloat(),
            mBgPaint!!
        )
        //绘制播放暂停按钮
        canvas.drawBitmap(mBitmap!!, null, mDstRect!!, mBgPaint)
        //绘制进度条
        canvas.drawArc(mProgressRectF!!, mStartAngle, mSweepAngle, false, mProgressPaint!!)
    }

    /**
     * 刷新暂停ui
     */
    fun pause() {
        mBitmap = mPlayBm
        invalidate()
    }

    /**
     * @param startAngle 开始角度
     * @param sweepAngle 扫过角度
     */
    fun updateProgress(
        @FloatRange(from = -360.0, to = 360.0) startAngle: Float,
        @FloatRange(from = 0.0, to = 360.0) sweepAngle: Float
    ) {
        mStartAngle = startAngle
        mSweepAngle = sweepAngle
        invalidate()
    }

    /**
     * 更新进度.
     *
     * @param sweepAngle 扫过角度
     */
    fun updateProgress(@FloatRange(from = 0.0, to = 360.0) sweepAngle: Float) {
        mSweepAngle = sweepAngle
        invalidate()
    }

    /**
     * dp转换为px
     */
    private fun dp2px(dp: Float): Int {
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