package org.phcbest.mini_retrofit

import okhttp3.OkHttpClient
import sun.net.www.http.HttpClient
import javax.security.auth.callback.Callback

class YepHttpCall(private var serviceMethod: ServiceMethod) : Call {

    companion object {
        var client: OkHttpClient = OkHttpClient()
    }

    override fun execute() {
        TODO("Not yet implemented")
    }

    override fun enqueue(callback: Callback) {
        TODO("Not yet implemented")
    }
}