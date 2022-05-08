package org.phcbest.neteasymusic.ui.fragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.phcbest.neteasymusic.bean.DiscoverBannerBean
import org.phcbest.neteasymusic.bean.PersonalizedPlayListBean
import org.phcbest.neteasymusic.bean.RecommendPlayListBean
import org.phcbest.neteasymusic.utils.RetrofitUtils

class DiscoverFragmentViewModel : ViewModel() {

    var discoverBannerLiveData: MutableLiveData<DiscoverBannerBean?> = MutableLiveData()
    var recommendPlayListLiveData: MutableLiveData<RecommendPlayListBean?> = MutableLiveData()

    //liveData状态管理
    val dataState: MutableLiveData<Map<String, STATE>> = MutableLiveData()
    private var dataStateMap = HashMap<String, STATE>()

    private val stateBanner = "banner"
    private val stateRecommendPlayList = "RecommendPlayList"

    init {
        dataStateMap[stateBanner] = STATE.FAIL
        dataStateMap[stateRecommendPlayList] = STATE.FAIL
    }

    fun setDiscoverBannerLiveData() {
        RetrofitUtils.newInstance().getDiscoverBanner("2")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe({
                discoverBannerLiveData.postValue(it)
                dataStateMap[stateBanner] = STATE.SUCCESS
                dataState.postValue(dataStateMap)
            }, {
                discoverBannerLiveData.postValue(null)
                dataStateMap[stateBanner] = STATE.FAIL
                dataState.postValue(dataStateMap)
            })
    }

    fun setRecommendPlayListLiveData() {
        RetrofitUtils.newInstance().getRecommendPlayList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                recommendPlayListLiveData.postValue(it)
                dataStateMap[stateRecommendPlayList] = STATE.SUCCESS
                dataState.postValue(dataStateMap)
            }, {
                it.printStackTrace()
                recommendPlayListLiveData.postValue(null)
                dataStateMap[stateRecommendPlayList] = STATE.FAIL
                dataState.postValue(dataStateMap)
            })
    }

    enum class STATE {
        SUCCESS, FAIL
    }

}