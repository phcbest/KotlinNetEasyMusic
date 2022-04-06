package org.phcbest.neteasymusic.utils

import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson
import org.phcbest.neteasymusic.base.BaseApplication
import org.phcbest.neteasymusic.bean.LoginBean

class SpStorageUtils {

    companion object {
        private const val SP_NULL = "sp_null"

        var instance = SpStorageUtils()
        fun newInstance(): SpStorageUtils {
            return instance
        }
    }

    private val loginSp =
        BaseApplication.appContext?.getSharedPreferences("login", MODE_PRIVATE)!!

    fun storageLoginInfo(info: String) {
        loginSp.edit().putString("loginInfo", info).apply()
    }

    fun getLoginInfo(): LoginBean? {
        val info = loginSp.getString("loginInfo", SP_NULL)
        var loginBean: LoginBean? = null
        if (info != SP_NULL) {
            loginBean = Gson().fromJson(info, LoginBean::class.java)
        }
        return loginBean
    }

}