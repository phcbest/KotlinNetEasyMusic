package org.phcbest.neteasymusic.ui.fragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.phcbest.neteasymusic.base.PAGE_STATE
import org.phcbest.neteasymusic.bean.HotTopicBean
import org.phcbest.neteasymusic.bean.UserEventBean
import org.phcbest.neteasymusic.bean.UserFollowBean
import org.phcbest.neteasymusic.utils.MMKVStorageUtils
import org.phcbest.neteasymusic.utils.RetrofitUtils

class FollowFragmentViewModel : ViewModel() {
    companion object {
        private const val TAG = "FollowFragmentViewModel"
    }

    var userFollowBeanLD: MutableLiveData<UserFollowBean?> = MutableLiveData()
    var hotTopicBeanLD: MutableLiveData<HotTopicBean?> = MutableLiveData()
    var userEventBeanLD: MutableLiveData<UserEventBean?> = MutableLiveData()

    //liveData状态管理
    val dataState: MutableLiveData<Map<String, PAGE_STATE>> = MutableLiveData()
    private var dataStateMap = HashMap<String, PAGE_STATE>()

    private val STATE_FOLLOWLIST = "stateFollowList"
    private val STATE_HOTTOPIC = "stateHotTopic"
    private val STATE_USER_EVENT = "stateUserEvent"

    init {
        dataStateMap[STATE_FOLLOWLIST] = PAGE_STATE.FAIL
        dataStateMap[STATE_HOTTOPIC] = PAGE_STATE.FAIL
        dataStateMap[STATE_USER_EVENT] = PAGE_STATE.FAIL
    }

    fun getUserFollowBeanLD(limit: Int, offset: Int) {
        RetrofitUtils.newInstance()
            .getUserFollows(MMKVStorageUtils.newInstance().getLoginBean()!!.profile.userId,
                limit,
                offset)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe({
                userFollowBeanLD.postValue(it)
                setLdState(STATE_FOLLOWLIST, true)
            }, {
                it.printStackTrace()
                userFollowBeanLD.postValue(null)
                setLdState(STATE_FOLLOWLIST, false)
            })
    }


    fun getHotTopicBeanLD() {
        RetrofitUtils.newInstance().getHotTopic(10, 0).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe({
                hotTopicBeanLD.postValue(it)
                setLdState(STATE_HOTTOPIC, true)
            }, {
                it.printStackTrace()
                hotTopicBeanLD.postValue(null)
                setLdState(STATE_HOTTOPIC, false)
            })
    }
    

    fun getUserEventBeanLd(userId: Long) {
        RetrofitUtils.newInstance()
            .getUserEvent(userId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe({
                userEventBeanLD.postValue(it)
                setLdState(STATE_USER_EVENT, true)
            }, {
                it.printStackTrace()
                userEventBeanLD.postValue(null)
                setLdState(STATE_USER_EVENT, false)
            })
    }


    private fun setLdState(dataStateTAG: String, isSuccess: Boolean) {
        if (isSuccess) {
            dataStateMap[dataStateTAG] = PAGE_STATE.SUCCESS
        } else {
            dataStateMap[dataStateTAG] = PAGE_STATE.FAIL
        }
        dataState.postValue(dataStateMap)
    }
}