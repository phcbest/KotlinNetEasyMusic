package org.phcbest.neteasymusic.presenter

import org.phcbest.neteasymusic.bean.LoginBean
import org.phcbest.neteasymusic.bean.SongListBean
import org.phcbest.neteasymusic.bean.UserAccountBean
import retrofit2.Response

interface ILoginPresenter {
    fun getCaptcha(
        phone: String, success: (Map<String, Any>) -> Unit,
        error: (Throwable) -> Unit
    )

    fun login(
        phone: String,
        captcha: String,
        success: (Response<LoginBean>) -> Unit,
        error: (Throwable) -> Unit
    )

    /**
     * 刷新登录
     */
    fun refreshLogin(success: (Response<Map<String, Int>>) -> Unit, error: (Throwable) -> Unit)

    /**
     * 获得用户账号信息
     */
    fun getUserAccount(success: (UserAccountBean?) -> Unit, error: (Throwable) -> Unit)
}