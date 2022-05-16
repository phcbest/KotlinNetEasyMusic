package org.phcbest.neteasymusic.service

import android.os.Binder
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.text.TextUtils
import android.util.Log
import androidx.media.MediaBrowserServiceCompat
import com.google.android.exoplayer2.ExoPlayer

class MusicService : MediaBrowserServiceCompat() {

    companion object {
        private const val TAG = "MusicService"
    }

    private lateinit var exoPlayer: ExoPlayer
    private lateinit var mediaSession: MediaSessionCompat

    /**
     * 1.创建并且初始化mediaSession
     * 2.设置mediaSession回调
     * 3.设置mediaSession token
     */
    override fun onCreate() {
        super.onCreate()
        mediaSession = MediaSessionCompat(this, TAG)
        mediaSession.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS or MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS)

        val playbackState = PlaybackStateCompat.Builder()
            .setActions(PlaybackStateCompat.ACTION_PLAY or PlaybackStateCompat.ACTION_PLAY_PAUSE
                    or PlaybackStateCompat.ACTION_STOP or PlaybackStateCompat.ACTION_PLAY_PAUSE or
                    PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID or
                    PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH or
                    PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS or
                    PlaybackStateCompat.ACTION_SKIP_TO_NEXT or PlaybackStateCompat.ACTION_SEEK_TO)
            .build()
        mediaSession.setPlaybackState(playbackState)
        //mediaSession设置回调
        mediaSession.setCallback(MyMediaSessionCallback())

        //设置mediaSession Token
        sessionToken = mediaSession.sessionToken

        //创建播放器实例
        exoPlayer = ExoPlayer.Builder(this).build()
    }

    /**
     * 告知MediaBrowser是否连接成功
     */
    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?,
    ): BrowserRoot {
        Log.i(TAG,
            "onGetRoot: clientPackageName = $clientPackageName clientUid = $clientUid pid = ${Binder.getCallingPid()} uid = ${Binder.getCallingUid()}")
        //返回非空即为连接成功
        return BrowserRoot("media_root_id", null)
    }

    /**
     * 加载音视频数据
     */
    override fun onLoadChildren(
        parentId: String,
        result: Result<MutableList<MediaBrowserCompat.MediaItem>>,
    ) {
        Log.i(TAG, "onLoadChildren: parentId = $parentId ")
        var mediaItems: MutableList<MediaBrowserCompat.MediaItem> = arrayListOf()
        if (TextUtils.equals("media_root_id", parentId)) {

        }
        var musicEntityList: MutableList<MusicEntity>
    }

    private data class MusicEntity(
        val id: String,
        val name: String,
        val artist: String,
        val source: String,
        val cover: String,
    )

    private inner class MyMediaSessionCallback : MediaSessionCompat.Callback() {
        override fun onPlay() {
            super.onPlay()
            Log.i(TAG, "onPlay: ")
            exoPlayer.play()
        }

        override fun onPause() {
            super.onPause()
            Log.i(TAG, "onPause: ")
            exoPlayer.pause()
        }

        //以毫秒为单位的指定时间
        override fun onSeekTo(pos: Long) {
            super.onSeekTo(pos)
            Log.i(TAG, "onSeekTo: $pos")
            exoPlayer.seekTo(pos)
        }

    }

}