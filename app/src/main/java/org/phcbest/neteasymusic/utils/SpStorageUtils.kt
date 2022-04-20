package org.phcbest.neteasymusic.utils

import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson
import org.phcbest.neteasymusic.base.BaseApplication
import org.phcbest.neteasymusic.bean.LoginBean
import retrofit2.Response

class SpStorageUtils {

    companion object {
        public const val SP_NULL = "sp_null"

        private var instance = SpStorageUtils()
        fun newInstance(): SpStorageUtils {
            return instance
        }
    }

    private val loginSp =
        BaseApplication.appContext?.getSharedPreferences("login", MODE_PRIVATE)!!

    fun storageCookie(info: LoginBean) {
        //let是空安全的,不为空才会调用
        info.cookie.let {
            //需要格式化为标准cookie
            loginSp.edit().putString("cookie", it.replace(";;", ";\u0020")).apply()
        }
    }

    fun getCookie(): String {
        val cookie = loginSp.getString("cookie", SP_NULL)
        return cookie!!
    }

    fun storageLoginBean(info: LoginBean) {
        //let是空安全的,不为空才会调用
        info.let {
            //需要格式化为标准cookie
            loginSp.edit().putString("login_bean", Gson().toJson(it)).apply()
        }
    }

    fun getLoginBean(): LoginBean? {
        val cookie = loginSp.getString("login_bean", SP_NULL)
        var loginBean: LoginBean? = null
        cookie.let {
            if (it != SP_NULL) {
                loginBean = Gson().fromJson(it, LoginBean::class.java)
            }
        }
        return loginBean
    }
}