package org.phcbest.mini_retrofit

import java.io.IOException

/**
 * 网络请求接口
 */
interface Call : Cloneable {

    /**
     * 同步请求
     */
    @Throws(IOException::class)
    fun execute(): String?

    /**
     * 异步请求
     */
    fun enqueue(callback: CallBack)

}