package org.phcbest.mini_retrofit

import okhttp3.Response
import java.lang.Exception

interface CallBack {
    /**
     * 网络请求失败
     */
    fun failure(e: Exception)

    /**
     * 网络请求成功
     */
    fun response(response: Response)
}