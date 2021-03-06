package org.phcbest.neteasymusic.ui.widget.playBar

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.ui.widget.tool.CustomUtils
import org.phcbest.neteasymusic.utils.Constants


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

    //旋转角度
    private var degrees = 0f


    private var handlerTurn: Handler? = null

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
        mFrontBm =
            CustomUtils.getInstance().getCircleBitmap(resources.getDrawable(R.drawable.cd_fg_sample,
                null)
                .toBitmap())
        //矩形和图画参数
        mBackPaint.isAntiAlias = true //抗锯齿
        mFrontPaint.isAntiAlias = true //抗锯齿
        //配置动画
        val cdAnimation =
//            AnimationUtils.loadAnimation(mContext, R.anim.anim_play_bar_cd_turn)
            ObjectAnimator.ofFloat(this, "rotation", 0F, 360f)
        cdAnimation.duration = 30000
        cdAnimation.repeatCount = -1
        cdAnimation.start()
        cdAnimation.pause()
        cdAnimation.interpolator = LinearInterpolator() //设置匀速转动
        //创建handler用于旋转view
        handlerTurn = Handler(Looper.myLooper()!!) { msg ->
            if (msg.what == Constants.HandlerParamKey.HANDLER_TURN_IMAGE_COVER_START) {
                //配置转动动画
                cdAnimation.resume()
//                this.startAnimation(cdAnimation)
            } else if (msg.what == Constants.HandlerParamKey.HANDLER_TURN_IMAGE_COVER_STOP) {
                //暂停转动动画
//                this.clearAnimation()
                cdAnimation.pause()
            }
            true
        }
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
        //画布旋转
        canvas?.rotate(degrees, (width / 2).toFloat(), (height / 2).toFloat())
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

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopTurn()
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
                    mFrontBm = CustomUtils.getInstance().getCircleBitmap(resource)
                    //刷新
                    invalidate()
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })

        return this
    }

    /**
     * 开始旋转
     */
    fun startTurn() {
        Constants.HandlerParamKey.HANDLER_TURN_IMAGE_COVER_START.let {
            val message = Message()
            message.what = it
            handlerTurn!!.sendMessage(message)
        }
    }

    /**
     * 停止旋转
     */
    fun stopTurn() {
        Constants.HandlerParamKey.HANDLER_TURN_IMAGE_COVER_STOP.let {
            val message = Message()
            message.what = it
            handlerTurn!!.sendMessage(message)
        }
    }

}