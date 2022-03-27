package org.phcbest.neteasymusic.presenter

import org.phcbest.neteasymusic.bean.SongDetailBean
import org.phcbest.neteasymusic.bean.SongListBean
import org.phcbest.neteasymusic.bean.SongUrlBean

interface IGetSongInfoPresenter {
    fun searchSongByKeywords(
        keywords: String,
        success: (SongListBean) -> Unit,
        error: (Throwable) -> Unit
    )

    fun getSongDetailByIDs(
        id: String,
        success: (SongDetailBean) -> Unit,
        error: (Throwable) -> Unit
    )

    fun getSongUrl(
        id: String,
        success: (SongUrlBean) -> Unit,
        error: (Throwable) -> Unit
    )
}