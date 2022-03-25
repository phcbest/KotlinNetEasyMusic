package org.phcbest.neteasymusic.presenter

import org.phcbest.neteasymusic.presenter.impl.DiscoverPagePresenterImpl
import org.phcbest.neteasymusic.presenter.impl.MainPresenterImpl

class PresenterManager() {


    companion object {

        private var instance: PresenterManager? = null
        private var mDiscoverPagePresenter: IDiscoverPagePresenter? = null
        private var mIMainPresenter: IMainPresenter? = null

        /**
         * 懒加载,并且线程安全的的单例模式
         */
        @Synchronized
        fun getInstance(): PresenterManager {
            if (null == instance) {
                instance = PresenterManager()
                initPresenter()
            }
            return instance!!
        }


        /**
         * 初始化其他的提供者
         */
        private fun initPresenter() {
            mDiscoverPagePresenter = DiscoverPagePresenterImpl()
            mIMainPresenter = MainPresenterImpl()
        }
    }

    fun getDiscoverPagePresenter(): IDiscoverPagePresenter {
        return mDiscoverPagePresenter!!
    }

    fun getMainPresenter(): IMainPresenter {
        return mIMainPresenter!!
    }
}