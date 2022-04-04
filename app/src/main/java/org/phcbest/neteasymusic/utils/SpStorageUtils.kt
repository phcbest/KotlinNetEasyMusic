package org.phcbest.neteasymusic.utils

import android.content.Context.MODE_PRIVATE
import org.phcbest.neteasymusic.base.BaseApplication

class SpStorageUtils {

    companion object {
        const val SP_NULL = "sp_null"

        var instance = SpStorageUtils()
        fun newInstance(): SpStorageUtils {
            return instance
        }
    }

    private val loginSp =
        BaseApplication.appContext?.getSharedPreferences("login", MODE_PRIVATE)!!

    fun storageCookie(cookie: String) {
        loginSp.edit().putString("cookie", cookie).apply()
    }

    fun getCookie(): String {
        return loginSp.getString("cookie", SP_NULL)!!
    }

}