package org.phcbest.neteasymusic.ui.fragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.phcbest.neteasymusic.bean.UserDetailBean
import org.phcbest.neteasymusic.bean.UserPlaylistBean
import org.phcbest.neteasymusic.utils.RetrofitUtils
import org.phcbest.neteasymusic.utils.MMKVStorageUtils

class MineFragmentViewModel() : ViewModel() {

    private val loginBean = MMKVStorageUtils.getInstance().getLoginBean()

    private var userDetailBean: MutableLiveData<UserDetailBean> = MutableLiveData()
    private var userPlaylistBean: MutableLiveData<UserPlaylistBean> = MutableLiveData()


    init {
        setUserDetail()
        setUserPlayList()
    }

    /**
     * 联网访问用户信息
     */
    fun setUserDetail() {
        RetrofitUtils.newInstance().getUserDetail(loginBean?.account?.id.toString())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe({ userDetailBean ->
//                ObservableField
                this.userDetailBean.postValue(userDetailBean)
            }, {
                this.userDetailBean.postValue(null)
            })
    }

    /**
     * 获得用户歌单列表
     */
    fun setUserPlayList() {
        RetrofitUtils.newInstance().getUserPlaylist(loginBean?.account?.id.toString())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ userPlaylist ->
                this.userPlaylistBean.postValue(userPlaylist)
            }, {
                it.printStackTrace()
                this.userPlaylistBean.postValue(null)
            })
    }

    /**
     * 访问用户详情
     */
    fun getUserDetail(): LiveData<UserDetailBean> {
        return this.userDetailBean
    }

    /**
     * 访问用户歌单
     */
    fun getUserPlaylist(): LiveData<UserPlaylistBean> {
        return this.userPlaylistBean
    }

}