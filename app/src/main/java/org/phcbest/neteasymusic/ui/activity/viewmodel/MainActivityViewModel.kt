package org.phcbest.neteasymusic.ui.activity.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.phcbest.neteasymusic.bean.PlayListDetailBean
import org.phcbest.neteasymusic.utils.RetrofitUtils
import org.phcbest.neteasymusic.utils.SpStorageUtils

class MainActivityViewModel : ViewModel() {

    var playlistDetailLiveData: MutableLiveData<PlayListDetailBean?> = MutableLiveData()

    private val loginBean = SpStorageUtils.newInstance().getLoginBean()


    fun setPlayListDetail(playlistId: String) {
        RetrofitUtils.newInstance().getPlayListDetail(playlistId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    playlistDetailLiveData.postValue(it)
                }, {
                    playlistDetailLiveData.postValue(null)
                }
            )
    }

}