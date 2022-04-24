package org.phcbest.neteasymusic.base

import android.app.Application
import android.content.Context

class BaseApplication : Application() {
    companion object {
        var appContext: Context? = null
        fun getBaseContext(): Context {
            return appContext!!
        }

        init {
            System.loadLibrary("native-lib")
        }
    }

    override fun onCreate() {
        super.onCreate()
        appContext = baseContext
    }


}