package org.phcbest.neteasymusic.presenter

import org.phcbest.neteasymusic.presenter.impl.DiscoverPagePresenterImpl
import org.phcbest.neteasymusic.presenter.impl.GetSongInfoImpl
import org.phcbest.neteasymusic.presenter.impl.LoginPresenterImpl

class PresenterManager() {


    companion object {

        private var instance: PresenterManager? = null
        private var mDiscoverPagePresenter: IDiscoverPagePresenter? = null
        private var mIGetSongInfoPresenter: IGetSongInfoPresenter? = null
        private var mILoginPresenter: ILoginPresenter? = null

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
            mIGetSongInfoPresenter = GetSongInfoImpl()
            mILoginPresenter = LoginPresenterImpl()
        }
    }

    fun getDiscoverPagePresenter(): IDiscoverPagePresenter {
        return mDiscoverPagePresenter!!
    }

    fun getSongInfoPresenter(): IGetSongInfoPresenter {
        return mIGetSongInfoPresenter!!
    }

    fun getLoginPresenter(): ILoginPresenter {
        return mILoginPresenter!!
    }
}