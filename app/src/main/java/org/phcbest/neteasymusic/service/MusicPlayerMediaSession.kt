package org.phcbest.neteasymusic.service

import android.media.MediaMetadata
import android.media.MediaPlayer
import android.media.browse.MediaBrowser
import android.media.session.MediaSession
import android.media.session.PlaybackState
import android.net.Uri
import android.os.Bundle
import android.service.media.MediaBrowserService
import android.util.Log
import org.phcbest.neteasymusic.utils.MMKVStorageUtils
import java.io.IOException

class MusicPlayerMediaSession : MediaBrowserService() {

    private var mPlaybackState: PlaybackState? = null
    private var mSession: MediaSession? = null

    //媒体播放器
    private var mMediaPlayer: MediaPlayer? = null

    companion object {
        private const val TAG = "MusicPlayerMediaSession"

        val MEDIA_ID_ROOT: String = "root"
    }

    /**
     * 初始化MediaSession,设置标志位等参数
     */
    override fun onCreate() {
        super.onCreate()
        //设置播放状态
        mPlaybackState = PlaybackState.Builder()
            //设置播放状态,开始位置,和播放速度
            .setState(PlaybackState.STATE_NONE, 0, 1.0f)
            .build()

        //初始化MediaSession
        mSession = MediaSession(this, "MusicPlayerMediaSession")
        //设置回调
        mSession?.setCallback(mediaSessionCallback)
        //设置标志位
        mSession?.setFlags(MediaSession.FLAG_HANDLES_TRANSPORT_CONTROLS)
        //设置播放状态
        mSession?.setPlaybackState(mPlaybackState)

        //MediaBrowserService的Token
        sessionToken = mSession?.sessionToken

        //初始化MediaPlayer
        mMediaPlayer = MediaPlayer()
        mMediaPlayer?.setOnPreparedListener(preparedListener)
        mMediaPlayer?.setOnCompletionListener(completionListener)
    }

    /**
     * 当播放器准备好后调用
     */
    private val preparedListener = MediaPlayer.OnPreparedListener {
        mMediaPlayer?.start()
        mPlaybackState = PlaybackState.Builder()
            .setState(PlaybackState.STATE_PLAYING, 0, 1.0f)
            .build()
        mSession?.setPlaybackState(mPlaybackState)
    }

    /**
     * 当播放器播放完成后调用
     */
    private val completionListener = MediaPlayer.OnCompletionListener {
        mPlaybackState = PlaybackState.Builder()
            .setState(PlaybackState.STATE_NONE, 0, 1.0f)
            .build()
        mSession?.setPlaybackState(mPlaybackState)
        mMediaPlayer?.reset()
    }

    /**
     * 设置响应控制器指令的回调
     */
    private val mediaSessionCallback = object : MediaSession.Callback() {
        //响应MediaController.TransportControls.play()指令
        override fun onPlay() {
            Log.i(TAG, "onPlay: ")
            if (mPlaybackState?.state == PlaybackState.STATE_PAUSED) {
                mMediaPlayer?.start()
                mPlaybackState = PlaybackState.Builder()
                    .setState(PlaybackState.STATE_PLAYING, 0, 1.0f)
                    .build()
                mSession?.setPlaybackState(mPlaybackState)
            }
        }

        //响应MediaController.TransportControls.pause()指令
        override fun onPause() {
            Log.i(TAG, "onPause: ")
            if (mPlaybackState?.state == PlaybackState.STATE_PLAYING) {
                mMediaPlayer?.pause()
                mPlaybackState = PlaybackState.Builder()
                    .setState(PlaybackState.STATE_PAUSED, 0, 1.0f)
                    .build()
                mSession?.setPlaybackState(mPlaybackState)
            }
        }

        //响应MediaController.TransportControls.playFromUri()指令
        override fun onPlayFromUri(uri: Uri?, extras: Bundle?) {
            Log.i(TAG, "onPlayFromUri: ")
            try {
                when (mPlaybackState?.state) {
                    PlaybackState.STATE_PLAYING, PlaybackState.STATE_PAUSED -> {}
                    PlaybackState.STATE_NONE -> {
                        mMediaPlayer?.reset()
                        mMediaPlayer?.setDataSource(this@MusicPlayerMediaSession, uri!!)
                        mMediaPlayer?.prepare()
                        mPlaybackState = PlaybackState.Builder()
                            .setState(PlaybackState.STATE_CONNECTING, 0, 1.0f)
                            .build()
                        mSession?.setPlaybackState(mPlaybackState)
                        //保存当前播放音乐的信息,方便客户端刷新UI
                        mSession?.setMetadata(
                            MediaMetadata.Builder()
                                .putString(MediaMetadata.METADATA_KEY_TITLE,
                                    extras?.getString("title"))
                                .build()
                        )
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        override fun onPlayFromSearch(query: String?, extras: Bundle?) {
            super.onPlayFromSearch(query, extras)
        }
    }


    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?,
    ): BrowserRoot {
        Log.i(TAG, "======================onGetRoot======================")
        return BrowserRoot(MEDIA_ID_ROOT, null)
    }

    override fun onLoadChildren(
        parentId: String,
        result: Result<MutableList<MediaBrowser.MediaItem>>,
    ) {
        Log.i(TAG, "======================onLoadChildren======================")
        //将此消息从当前线程中分离出来，并允许稍后发生sendResult调用
        result.detach()
        //创建媒体项目列表
        val mediaItems = mutableListOf<MediaBrowser.MediaItem>()
        MMKVStorageUtils.newInstance().getPlayList().let { list ->
            list?.forEach { item ->
                val mediaMetadata = MediaMetadata.Builder()
                    .putString(MediaMetadata.METADATA_KEY_TITLE, item.name)
                    .putString(MediaMetadata.METADATA_KEY_AUTHOR, item.author)
                    .putString(MediaMetadata.METADATA_KEY_MEDIA_ID, item.songId)
                    .putString(MediaMetadata.METADATA_KEY_MEDIA_URI, item.songId)
                    .build()
                mediaItems.add(MediaBrowser.MediaItem(mediaMetadata.description,
                    MediaBrowser.MediaItem.FLAG_PLAYABLE))
            }
        }

        //发送结果
        result.sendResult(mediaItems)
    }

}