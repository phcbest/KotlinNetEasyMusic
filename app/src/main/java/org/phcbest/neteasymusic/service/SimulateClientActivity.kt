package org.phcbest.neteasymusic.service

import android.content.ComponentName
import android.media.browse.MediaBrowser
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SimulateClientActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SimulateClientActivity"
    }

    private var mBrowser: MediaBrowser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBrowser = MediaBrowser(this, ComponentName(this, MusicPlayerMediaSession::class.java),
            browserConnectionCallback, null)
    }

    /**
     * 连接状态的回调接口
     */
    private var browserConnectionCallback = object : MediaBrowser.ConnectionCallback() {
        override fun onConnected() {
            super.onConnected()
            Log.i(TAG, "onConnected: ")
            //进行订阅操作
            if (!mBrowser?.isConnected!!) return
            //运行连接会正常返回值,不允许连接会返回null
            val mediaId = mBrowser?.root!!
            //browser通过订阅的方式向Service请求数据,发起订阅需要mediaId参数
            mBrowser?.unsubscribe(mediaId)
            mBrowser?.subscribe(mediaId, browserSubscriptionCallback)
        }

        override fun onConnectionFailed() {
            super.onConnectionFailed()
            Log.i(TAG, "onConnectionFailed: 连接失败")
        }
    }

    /**
     * 向MediaBrowserService发起数据订阅请求后的回调接口
     */
    private var browserSubscriptionCallback = object : MediaBrowser.SubscriptionCallback() {
        override fun onChildrenLoaded(
            parentId: String,
            children: MutableList<MediaBrowser.MediaItem>,
        ) {
            Log.i(TAG, "onChildrenLoaded: ")
            //service发送回来的媒体数据集合
            for (child in children) {
                Log.i(TAG, "onChildrenLoaded: ${child.description.title.toString()}")
            }
            //执行ui刷新

        }
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