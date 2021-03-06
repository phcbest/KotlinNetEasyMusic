package org.phcbest.neteasymusic.utils

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "RetrofitUtils"

class RetrofitUtils {
    companion object {
        //service类是接口服务类
        private fun <T> getService(baseUrl: String, service: Class<T>): T {
            val client = OkHttpClient.Builder().addInterceptor(LogIntercepted()).build()

            val retrofit = Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(client).build()
            return retrofit.create(service)
        }

        private fun apiService(): RetrofitApi {
            return getService(RetrofitApi.baseUrl, RetrofitApi::class.java)
        }

        private val instance = apiService()

        @JvmStatic
        fun newInstance(): RetrofitApi {
            return instance
        }
    }

}

class LogIntercepted : Interceptor {
    private val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.CHINA)


    override fun intercept(chain: Interceptor.Chain): Response {
        //判断网络状态,如果没网络就toast
        NetWorkUtils.testAndSendNetWorkStateToast()
        //请求
        var request = chain.request()
        Log.i(
            TAG,
            format.format(Date()) + " Requeste " + "\nmethod:" + request.method + "\nurl:"
                    + request.url + "\nbody:" + request.body
        )
        //添加cookie
        val newBuilder = request.newBuilder()
        MMKVStorageUtils.getInstance().getCookie()
            .let {
                if (!it.contains(MMKVStorageUtils.SP_NULL)) {
                    Log.i(TAG, "intercept: cookie:$it")
                    //ntmd添加hander后需要nm build,怪不得一直请求没有带上token,就离谱
                    request = newBuilder.addHeader("Cookie", it).build()
                }
            }
        //响应
        val response = chain.proceed(request)
        Log.i(
            TAG,
            format.format(Date()) + " Response " + "\nsuccessful:" + response.isSuccessful + "\nbody:" + response.peekBody(
                1024
            ).string()
        )
        return response
    }

}
