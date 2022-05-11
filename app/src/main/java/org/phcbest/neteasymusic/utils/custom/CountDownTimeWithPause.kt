package org.phcbest.neteasymusic.utils.custom

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.os.SystemClock
import android.util.Log
import org.phcbest.neteasymusic.utils.Constants


/**
 * 一个可以暂停的countdowntime
 */
abstract class CountDownTimeWithPause(
    millisInFuture: Long,
    countDownInterval: Long,
) {
    companion object {
        private const val TAG = "CountDownTimeWithPause"
    }

    /**
     *总记时
     */
    private var mMillisInFuture: Long = millisInFuture

    /**
     * 时间间隔
     */
    private var mCountdownInterval: Long = countDownInterval

    private var mStopTimeInFuture: Long = 0

    private var mPauseTime: Long = 0

    private var mCancelled = false

    private var mPaused = false


    /**
     * 控制必须是线程安全的
     */
    @Synchronized
    fun start(): CountDownTimeWithPause {
        Log.i(TAG, "全部记时:$mMillisInFuture  记时间隔$mCountdownInterval ")
        //如果计时时间小于0直接结束
        if (mMillisInFuture <= 0) {
            onFinish()
            return this
        }
        //停止时间为当前时间+计时时间
        mStopTimeInFuture = SystemClock.elapsedRealtime() + mMillisInFuture
        //给handler发消息
        mHandler.sendMessage(mHandler.obtainMessage(Constants.HandlerParamKey.HANDLER_CDT))
        //状态置为false
        mCancelled = false
        mPaused = false
        return this
    }

    /**
     * 取消定时器
     */
    fun cancel() {
        mHandler.removeMessages(Constants.HandlerParamKey.HANDLER_CDT)
        mCancelled = true
    }

    /**
     * 暂停
     */
    fun pause(): Long {
        if (!mPaused) {
            mPauseTime = mStopTimeInFuture - SystemClock.elapsedRealtime()
            mPaused = true
        }
        return mPauseTime
    }


    /**
     * 继续
     */
    fun resume(): Long {
        if (mPaused) {
            mStopTimeInFuture = mPauseTime + SystemClock.elapsedRealtime()
            mPaused = false
            mHandler.sendMessage(mHandler.obtainMessage(Constants.HandlerParamKey.HANDLER_CDT))
        }
        return mPauseTime
    }


    abstract fun onFinish()


    abstract fun onTick(lastTickStart: Long)


    /**
     * 用handle来进行倒计时
     */
    private var mHandler = object : Handler(Looper.myLooper()!!) {

        override fun handleMessage(msg: Message) {
            synchronized(this) {
                //这里计算出来的是剩余倒计时时间
                if (!mPaused) {
                    val millisLeft = mStopTimeInFuture - SystemClock.elapsedRealtime()
                    Log.i(TAG, "倒计时剩余时间: $millisLeft")
                    if (millisLeft <= 0) {
                        //剩余时间小于等于0就结束
                        onFinish()
                    } else if (millisLeft < mCountdownInterval) {
                        //剩余时间小于倒计时时间间隔,发送一次延迟时间的消息
                        sendMessageDelayed(
                            obtainMessage(Constants.HandlerParamKey.HANDLER_CDT),
                            millisLeft
                        )
                    } else {
                        //时间刚刚好,执行一次
                        val lastTickStart = SystemClock.elapsedRealtime()
                        //将当前时间传出去
                        onTick(millisLeft)
                        //去除上一次系统时间和当前系统时间的误差,计算出的数值为下一onTick间隔时间
                        var delay =
                            lastTickStart + mCountdownInterval - SystemClock.elapsedRealtime()
                        while (delay < 0) delay += mCountdownInterval
                        if (!mCancelled) {
                            sendMessageDelayed(
                                obtainMessage(Constants.HandlerParamKey.HANDLER_CDT),
                                delay
                            )
                        }
                    }
                }
            }
        }
    }
}