package org.phcbest.neteasymusic.service

import android.app.Service
import android.content.Entity
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.IBinder


import android.os.Binder
import android.os.Build
import android.os.PowerManager
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.bean.PlayListDetailBean
import org.phcbest.neteasymusic.presenter.PresenterManager
import java.util.*
import kotlin.math.log

private const val TAG = "MusicPlayService"

/**
 * 播放音乐的服务
 */
class MusicPlayerService : Service() {

    private var mPlaylist: MutableList<SongEntity> = arrayListOf()
    private lateinit var mMediaPlayer: MediaPlayer
    private var mCurrentSongIndex: Int = 0


    override fun onCreate() {
        super.onCreate()
        //初始化播放器
        mMediaPlayer = MediaPlayer()
        mMediaPlayer.setWakeMode(applicationContext, PowerManager.PARTIAL_WAKE_LOCK);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //初始化mediaPlayer的事件
        initMediaPlayerEvent()
    }

    var currentSongEntityLD: MutableLiveData<SongEntity> = MutableLiveData()

    private fun initMediaPlayerEvent() {
        mMediaPlayer.setOnPreparedListener {
            //准备加载,进行播放
            playControl(2)
            currentSongEntityLD.postValue(mPlaylist[mCurrentSongIndex])
            //获得歌曲时间
//            it.duration
        }
        mMediaPlayer.setOnCompletionListener {
            //播放完成回调,切换下一首
            switchSongNOP(true)
        }
        mMediaPlayer.setOnInfoListener { mp, what, extra ->
            Log.i(TAG,
                "initMediaPlayerEvent OnInfoListener: isplayin= ${mp.isPlaying} what = $what")
            false
        }
        //设置错误监听为已经处理,就不会在切歌的时候触发完成回调
        mMediaPlayer.setOnErrorListener { mediaPlayer: MediaPlayer, what: Int, extra: Int ->
            Log.i(TAG, "initMediaPlayerEvent: OnErrorListener what = $what")
            false
        }
    }


    //true为播放,false为暂停
    var isPlayerLD: MutableLiveData<Boolean> = MutableLiveData(false)

    /**
     * 播放控制
     * 1=pause 将当前音乐暂停，保持进度
     * 2=start 开始播放 如果在pause后调用就是恢复播放
     * 3=stop 停止播放是如果stop后调用start会从头开始播放
     */
    fun playControl(controlCode: Int) {
        when (controlCode) {
            1 -> {
                if (mMediaPlayer.isPlaying) {
                    mMediaPlayer.pause()
                }
            }
            2 -> {
                if (!mMediaPlayer.isPlaying) {
                    mMediaPlayer.start()
                }
            }
            3 -> {
                mMediaPlayer.stop()
            }
            else -> Log.i(TAG, "playControl: 控制代码$controlCode 没有符合的")
        }
        isPlayerLD.postValue(mMediaPlayer.isPlaying)
    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun setPlayList(playlist: PlayListDetailBean.Playlist) {
        this.mPlaylist.clear()
        playlist.tracks.map {
            this.mPlaylist.add(SongEntity(
                it.id.toString(),
                it.name,
                it.id.toString(),
                it.al.picUrl,
                if (it.ar!!.isEmpty()) {
                    "佚名"
                } else {
                    val sj = StringJoiner("-")
                    it.ar.map { ar -> sj.add(ar.name) }
                    sj.toString()
                }
            ))
        }
    }

    //添加歌曲到播放列表
    private fun addSongToList(songEntity: SongEntity, playWhenAppend: Boolean) {
        //歌曲列表不包含当前歌曲,添加到当前播放的歌曲后面
        mPlaylist.add(mCurrentSongIndex, songEntity)
        //加载歌曲
        if (playWhenAppend) {
            mCurrentSongIndex++
            aSyncLoadSong()
        }
    }

    private fun aSyncLoadSong() {
        mMediaPlayer.reset()
        //网络请求获得播放地址
        Log.i(TAG, "aSyncLoadSong: 加载音乐的下标 $mCurrentSongIndex")
        PresenterManager.getInstance().getSongInfoPresenter()
            .getSongDownLoadUrl(mPlaylist[mCurrentSongIndex].songId, {
                if (it.data.isNotEmpty()) {
                    mMediaPlayer.setDataSource(it.data[0].url)
                    mMediaPlayer.prepareAsync()
                    //将当前播放音乐的数据传递出去
                }
            }, { it.printStackTrace() })
    }

    /**
     * 切换歌曲,按照index
     */
    fun switchSong(index: Int) {
        if (index in mPlaylist.indices) {
            mCurrentSongIndex = index
            aSyncLoadSong()
        }
    }

    /**
     * 切换上下首
     * @param nextOrPrevious 为true是下一首,false是上一首
     */
    fun switchSongNOP(nextOrPrevious: Boolean) {
        Log.i(TAG, "switchSongNOP 切换为 ${
            if (nextOrPrevious) {
                "下一首"
            } else {
                "上一首"
            }
        } ")
        if (nextOrPrevious) {
            //判断最后一首
            if (mCurrentSongIndex >= mPlaylist.size - 1) {
                mCurrentSongIndex = 0
            } else {
                mCurrentSongIndex++
            }

        } else {
            //判断第一首
            if (mCurrentSongIndex == 0) {
                mCurrentSongIndex = mPlaylist.size - 1
            } else {
                mCurrentSongIndex--
            }

        }
        aSyncLoadSong()
    }

    data class SongEntity(
        val id: String,
        val name: String,
        val songId: String,
        val cover: String,
        val author: String,
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
