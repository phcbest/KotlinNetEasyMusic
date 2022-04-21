package org.phcbest.neteasymusic.utils

import android.content.Context.MODE_PRIVATE
import android.util.Log
import com.google.gson.Gson
import org.phcbest.neteasymusic.base.BaseApplication
import org.phcbest.neteasymusic.bean.LoginBean
import retrofit2.Response
import java.util.*

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

    fun storageCookie(info: Response<LoginBean>) {
        val s = info.headers()["set-cookie"]
        Log.i("TAG", "storageCookie: $s")
//        info.cookie.let {
//            var cookies = it.split(";;")
//            cookies = cookies.filter { cookie ->
//                cookie.contains("__remember_me") || cookie.contains("__csrf")
//                        || cookie.contains("MUSIC_U") || cookie.contains("NMTID")
//            }
//            val stringBuffer = StringBuffer()
//            cookies.forEach { text ->
//                stringBuffer.append(text).append(";\u0020");
//            }
//            //需要格式化为标准cookie
//            loginSp.edit().putString("cookie", stringBuffer.toString()).apply()
//        }
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