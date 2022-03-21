package org.phcbest.neteasymusic.utils

import android.view.Gravity
import android.widget.Toast
import org.phcbest.neteasymusic.base.BaseApplication

class ToastUtils {
    companion object {
        private var makeText: Toast? = null

        fun showToast(text: String) {
            //防止toast重复弹窗
            if (makeText == null) {
                makeText = Toast.makeText(BaseApplication.appContext, text, Toast.LENGTH_SHORT)
            } else {
                makeText!!.setText(text)
            }
            //toast显示在顶部,y轴偏移200个单位
            makeText!!.setGravity(Gravity.TOP, 0, 200)
            makeText!!.show()
        }
    }

}