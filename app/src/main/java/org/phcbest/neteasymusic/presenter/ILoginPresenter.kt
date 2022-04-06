package org.phcbest.neteasymusic.presenter

import org.phcbest.neteasymusic.bean.LoginBean
import org.phcbest.neteasymusic.bean.SongListBean

interface ILoginPresenter {
    fun getCaptcha(
        phone: String, success: (Map<String, Any>) -> Unit,
        error: (Throwable) -> Unit
    )

    fun login(
        phone: String,
        captcha: String,
        success: (LoginBean) -> Unit,
        error: (Throwable) -> Unit
    )
}