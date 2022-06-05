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
                    //todo 需要让动画结束后保持最后一帧的位置
                    it.repeatMode = ObjectAnimator.REVERSE
                }
            }
            return this
        } else {
            throw IllegalArgumentException("view is not PlayDiscView,cannotFind discView or needleView")
        }
    }

    /**
     * 播放的ui表现
     */
    fun play() {
        discView?.objectAnimator?.resume()
        //将指针放在播放位
        needleAnimator?.start()
    }

    /**
     * 暂停的ui表现
     */
    fun stop() {
        discView?.objectAnimator?.pause()
        //将指针移开播放位
        needleAnimator?.start()
    }


}