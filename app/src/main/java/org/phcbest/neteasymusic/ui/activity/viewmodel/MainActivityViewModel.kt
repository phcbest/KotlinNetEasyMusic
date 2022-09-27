package org.phcbest.neteasymusic.ui.activity.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.phcbest.neteasymusic.bean.PlayListDetailBean
import org.phcbest.neteasymusic.utils.RetrofitUtils
import org.phcbest.neteasymusic.utils.MMKVStorageUtils

class MainActivityViewModel : ViewModel() {

    companion object {
        private const val TAG = "MainActivityViewModel"
    }

    var playlistDetailLiveData: MutableLiveData<PlayListDetailBean?> = MutableLiveData()

    //用户喜欢的歌单ID
    var favoritePlayListID: MutableLiveData<Long> = MutableLiveData(-1)

    private val loginBean = MMKVStorageUtils.getInstance().getLoginBean()


    fun setFavoritePlaylistId() {
        RetrofitUtils.newInstance().getUserPlaylist(loginBean?.account?.id.toString())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ userPlaylist ->
                Log.i(TAG, "setFavoritePlaylistId: ${userPlaylist.playlist[0].id}")
                favoritePlayListID.postValue(userPlaylist.playlist[0].id)
            }, {
                it.printStackTrace()
                favoritePlayListID.postValue(-1)
            })
    }

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