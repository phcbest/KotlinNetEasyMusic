package org.phcbest.neteasymusic.utils

import android.util.Log
import com.google.gson.Gson
import com.tencent.mmkv.MMKV
import org.phcbest.neteasymusic.bean.LoginBean
import retrofit2.Response

class SpStorageUtils {

    companion object {
        public const val SP_NULL = "sp_null"

        private const val TAG = "SpStorageUtils"
        private var instance = SpStorageUtils()
        fun newInstance(): SpStorageUtils {
            return instance
        }
    }

//    private val loginSp =
//        BaseApplication.appContext?.getSharedPreferences("login", MODE_PRIVATE)!!

    private val logigMMKV = MMKV.mmkvWithID("login")

    fun storageCookie(info: Response<LoginBean>) {
        val setCookieHandler = info.raw().headers("Set-Cookie")
        for (cookie in setCookieHandler) {
            if (cookie.contains("__remember_me")) {
                logigMMKV.encode("cookie-__remember_me", "$cookie\u0020")
//                loginSp.edit().putString("cookie-__remember_me", "$cookie\u0020").apply()
            }
            if (cookie.contains("__csrf")) {
                logigMMKV.encode("cookie-__csrf", "$cookie\u0020")
//                loginSp.edit().putString("cookie-__csrf", "$cookie\u0020").apply()
            }
            if (cookie.contains("MUSIC_U")) {
                logigMMKV.encode("cookie-MUSIC_U", "$cookie\u0020")
//                loginSp.edit().putString("cookie-MUSIC_U", "$cookie\u0020").apply()
            }
            if (cookie.contains("NMTID")) {
                logigMMKV.encode("cookie-NMTID", "$cookie\u0020")
//                loginSp.edit().putString("cookie-NMTID", "$cookie\u0020").apply()
            }
        }
    }

    fun updateCookieNMTID(refresh: Response<Map<String, Int>>) {
        Log.i(TAG, "updateCookieNMTID: ${refresh.raw().headers("Set-Cookie")}")
        val s = refresh.raw().headers("Set-Cookie").filter { it.contains("NMTID") }

        if (s.size == 1) {
            logigMMKV.encode("cookie-NMTID", "$s\u0020")
//            loginSp.edit().putString("cookie-NMTID", "$s\u0020").apply()
        }
    }

    fun getCookie(): String {
        return "${
            logigMMKV.decodeString("cookie-__remember_me", SP_NULL)
//            loginSp.getString(
//                "cookie-__remember_me",
//                SP_NULL
//            )
        }${
            logigMMKV.decodeString("cookie-__csrf", SP_NULL)
//            loginSp.getString(
//                "cookie-__csrf",
//                SP_NULL
//            )
        }${
            logigMMKV.decodeString("cookie-MUSIC_U", SP_NULL)
//            loginSp.getString(
//                "cookie-MUSIC_U",
//                SP_NULL
//            )
        }${
            logigMMKV.decodeString("cookie-NMTID", SP_NULL)
//            loginSp.getString(
//                "cookie-NMTID",
//                SP_NULL
//            )
        }"
    }

    fun storageLoginBean(info: LoginBean) {
        //let是空安全的,不为空才会调用
        info.let {
            //需要格式化为标准cookie
            logigMMKV.encode("login_bean", Gson().toJson(it))
//            loginSp.edit().putString("login_bean", Gson().toJson(it)).apply()
        }
    }

    fun getLoginBean(): LoginBean? {
        val cookie = logigMMKV.decodeString("login_bean", SP_NULL)
//        val cookie = loginSp.getString("login_bean", SP_NULL)
        var loginBean: LoginBean? = null
        cookie.let {
            if (it != SP_NULL) {
                loginBean = Gson().fromJson(it, LoginBean::class.java)
            }
        }
        return loginBean
    }
}