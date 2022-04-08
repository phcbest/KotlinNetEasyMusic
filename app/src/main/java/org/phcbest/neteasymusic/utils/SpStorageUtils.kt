package org.phcbest.neteasymusic.utils

import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson
import org.phcbest.neteasymusic.base.BaseApplication
import org.phcbest.neteasymusic.bean.LoginBean

class SpStorageUtils {

    companion object {
        public const val SP_NULL = "sp_null"

        var instance = SpStorageUtils()
        fun newInstance(): SpStorageUtils {
            return instance
        }
    }

    private val loginSp =
        BaseApplication.appContext?.getSharedPreferences("login", MODE_PRIVATE)!!

    fun storageCookie(info: LoginBean) {
        //let是空安全的,不为空才会调用
        info.cookie.let { loginSp.edit().putString("cookie", it).apply() }
    }

    fun getCookie(): String {
        val cookie = loginSp.getString("cookie", SP_NULL)
        return cookie!!
    }

}