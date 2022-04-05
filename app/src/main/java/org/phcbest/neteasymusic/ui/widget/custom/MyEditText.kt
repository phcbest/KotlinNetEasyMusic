package org.phcbest.neteasymusic.ui.widget.custom

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatEditText

class MyEditText : AppCompatEditText {
    private var lastTime: Long = 0

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        super.onSelectionChanged(selStart, selEnd)
        //在选择更改时,将光标移动到最后一个位置
        this.setSelection(this.text!!.length)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                val currentTime = System.currentTimeMillis()
                if (currentTime - lastTime < 500) {
                    lastTime = currentTime
                    return true
                } else {
                    lastTime = currentTime
                }
            }
        }
        return super.onTouchEvent(event)
    }
}