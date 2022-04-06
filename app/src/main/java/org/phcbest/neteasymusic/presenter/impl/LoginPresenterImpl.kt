package org.phcbest.neteasymusic.presenter.impl

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.phcbest.neteasymusic.bean.LoginBean
import org.phcbest.neteasymusic.presenter.ILoginPresenter
import org.phcbest.neteasymusic.utils.RetrofitUtils

class LoginPresenterImpl : ILoginPresenter {
    override fun getCaptcha(
        phone: String,
        success: (Map<String, Any>) -> Unit,
        error: (Throwable) -> Unit
    ) {
        RetrofitUtils.newInstance().getCaptcha(phone).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> success(result) }, { throwable ->
                throwable.printStackTrace()
                error(throwable)
            })
    }

    override fun login(
        phone: String,
        captcha: String,
        success: (LoginBean) -> Unit,
        error: (Throwable) -> Unit
    ) {
        RetrofitUtils.newInstance().login(phone, captcha).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> success(result) }, { throwable ->
                throwable.printStackTrace()
                error(throwable)
            })
    }
}