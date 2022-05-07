package org.phcbest.neteasymusic.ui.fragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.phcbest.neteasymusic.bean.DiscoverBannerBean
import org.phcbest.neteasymusic.utils.RetrofitUtils

class DiscoverFragmentViewModel : ViewModel() {

    var discoverBannerLiveData: MutableLiveData<DiscoverBannerBean?> = MutableLiveData()
    val dataState: MutableLiveData<Map<String, STATE>> = MutableLiveData()
    private var dataStateMap = HashMap<String, STATE>()

    init {
        dataStateMap["banner"] = STATE.FAIL
    }

    fun setDiscoverBannerLiveData() {
        RetrofitUtils.newInstance().getDiscoverBanner("2")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe({
                discoverBannerLiveData.postValue(it)
                dataStateMap["banner"] = STATE.SUCCESS
                dataState.postValue(dataStateMap)
            }, {
                discoverBannerLiveData.postValue(null)
                dataStateMap["banner"] = STATE.FAIL
                dataState.postValue(dataStateMap)
            })
    }

    enum class STATE {
        SUCCESS, FAIL
    }

}