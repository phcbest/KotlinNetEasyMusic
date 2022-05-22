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
            System.loadLibrary("native-lib")
        }

        private const val TAG = "BaseApplication"
    }

    override fun onCreate() {
        super.onCreate()
        appContext = baseContext
        //设置mmkv
        val rootDir = MMKV.initialize(this)
        Log.i(TAG, "mmkv root: $rootDir")
    }


}