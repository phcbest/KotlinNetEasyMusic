package org.phcbest.neteasymusic.ui.fragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tencent.mmkv.MMKV
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.phcbest.neteasymusic.base.PAGE_STATE
import org.phcbest.neteasymusic.bean.UserFollowBean
import org.phcbest.neteasymusic.utils.MMKVStorageUtils
import org.phcbest.neteasymusic.utils.RetrofitUtils
import retrofit2.http.Query

class FollowFragmentViewModel : ViewModel() {
    companion object {
        private const val TAG = "FollowFragmentViewModel"
    }

    var userFollowBeanLD: MutableLiveData<UserFollowBean?> = MutableLiveData()


    //liveData状态管理
    val dataState: MutableLiveData<Map<String, PAGE_STATE>> = MutableLiveData()
    private var dataStateMap = HashMap<String, PAGE_STATE>()

    private val stateFollowList = "stateFollowList"

    init {
        dataStateMap[stateFollowList] = PAGE_STATE.FAIL
    }

    fun getUserFollowBeanLD(limit: Int, offset: Int) {
        RetrofitUtils.newInstance()
            .getUserFollows(MMKVStorageUtils.newInstance().getLoginBean()!!.profile.userId,
                limit,
                offset)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe({
                dataStateMap[stateFollowList] = PAGE_STATE.SUCCESS
                dataState.postValue(dataStateMap)
                userFollowBeanLD.postValue(it)
            }, {
                dataStateMap[stateFollowList] = PAGE_STATE.FAIL
                dataState.postValue(dataStateMap)
                it.printStackTrace()
                userFollowBeanLD.postValue(null)
            })
    }
}