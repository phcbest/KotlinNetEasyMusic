package org.phcbest.neteasymusic.ui.widget.playBar

import android.content.Context
import android.graphics.*
import android.os.CountDownTimer
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.IntRange
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.marginRight
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.utils.CountDownTimeWithPause
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
        //初始化bitmap,设置默认状态为暂停,播放的ui状态要显示为暂停,所以反过来
        mPlayBm = resources.getDrawable(R.drawable.ic_play_bar_pause, null).toBitmap()
        mPauseBm = resources.getDrawable(R.drawable.ic_play_bar_play, null).toBitmap()
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
//        mProgressCDT?.resume()
        invalidate()
    }

    /**
     * 暂停
     */
    fun pause() {
        mBm = mPauseBm
//        mProgressCDT?.pause()
        invalidate()
    }

    /**
     * 复位进度
     */
    fun resetProgress() {
//        mProgressCDT?.cancel()
//        mProgressCDT?.start()?.pause()
        updateProgress(0)
    }

    //进度控制
//    var mProgressCDT: CountDownTimeWithPause? = null

//    /**
//     * 设置音乐时长
//     */
//    fun setMusicDuration(duration: Int) {
//        val durationOfOneDegree = duration / 100
//        Log.i(TAG, "setMusicDuration: $durationOfOneDegree")
//
//        mProgressCDT = object : CountDownTimeWithPause(
//            duration.toLong(),
//            durationOfOneDegree.toLong()
//        ) {
//            var progress: Int = 1
//            override fun onFinish() {
//                //复位进度
//                resetProgress()
//                mProgressCDT?.cancel()
//                mProgressCDT?.start()?.pause()
//                progress = 1
//            }
//
//            override fun onTick(lastTickStart: Long) {
//                updateProgress(progress)
//                progress++
//            }
//        }
//        mProgressCDT?.start()?.pause()
//    }

    /**
     * 进度更新
     */
    fun updateProgress(@IntRange(from = 0, to = 100) progress: Int) {
        this.mSweepAngle = (3.6 * progress).toFloat()
        Log.i(TAG, "更新进度为: ${(3.6 * progress).toFloat()}")
        invalidate()
    }

}