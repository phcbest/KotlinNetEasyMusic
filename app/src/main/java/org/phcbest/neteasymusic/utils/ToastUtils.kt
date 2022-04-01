package org.phcbest.neteasymusic.utils

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.Gravity
import android.widget.Toast
import org.phcbest.neteasymusic.base.BaseApplication

class ToastUtils {
    companion object {
        private var makeText: Toast? = null

        /**
         * 运行在ui线程的handler
         */
        const val HANDLER_MSG_WHAT: Int = Constant.HandlerParamKey.HANDLER_MSG_WHAT
        const val HANDLER_MSG_KEY: String = "toast_msg"
        val handler = Handler(Looper.getMainLooper()) { msg ->
            if (msg.what == HANDLER_MSG_WHAT) {
                msg.data.getString(HANDLER_MSG_KEY)?.let { showToast(it) }
            }
            true
        }

        private fun showToast(text: String) {

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