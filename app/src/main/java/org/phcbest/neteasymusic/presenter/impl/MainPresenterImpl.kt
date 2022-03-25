package org.phcbest.neteasymusic.presenter.impl

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.phcbest.neteasymusic.bean.SongListBean
import org.phcbest.neteasymusic.presenter.IMainPresenter
import org.phcbest.neteasymusic.utils.RetrofitUtils

private const val TAG = "MainPresenterImpl"

class MainPresenterImpl : IMainPresenter {
    override fun getSongByKeywords(
        keywords: String, success: (SongListBean) -> Unit, error: (Throwable) -> Unit
    ) {
        RetrofitUtils.newInstance().getSongByKeyWords(keywords)
            .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
            .subscribe({ result -> success(result) }, { errorInfo ->
                Log.e(
                    TAG,
                    "getSongByKeywords: 网络请求错误+${errorInfo.printStackTrace()}",
                )
                error(errorInfo)
            })
    }
}