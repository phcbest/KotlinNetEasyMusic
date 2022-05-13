package org.phcbest.neteasymusic.presenter.impl

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.phcbest.neteasymusic.bean.PlayListDetailBean
import org.phcbest.neteasymusic.bean.SongDetailBean
import org.phcbest.neteasymusic.bean.SongListBean
import org.phcbest.neteasymusic.bean.SongUrlBean
import org.phcbest.neteasymusic.presenter.IGetSongInfoPresenter
import org.phcbest.neteasymusic.utils.RetrofitApi
import org.phcbest.neteasymusic.utils.RetrofitUtils


class GetSongInfoImpl : IGetSongInfoPresenter {

    companion object {
        private const val TAG = "MainPresenterImpl"
    }

    /**
     * 搜索歌曲
     */
    override fun searchSongByKeywords(
        keywords: String, success: (SongListBean) -> Unit, error: (Throwable) -> Unit,
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
     * 获得歌曲的详细信息
     */
    override fun getSongDetailByIDs(
        id: String,
        success: (SongDetailBean) -> Unit,
        error: (Throwable) -> Unit,
    ) {
        val retrofitUtils: RetrofitApi = RetrofitUtils.newInstance()
        retrofitUtils.getSongDetailByIds(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ songDetailBean ->
                success(songDetailBean)
            }, { throwable ->
                throwable.printStackTrace()
                error(throwable)
            })

    }

    /**
     * 获取歌曲下载url
     */
    override fun getSongDownLoadUrl(
        id: Int,
        success: (SongUrlBean) -> Unit,
        error: (Throwable) -> Unit,
    ) {
        val retrofitUtils: RetrofitApi = RetrofitUtils.newInstance()
        retrofitUtils.getDownloadUrlById(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ songUrlBean -> success(songUrlBean) }, { throwable ->
                throwable.printStackTrace()
                error(throwable)
            })
    }

    override fun getPlayListDetailBean(
        id: String,
        success: (PlayListDetailBean) -> Unit,
        error: (Throwable) -> Unit,
    ) {
        RetrofitUtils.newInstance().getPlayListDetail(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ success(it) }, { error(it) })
    }
}