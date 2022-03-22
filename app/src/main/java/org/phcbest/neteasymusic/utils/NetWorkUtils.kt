package org.phcbest.neteasymusic.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.os.Message
import org.phcbest.neteasymusic.base.BaseApplication

class NetWorkUtils {
    companion object {
        fun isNetWorkConnected(): Boolean {
            //"?:" 在kotlin中 如果左侧是一个null那么会返回右侧
            var result = false
            val connectivityManager =
                BaseApplication.appContext?.getSystemService(Context.CONNECTIVITY_SERVICE)
                        as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val networkCapabilities = connectivityManager.activeNetwork ?: return false
                val actNw =
                    connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
                result = when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            } else {
                //run 函数
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }

            }
            return result
        }

        fun testAndSendNetWorkStateToast() {
            if (!isNetWorkConnected()) {
                val message = Message()
                message.what = ToastUtils.HANDLER_MSG_WHAT
                val bundle = Bundle()
                bundle.putString(ToastUtils.HANDLER_MSG_KEY, "网络未连接")
                message.data = bundle
                ToastUtils.handler.sendMessage(message)
            }
        }
    }
}