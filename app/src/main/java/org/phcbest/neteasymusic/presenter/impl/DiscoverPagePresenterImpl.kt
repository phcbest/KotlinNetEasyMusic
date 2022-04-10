package org.phcbest.neteasymusic.presenter.impl

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.phcbest.neteasymusic.bean.DiscoverBannerBean
import org.phcbest.neteasymusic.presenter.IDiscoverPagePresenter
import org.phcbest.neteasymusic.ui.fragment.DiscoverFragment
import org.phcbest.neteasymusic.ui.widget.banner.CustomBanner
import org.phcbest.neteasymusic.utils.RetrofitUtils

private const val TAG = "DiscoverPagePresenterIm"

class DiscoverPagePresenterImpl : IDiscoverPagePresenter {


    override fun getBanner(
        success: (DiscoverBannerBean) -> Unit,
        error: (Throwable) -> Unit
    ) {
        val newInstance = RetrofitUtils.newInstance()

        newInstance.getDiscoverBanner("2")
            .observeOn(AndroidSchedulers.mainThread())//观察者运行在AndroidUI主线程上
            .subscribeOn(Schedulers.io()).subscribe({ result ->
                success(result)
            }, { errorInfo ->
                Log.e(
                    TAG, "doAdapter: 网络请求错误" + errorInfo.printStackTrace()
                )
                error(errorInfo)
            })

    }

}