package org.phcbest.neteasymusic.ui.widget.custom

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

/**
 * 这个类解决了ViewPager2 制作的banner在滑动的时候和 外层的viewPager2产生的冲突
 */
class ViewPager2Container @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    private var mViewPager2: ViewPager2? = null

    //禁止拦截设为true
    private var disallowParentInterceptDownEvent = false
    private var startX = 0
    private var startY = 0

    //在完成的时候进行
    override fun onFinishInflate() {
        super.onFinishInflate()
        //遍历所有的子view
        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            if (childView is ViewPager2) {
                //如果包含有viewPager2,将viewPager2赋值给成员变量
                mViewPager2 = childView
                break
            }
        }
        if (mViewPager2 == null) {
            //如果没有找到viewPager2,抛出异常
            throw IllegalStateException("The root child of ViewPager2Container must contains a ViewPager2")
        }
    }

    /**
     * 拦截触摸事件
     */
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {

        val doNotNeedIntercept = (!mViewPager2!!.isUserInputEnabled
                || (mViewPager2?.adapter != null
                && mViewPager2?.adapter!!.itemCount <= 1))
        if (doNotNeedIntercept) {
            //如果没有启用用户输入,或者viewPager2的item数量小于等于1,执行当前view拦截
            return super.onInterceptTouchEvent(ev)
        }
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                //保存按下时的起始位置
                startX = ev.x.toInt()
                startY = ev.y.toInt()

                parent.requestDisallowInterceptTouchEvent(!disallowParentInterceptDownEvent)
            }
            MotionEvent.ACTION_MOVE -> {
                val endX = ev.x.toInt()
                val endY = ev.y.toInt()
                //计算xy坐标的滑动距离绝对值
                val disX = abs(endX - startX)
                val disY = abs(endY - startY)
                if (mViewPager2!!.orientation == ViewPager2.ORIENTATION_VERTICAL) {
                    //如果vp2的方向是垂直,进行垂直移动操作
                    onVerticalActionMove(endY, disX, disY)
                } else if (mViewPager2!!.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
                    onHorizontalActionMove(endX, disX, disY)
                }
            }
            //结束event时,设置允许父view拦截
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> parent.requestDisallowInterceptTouchEvent(
                false)
        }
        return super.onInterceptTouchEvent(ev)
    }

    private fun onHorizontalActionMove(endX: Int, disX: Int, disY: Int) {
        if (mViewPager2?.adapter == null) {
            return
        }
        //x轴绝对值大于y轴绝对,有明显的x方向滑动意图
        if (disX > disY) {
            val currentItem = mViewPager2?.currentItem //获得当前item的位置
            val itemCount = mViewPager2?.adapter!!.itemCount //获得item的数量
            if (currentItem == 0 && endX - startX > 0) {
                //如果是第一个item,并且向右滑动,设置允许父view拦截
                parent.requestDisallowInterceptTouchEvent(false)
            } else {
                //计算是否是最后一个item,如果是最后一个item,并且向左滑动,设置允许父view拦截
                parent.requestDisallowInterceptTouchEvent(currentItem != itemCount - 1
                        || endX - startX >= 0)
            }
        } else if (disY > disX) {
            parent.requestDisallowInterceptTouchEvent(false)
        }
    }

    private fun onVerticalActionMove(endY: Int, disX: Int, disY: Int) {
        if (mViewPager2?.adapter == null) {
            return
        }
        val currentItem = mViewPager2?.currentItem
        val itemCount = mViewPager2?.adapter!!.itemCount
        if (disY > disX) {
            if (currentItem == 0 && endY - startY > 0) {
                parent.requestDisallowInterceptTouchEvent(false)
            } else {
                parent.requestDisallowInterceptTouchEvent(currentItem != itemCount - 1
                        || endY - startY >= 0)
            }
        } else if (disX > disY) {
            parent.requestDisallowInterceptTouchEvent(false)
        }
    }

    /**
     * 设置是否允许在当前View的{@link MotionEvent#ACTION_DOWN}事件中禁止父View对事件的拦截，该方法
     * 用于解决CoordinatorLayout+CollapsingToolbarLayout在嵌套ViewPager2Container时引起的滑动冲突问题。
     *
     * 设置是否允许在ViewPager2Container的{@link MotionEvent#ACTION_DOWN}事件中禁止父View对事件的拦截，该方法
     * 用于解决CoordinatorLayout+CollapsingToolbarLayout在嵌套ViewPager2Container时引起的滑动冲突问题。
     *
     * @param disallowParentInterceptDownEvent 是否允许ViewPager2Container在{@link MotionEvent#ACTION_DOWN}事件中禁止父View拦截事件，默认值为false
     *                          true 不允许ViewPager2Container在{@link MotionEvent#ACTION_DOWN}时间中禁止父View的时间拦截，
     *                          设置disallowIntercept为true可以解决CoordinatorLayout+CollapsingToolbarLayout的滑动冲突
     *                          false 允许ViewPager2Container在{@link MotionEvent#ACTION_DOWN}时间中禁止父View的时间拦截，
     */
    fun disallowParentInterceptDownEvent(disallowParentInterceptDownEvent: Boolean) {
        this.disallowParentInterceptDownEvent = disallowParentInterceptDownEvent
    }
}