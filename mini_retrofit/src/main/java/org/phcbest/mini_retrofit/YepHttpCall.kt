package org.phcbest.mini_retrofit

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


/**
 * 执行http请求的类
 * 实现Call中的接口
 */
class YepHttpCall(private var mServiceMethod: ServiceMethod) : Call {

    companion object {
        var client: OkHttpClient = OkHttpClient()
    }

    override fun execute(): String? {
        if (mServiceMethod.getMethodName() == "GET") {
            val request = Request.Builder()
                .url(mServiceMethod.getBaseUrl())
                .build()
            val response = client.newCall(request).execute()
            return response.body?.string()
        }
        return null
    }

    override fun enqueue(callback: CallBack) {
        if (mServiceMethod.getMethodName() == "GET") {
            val request = Request.Builder()
                .url(mServiceMethod.getBaseUrl())
                .build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: okhttp3.Call, e: IOException) {
                    e.printStackTrace()
                    callback.failure(e)
                }

                override fun onResponse(call: okhttp3.Call, response: Response) {
                    callback.response(response)
                }

            })
        }
    }
}
