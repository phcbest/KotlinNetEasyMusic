package org.phcbest.neteasymusic.ui.widget.playBar

import android.view.LayoutInflater
import android.view.View
import org.phcbest.neteasymusic.databinding.CustomPlayBarBinding

class CustomPlayBar {

    companion object {
        fun newInstance(): CustomPlayBar {
            return CustomPlayBar()
        }
    }

    private var mBind: CustomPlayBarBinding? = null

    fun initView(view: View): CustomPlayBar {
        mBind = CustomPlayBarBinding.inflate(LayoutInflater.from(view.context))
        return this
    }

    fun SwitchView(view: View) {
        mBind = CustomPlayBarBinding.inflate(LayoutInflater.from(view.context))
    }

    fun setData() {
        mBind!!.tvPlayBarName.text = "四月是你的谎言"
    }

}