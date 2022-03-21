package org.phcbest.neteasymusic.base

interface IBasePresenter<T> {
    /**
     * 注册接口通知
     */
    fun registerViewCallBack(callback: T)

    /**
     * 取消注册接口通知
     */
    fun unRegisterViewCallBack(callback: T)
}