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
    private var MAX: Int = 4
    private var inputContent: String? = null

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        View.inflate(context, R.layout.custom_verify_code_view, this)
        textViews?.add(0, findViewById(R.id.tv_verify_0))
        textViews?.add(1, findViewById(R.id.tv_verify_1))
        textViews?.add(2, findViewById(R.id.tv_verify_2))
        textViews?.add(3, findViewById(R.id.tv_verify_3))
//        textViews?.add(4, findViewById(R.id.tv_verify_4))
//        textViews?.add(5, findViewById(R.id.tv_verify_5))
        editText = findViewById(R.id.et_verify_code_input)

        setEditTextListener()
    }

    private fun setEditTextListener() {
        editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                inputContent = editText?.text.toString()
                if (inputCompleteListener != null) {
                    if (inputContent!!.length >= MAX) {
                        inputCompleteListener!!.inputComplete()
                    } else {
                        inputCompleteListener!!.invalidComplete()
                    }
                }
                for (index in 0 until MAX) {
                    if (index < inputContent!!.length) {
                        textViews?.get(index)?.text = inputContent!![index].toString()
                    } else {
                        textViews?.get(index)?.text = ""
                    }
                }
            }
        })
    }

    private var inputCompleteListener: InputCompleteListener? = null
    public fun setInputCompleteListener(inputCompleteListener: InputCompleteListener) {
        this.inputCompleteListener = inputCompleteListener
    }

    public interface InputCompleteListener {
        fun inputComplete()
        fun invalidComplete()
    }

    public fun getEditContent(): String {
        return inputContent!!
    }

}