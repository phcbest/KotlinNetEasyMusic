package org.phcbest.neteasymusic.ui.activity.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.phcbest.neteasymusic.bean.UserPlaylistBean
import org.phcbest.neteasymusic.utils.RetrofitUtils
import org.phcbest.neteasymusic.utils.SpStorageUtils

class MainActivityViewModel : ViewModel() {

    var liveDataPlaylist: MutableLiveData<UserPlaylistBean?> = MutableLiveData()

    private val loginBean = SpStorageUtils.newInstance().getLoginBean()


    fun setPlayList() {
        RetrofitUtils.newInstance().getUserPlaylist(loginBean?.account?.id.toString())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    liveDataPlaylist.postValue(it)
                }, {
                    liveDataPlaylist.postValue(null)
                }
            )
    }

}