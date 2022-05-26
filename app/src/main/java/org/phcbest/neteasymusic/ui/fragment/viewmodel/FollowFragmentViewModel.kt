package org.phcbest.neteasymusic.ui.fragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tencent.mmkv.MMKV
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.phcbest.neteasymusic.bean.UserFollowBean
import org.phcbest.neteasymusic.utils.MMKVStorageUtils
import org.phcbest.neteasymusic.utils.RetrofitUtils

class FollowFragmentViewModel : ViewModel() {
    companion object {
        private const val TAG = "FollowFragmentViewModel"
    }

    var userFollowBeanLD: MutableLiveData<UserFollowBean?> = MutableLiveData()
    fun getUserFollowBeanLD() {
        RetrofitUtils.newInstance()
            .getUserFollows(MMKVStorageUtils.newInstance().getLoginBean()!!.profile.userId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe({
                userFollowBeanLD.postValue(it)
            }, {
                it.printStackTrace()
                userFollowBeanLD.postValue(null)
            })
    }
}