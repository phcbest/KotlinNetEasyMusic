package org.phcbest.neteasymusic.ui.widget.playDisc

import android.animation.ObjectAnimator
import android.util.Log
import android.view.View
import android.widget.ImageView
import org.phcbest.neteasymusic.R

class PlayDiscHelper {

    companion object {
        private const val TAG = "PlayDiscManipulator"
    }

    private var discView: DiscRotateImageView? = null
    private var needleView: ImageView? = null
    private var needleAnimator: ObjectAnimator? = null

    fun setView(view: View): PlayDiscHelper {
        //cd
        discView = view.findViewById(R.id.iv_play_disc)
        //顶针(不是
        needleView = view.findViewById(R.id.iv_play_needle)
        if (discView != null && needleView != null) {
            //获取指针view的宽高
            needleAnimator = ObjectAnimator.ofFloat(needleView, View.ROTATION, 0F, -40F)
            needleView!!.post {
                //设置居中点
                Log.i(TAG,
                    "setView 指针: 宽 ${needleView!!.measuredWidth} 高${needleView!!.measuredHeight}")
                val xShifting = needleView!!.measuredWidth * 0.18f
                val yShifting = needleView!!.measuredHeight * 0.11f
                Log.i(TAG, "setView: 指针偏移量 x $xShifting y $yShifting")
                needleView!!.pivotX = xShifting
                needleView!!.pivotY = yShifting
                needleAnimator?.let {
                    it.duration = 1000
                    it.repeatCount = 0
                    it.repeatMode = ObjectAnimator.REVERSE
                }
            }
            return this
        } else {
            throw IllegalArgumentException("view is not PlayDiscView,cannotFind discView or needleView")
        }
    }


    /**
     * 枚举指针状态
     */
    enum class NEEDLE_STATE {
        OVERLAY, LEAVE
    }

    /**
     * 设置指针初始位置
     * @param place @see NEEDLE_STATE 枚举
     */
    fun setInitNeedlePlace(place: NEEDLE_STATE) {
        if (place == NEEDLE_STATE.LEAVE) {
            needleView?.rotation = -40f
        } else if (place == NEEDLE_STATE.OVERLAY) {
            needleView?.rotation = 0F
        } else {
            throw IllegalArgumentException("place is not a valid value")
        }
    }

    /**
     * 设置cd的部分信息
     */
    fun setDiscInfo(imageUrl: String) {
        discView?.setFrontBitmap(imageUrl)
    }

    /**
     * 播放的ui表现
     */
    fun play() {
        discView?.objectAnimator?.resume()
        //将指针移开播放位
        needleAnimator?.reverse()
    }

    /**
     * 暂停的ui表现
     */
    fun stop() {
        discView?.objectAnimator?.pause()
        //将指针放在播放位
        needleAnimator?.start()
    }

    /**
     * 执行切换Needle的动画
     */
    fun switchNeedle(animDoneEvent: () -> Unit) {
        //获得needle位置
        val rotation = needleView?.rotation ?: 0F
        if (rotation == 0F) {
            //执行needle移开后归位的动画
            needleAnimator?.setFloatValues(0F, -40F)
        } else {
            //执行needle归位
        }
    }


}