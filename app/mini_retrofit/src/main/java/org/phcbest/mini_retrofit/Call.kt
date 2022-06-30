package org.phcbest.mini_retrofit

import java.io.IOException
import javax.security.auth.callback.Callback

/**
 * 网络请求接口
 */
interface Call : Cloneable {

    /**
     * 同步请求
     */
    @Throws(IOException::class)
    fun execute()

    /**
     * 异步请求
     */
    fun enqueue(callback: Callback)
}