package org.phcbest.neteasymusic.ui.activity.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.phcbest.neteasymusic.bean.PlayListDetailBean
import org.phcbest.neteasymusic.presenter.PresenterManager
import org.phcbest.neteasymusic.utils.ToastUtils

class PlayListDetailViewModel : ViewModel() {
    companion object {
        private const val TAG = "PlayListDetailViewModel"

    }

    private val songInfoPresenter = PresenterManager.getInstance().getSongInfoPresenter()

    var playListDetail: MutableLiveData<PlayListDetailBean?> = MutableLiveData()

    fun setPlayListDetail(id: String) {
        songInfoPresenter.getPlayListDetailBean(id, {
            playListDetail.postValue(it)
        }, {
            ToastUtils.SEND_SMG(it.message.toString())
            playListDetail.postValue(null)
            it.printStackTrace()
        })
    }

}