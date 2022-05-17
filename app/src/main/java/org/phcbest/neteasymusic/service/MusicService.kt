package org.phcbest.neteasymusic.service

import android.os.Binder
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.text.TextUtils
import android.util.Log
import androidx.media.MediaBrowserServiceCompat
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player


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
        var musicEntityList: MutableList<MusicEntity> = arrayListOf()

        for ((index, musicEntity) in musicEntityList.withIndex()) {
            val metadataCompat: MediaMetadataCompat = buildMediaMetadata(musicEntity)

            if (index == 0) {
                mediaSession.setMetadata(metadataCompat)
            }
            mediaItems.add(MediaBrowserCompat.MediaItem(metadataCompat.description,
                MediaBrowserCompat.MediaItem.FLAG_BROWSABLE))

            exoPlayer.addMediaItem(MediaItem.fromUri(musicEntity.source));
        }

        result.sendResult(mediaItems)
        Log.i(TAG, "onLoadChildren: addMediaItem")

        initExoPlayerListener()
        exoPlayer.prepare()
        Log.i(TAG, "onLoadChildren: prepare")

    }


    private fun buildMediaMetadata(musicEntity: MusicEntity): MediaMetadataCompat {
        return MediaMetadataCompat.Builder()
            .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, musicEntity.id)
            .putString("__SOURCE__", musicEntity.source)
            .putString(MediaMetadataCompat.METADATA_KEY_TITLE, musicEntity.name)
            .putString(MediaMetadataCompat.METADATA_KEY_ALBUM, musicEntity.artist)
            .putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI, musicEntity.cover)
            .build()
    }

    private fun initExoPlayerListener() {
        exoPlayer.addListener(object : Player.Listener {
            /**
             * 用户触发切歌暂停播放,seek
             * 播放器触发,播放结束,自动切歌
             */
            override fun onPlaybackStateChanged(state: Int) {
                val currentPosition = exoPlayer.currentPosition
                val duration = exoPlayer.duration
                Log.i(TAG,
                    "onPlaybackStateChanged: currentPosition = $currentPosition duration = $duration state = $state")

                val playbackState: Int = when (state) {
                    Player.STATE_IDLE -> PlaybackStateCompat.STATE_NONE
                    Player.STATE_BUFFERING -> PlaybackStateCompat.STATE_BUFFERING
                    Player.STATE_READY -> if (exoPlayer.playWhenReady) {
                        PlaybackStateCompat.STATE_PLAYING
                    } else {
                        PlaybackStateCompat.STATE_PAUSED
                    }
                    Player.STATE_ENDED -> PlaybackStateCompat.STATE_STOPPED
                    else -> PlaybackStateCompat.STATE_NONE
                }
                //播放器状态改变,通过mediaSession告诉ui在业务层注册的MediaControllerCompat.Callback回调
                setPlaybackState(playbackState)
            }
        })
    }

    private fun setPlaybackState(playbackState: Int) {
        val speed: Float = if (exoPlayer.playbackParameters == null) {
            1f
        } else {
            exoPlayer.playbackParameters.speed
        }
        mediaSession.setPlaybackState(PlaybackStateCompat.Builder()
            .setState(playbackState, exoPlayer.currentPosition, speed).build())
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