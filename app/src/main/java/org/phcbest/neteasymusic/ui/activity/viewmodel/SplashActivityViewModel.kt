package org.phcbest.neteasymusic.ui.activity.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.phcbest.neteasymusic.presenter.ILoginPresenter
import org.phcbest.neteasymusic.presenter.PresenterManager

class SplashActivityViewModel : ViewModel() {
    companion object {
        private const val TAG = "SplashActivityViewModel"
    }

    private val loginPresenter: ILoginPresenter = PresenterManager.getInstance().getLoginPresenter()

    var loginStatus: MutableLiveData<Boolean> = MutableLiveData()

    fun checkCookieState() {
        loginPresenter.getUserAccount({ userAccountBean ->
            //如果cookie过期是无法获取到这两条数据的
            if (userAccountBean?.account == null || userAccountBean.profile == null) {
                //要求刷新cookie
                this.refreshLogin()
            } else {
                this.loginStatus.postValue(true)
            }
        }, {})
    }

    fun refreshLogin() {
        loginPresenter.refreshLogin({

            if (it["code"] == 200) {
                this.loginStatus.postValue(true)
            }
        }, {})
    }
}