package org.phcbest.neteasymusic.presenter

import org.phcbest.neteasymusic.bean.SongListBean

interface IMainPresenter {
    fun getSongByKeywords(
        keywords: String,
        success: (SongListBean) -> Unit,
        error: (Throwable) -> Unit
    )
}