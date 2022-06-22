package org.phcbest.neteasymusic.service

import android.content.ComponentName
import android.media.browse.MediaBrowser
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SimulateClientActivity : AppCompatActivity() {

    private var mBrowser: MediaBrowser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBrowser = MediaBrowser(this, ComponentName(this, MusicPlayerMediaSession::class.java),
            browserConnectionCallback, null)
    }

    private var browserConnectionCallback = object : MediaBrowser.ConnectionCallback() {

    }

    override fun onStart() {
        super.onStart()
        //发送连接请求
        mBrowser?.connect()
    }

    override fun onStop() {
        super.onStop()
        //断开连接
        mBrowser?.disconnect()
    }
}