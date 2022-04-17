package org.phcbest.neteasymusic.ui.fragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import org.phcbest.neteasymusic.bean.UserAccountBean
import org.phcbest.neteasymusic.utils.RetrofitUtils

class MineFragmentViewModel : ViewModel {

    private var userAccountBean: LiveData<UserAccountBean>? = null

    constructor() {
        setUserAccount()
    }

    /**
     * 联网访问用户信息
     */
    fun setUserAccount() {
        RetrofitUtils.newInstance().getUserAccount()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe({ userAccountBean ->
                val data = MutableLiveData<UserAccountBean>()
                data.value = userAccountBean
                this.userAccountBean = data
            }, {
                this.userAccountBean = null
            })
    }

    fun getUserAccount(): LiveData<UserAccountBean>? {
        return this.userAccountBean
    }

}