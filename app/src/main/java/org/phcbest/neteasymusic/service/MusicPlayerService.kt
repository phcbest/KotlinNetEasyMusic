package org.phcbest.neteasymusic.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder


import android.os.Binder

private const val TAG = "MusicPlayService"

/**
 * 播放音乐的服务
 */
class MusicPlayerService : Service() {

    private var mPlaylist: MutableList<SongEntity> = arrayListOf()
    private lateinit var mMediaPlayer: MediaPlayer
    private var mCurrentSong: SongEntity? = null


    override fun onCreate() {
        super.onCreate()
        //初始化播放器
        mMediaPlayer = MediaPlayer()
        //初始化mediaPlayer的事件
        initMediaPlayerEvent()
    }

    private fun initMediaPlayerEvent() {
        mMediaPlayer.setOnPreparedListener {
            //准备加载,进行播放
            it.start()
            //获得歌曲时间
//            it.duration
        }
        mMediaPlayer.setOnCompletionListener {
            //播放完成回调,切换下一首
            switchSong(true)
        }
    }

    //管理播放列表
    private fun addSongToList(songEntity: SongEntity, playWhenAppend: Boolean) {
        if (mPlaylist.indexOf(songEntity) != -1) {
            //歌曲列表不包含当前歌曲,添加到当前播放的歌曲后面
            mPlaylist.add(mPlaylist.indexOf(mCurrentSong), songEntity)
        }
        //加载歌曲
        if (playWhenAppend) {
            aSyncLoadSong(songEntity)
        }
    }

    fun setPlayList(songEntities: MutableList<SongEntity>) {
        this.mPlaylist.clear()
        this.mPlaylist.addAll(songEntities)
    }

    private fun aSyncLoadSong(songEntity: SongEntity) {
        mMediaPlayer.reset()
        mMediaPlayer.setDataSource(songEntity.source)
        mMediaPlayer.prepareAsync()
    }

    /**
     * @param nextOrPrevious 为true是下一首,false是上一首
     */
    private fun switchSong(nextOrPrevious: Boolean) {
        if (nextOrPrevious) {
            //判断最后一首
            aSyncLoadSong(mPlaylist[if (mPlaylist.indexOf(mCurrentSong) == mPlaylist.size - 1) {
                0
            } else {
                mPlaylist.indexOf(mCurrentSong) + 1
            }])
        } else {
            //判断第一首
            aSyncLoadSong(mPlaylist[if (mPlaylist.indexOf(mCurrentSong) == 0) {
                mPlaylist.indexOf(mCurrentSong) - 1
            } else {
                mPlaylist.size - 1
            }])
        }
    }

    data class SongEntity(
        val id: String,
        val name: String,
        val source: String,
        val cover: String,
        val author: String,
        val special: String,
    )

    //=============================binder相关==============================
    private val mBinder = MyBinder()

    override fun onBind(intent: Intent?): IBinder {
        return mBinder
    }

    inner class MyBinder() : Binder() {
        fun getService(): MusicPlayerService {
            return this@MusicPlayerService
        }
    }
}
