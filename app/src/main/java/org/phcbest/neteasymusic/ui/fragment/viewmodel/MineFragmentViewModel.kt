package org.phcbest.neteasymusic.ui.fragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.phcbest.neteasymusic.bean.UserDetailBean
import org.phcbest.neteasymusic.utils.RetrofitUtils
import org.phcbest.neteasymusic.utils.SpStorageUtils

class MineFragmentViewModel : ViewModel {

    private var userDetailBean: MutableLiveData<UserDetailBean> = MutableLiveData()

    constructor() {
//        setUserDetail()
    }

    /**
     * 联网访问用户信息
     */
    fun setUserDetail() {
        val loginBean = SpStorageUtils.newInstance().getLoginBean()
        RetrofitUtils.newInstance().getUserDetail(loginBean?.account?.id.toString())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe({ userDetailBean ->
//                ObservableField
                this.userDetailBean.postValue(userDetailBean)
            }, {
                this.userDetailBean.postValue(null)
            })
    }

    fun getUserDetail(): LiveData<UserDetailBean> {
        return this.userDetailBean
    }

}