package org.phcbest.neteasymusic.base

interface IBaseCallBack {
    /**
     * 失败调用
     */
    fun onError()

    /**
     * 加载调用
     */
    fun onLoading()

    /**
     * 请求成功但数据为null调用
     */
    fun onEmpty()
}