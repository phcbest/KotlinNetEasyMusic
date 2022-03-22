package org.phcbest.neteasymusic.presenter

import io.reactivex.rxjava3.core.Observable
import org.phcbest.neteasymusic.bean.DiscoverBannerBean

interface IDiscoverPagePresenter {
    fun getBanner(
        success: (DiscoverBannerBean) -> Unit,
        error: (Throwable) -> Unit
    )
}