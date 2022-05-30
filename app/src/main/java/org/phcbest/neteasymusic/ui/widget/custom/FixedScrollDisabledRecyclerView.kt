package org.phcbest.neteasymusic.ui.widget.custom

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView

class FixedScrollDisabledRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : NestedScrollView(context, attrs, defStyleAttr) {

    companion object {
        private const val TAG = "FixedScrollDisabledRecyclerView"
    }

    var contentView: ViewGroup? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        //找到底下的内容区域
        contentView = (getChildAt(0) as ViewGroup).getChildAt(3) as ViewGroup
        Log.i(TAG, "onFinishInflate: ${contentView!!.javaClass.simpleName}")
    }

    /**
     * 在测量的时候计算出内容区域的高度
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        contentView?.layoutParams?.height = measuredHeight

    }

}
