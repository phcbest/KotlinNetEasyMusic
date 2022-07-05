package org.phcbest.neteasymusic.base

import android.app.Application
import android.content.Context
import android.util.Log
import com.tencent.mmkv.MMKV

class BaseApplication : Application() {
    companion object {
        var appContext: Context? = null
        fun getBaseContext(): Context {
            return appContext!!
        }

        init {
            //加载c++库
            System.loadLibrary("native-lib")
        }

        private const val TAG = "BaseApplication"
    }

    override fun onCreate() {
        super.onCreate()
        appContext = baseContext
        //初始化mmkv
        val rootDir = MMKV.initialize(this)
        Log.i(TAG, "mmkv root: $rootDir")
    }


}