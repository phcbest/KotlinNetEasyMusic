package org.phcbest.neteasymusic.presenter.impl

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.ObservableSource
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers
import org.phcbest.neteasymusic.bean.SongListBean
import org.phcbest.neteasymusic.bean.SongUrlBean
import org.phcbest.neteasymusic.presenter.IGetSongInfoPresenter
import org.phcbest.neteasymusic.utils.RetrofitApi
import org.phcbest.neteasymusic.utils.RetrofitUtils
import kotlin.math.log

private const val TAG = "MainPresenterImpl"

class GetSongInfoImpl : IGetSongInfoPresenter {

    /**
     * 获取歌曲信息和下载url
     */
    override fun searchSongByKeywords(
        keywords: String, success: (SongListBean) -> Unit, error: (Throwable) -> Unit
    ) {
        val retrofitUtils: RetrofitApi = RetrofitUtils.newInstance()
        retrofitUtils.getSongByKeyWords(keywords).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ songListBean -> success(songListBean) }, { throwable ->
                throwable.printStackTrace()
                error(throwable)
            })
    }

    /**
     * 获取歌曲下载url
     */
    override fun getSongUrl(
        id: String,
        success: (SongUrlBean) -> Unit,
        error: (Throwable) -> Unit
    ) {
        val retrofitUtils: RetrofitApi = RetrofitUtils.newInstance()
        retrofitUtils.getDownloadUrlById(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ songUrlBean -> success(songUrlBean) }, { throwable ->
                throwable.printStackTrace()
                error(throwable)
            })
    }
}