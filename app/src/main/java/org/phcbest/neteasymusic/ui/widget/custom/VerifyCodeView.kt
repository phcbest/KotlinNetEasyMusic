package org.phcbest.neteasymusic.ui.widget.custom

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import org.phcbest.neteasymusic.R

class VerifyCodeView : RelativeLayout {

    private var editText: EditText? = null
    private var textViews: MutableList<TextView>? = arrayListOf()
    private var MAX: Int = 6
    private var inputContent: String? = null

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        View.inflate(context, R.layout.custom_verify_code_view, this)
        textViews?.set(0, findViewById(R.id.tv_verify_0))
        textViews?.set(1, findViewById(R.id.tv_verify_1))
        textViews?.set(2, findViewById(R.id.tv_verify_2))
        textViews?.set(3, findViewById(R.id.tv_verify_3))
        textViews?.set(4, findViewById(R.id.tv_verify_4))
        textViews?.set(5, findViewById(R.id.tv_verify_5))
        editText = findViewById(R.id.et_verify)

        setEditTextListener()
    }

    private fun setEditTextListener() {
        editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

}