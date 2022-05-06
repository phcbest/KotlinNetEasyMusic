package org.phcbest.neteasymusic.ui.fragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.phcbest.neteasymusic.bean.DiscoverBannerBean
import org.phcbest.neteasymusic.ui.widget.banner.BannerItemBean

class DiscoverFragmentViewModel : ViewModel() {

    var discoverBannerLiveData: LiveData<DiscoverBannerBean> = MutableLiveData()

    fun setDiscoverBannerLiveData() {


    }


}