package org.phcbest.neteasymusic.ui.widget.custom

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * 解决RecyclerView嵌套ViewPager2滑动冲突
 */
class RecyclerViewContainer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : RecyclerView(context, attrs, defStyleAttr) {
    companion object {
        private const val TAG = "RecyclerViewContainer"
    }


    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        var starX: Float = 0.0f
        var starY: Float = 0.0f
        when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d(TAG, "onInterceptTouchEvent: ACTION_DOWN")
                //记录移动的位置
                starX = e.x
                starY = e.y
                parent.requestDisallowInterceptTouchEvent(false)
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d(TAG, "onInterceptTouchEvent: ACTION_MOVE")
//                layoutManager
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                Log.d(TAG, "onInterceptTouchEvent: ACTION_UP ACTION_CANCEL")
                parent.requestDisallowInterceptTouchEvent(false)
            }

        }
        //判断用户是否滑动到最后一个item
        if (layoutManager is LinearLayoutManager) {
            val lm = layoutManager as LinearLayoutManager
            //获得最后一个可见的item的位置
            val pos = lm.findLastCompletelyVisibleItemPosition()
            val itemCount = this.adapter?.itemCount
            if (pos == itemCount?.minus(1)) {
                Log.d(TAG, "onInterceptTouchEvent: 滑动到最后一个item")
                //允许传递到下一个控件
                return true
            }
        } else if (layoutManager is StaggeredGridLayoutManager) {
            val lm = layoutManager as StaggeredGridLayoutManager
            //获得最后一个可见的item的位置
            val pos = lm.findLastCompletelyVisibleItemPositions(null)
            val itemCount = this.adapter?.itemCount
            for (i in pos) {
                if (i == itemCount?.minus(1)) {
                    Log.d(TAG, "onInterceptTouchEvent: 滑动到最后一个item")
                    //允许传递到下一个控件
                    return true
                }
            }
        }
        return super.onInterceptTouchEvent(e)
    }
}