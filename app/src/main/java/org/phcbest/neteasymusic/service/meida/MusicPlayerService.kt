package org.phcbest.neteasymusic.service.meida

import android.app.Service
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.*


import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import org.phcbest.neteasymusic.bean.SongEntity
import org.phcbest.neteasymusic.presenter.PresenterManager
import org.phcbest.neteasymusic.remote_view.notify.PlayServiceNotify
import org.phcbest.neteasymusic.utils.MMKVStorageUtils

private const val TAG = "MusicPlayService"

/**
 * 播放音乐的服务
 */
class MusicPlayerService : Service() {

    var mPlaylist: MutableLiveData<MutableList<SongEntity>> = MutableLiveData(arrayListOf())
    private lateinit var mMediaPlayer: MediaPlayer
    private var mCurrentSongIndex: Int = 0

    private var mPlayServiceNotify: PlayServiceNotify? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        //初始化播放器
        mMediaPlayer = MediaPlayer()
        mMediaPlayer.setWakeMode(applicationContext, PowerManager.PARTIAL_WAKE_LOCK);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //初始化mediaPlayer的事件
        initMediaPlayerEvent()
        //初始化通知
        mPlayServiceNotify = PlayServiceNotify.getInstance()
        mPlayServiceNotify?.showNotify()
    }

    //改变播放位置
    fun seekTo(position: Float) {
        if (!this::mMediaPlayer.isInitialized) return
        //计算播放位置
        val duration = mMediaPlayer.duration
        val newPosition = (position * duration).toInt()
        mMediaPlayer.seekTo(newPosition)
    }

    data class SongProgress(val currentProgress: Int, val fullProgress: Int)

    var playProgressLD: MutableLiveData<SongProgress?> = MutableLiveData()

    /**
     *  控制发送进度给外部
     */
    private var mProgressHandler: Handler = object : Handler(Looper.myLooper()!!) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            //获得当前播放的进度
            if (this@MusicPlayerService::mMediaPlayer.isInitialized) {

//                Log.i(TAG,
//                    "handleMessage: 歌曲长度${mMediaPlayer.duration}  当前位置${mMediaPlayer.currentPosition}")
                //将进度推出
                if (mMediaPlayer.duration <= 0 || mMediaPlayer.duration <= 0) {
                    playProgressLD.postValue(null)
                } else {
                    //100F * mMediaPlayer.currentPosition / mMediaPlayer.duration
                    playProgressLD.postValue(SongProgress(mMediaPlayer.currentPosition,
                        mMediaPlayer.duration))
                }
                sendEmptyMessageDelayed(0, 1000)
            }
        }
    }
//        progressHandler.sendEmptyMessage(0)


    var currentSongEntityLD: MutableLiveData<SongEntity> = MutableLiveData()

    private fun initMediaPlayerEvent() {
        //播放预加载完成的回调
        mMediaPlayer.setOnPreparedListener {
            val songEntity = mPlaylist.value?.get(mCurrentSongIndex)
            //取消消息
            mProgressHandler.removeMessages(0)
            //设置播放的状态栏样式
            mPlayServiceNotify?.setViewInfo(songEntity?.name!!,
                songEntity.author,
                songEntity.cover)
            //准备加载,进行播放
            playControl(2)
            currentSongEntityLD.postValue(songEntity!!)
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
        //设置状态栏的播放状态
        mPlayServiceNotify?.switchPlayButton(!mMediaPlayer.isPlaying)

        //状态推出
        isPlayerLD.postValue(mMediaPlayer.isPlaying)
        //设置进度推出  声音渐弱和渐强
        if (mMediaPlayer.isPlaying) {
            mProgressHandler.sendEmptyMessage(0)
        } else {
            mProgressHandler.removeMessages(0)
        }
    }


    fun setPlayListSync() {
//        playlist.tracks.map {
//            this.mPlaylist.add(SongEntity(
//                it.id.toString(),
//                it.name,
//                it.id.toString(),
//                it.al.picUrl,
//                if (it.ar!!.isEmpty()) {
//                    "未知歌手"
//                } else {
//                    val sj = StringJoiner("-")
//                    it.ar.map { ar -> sj.add(ar.name) }
//                    sj.toString()
//                }
//            ))
//        }
        MMKVStorageUtils.newInstance().getPlayList().let {
            this.mPlaylist.postValue(it as MutableList<SongEntity>?)
        }
    }

    //添加歌曲到播放列表
    fun addSongToList(songEntity: SongEntity, playWhenAppend: Boolean) {
        //歌曲列表不包含当前歌曲,添加到当前播放的歌曲后面
        mPlaylist.value!!.add(mCurrentSongIndex + 1, songEntity)
        //存储当前歌单到mmkv
        MMKVStorageUtils.newInstance().storagePlayList(mPlaylist.value!!)
        this.mPlaylist.postValue(mPlaylist.value)
        //加载歌曲
        if (playWhenAppend) {
            mCurrentSongIndex++
            aSyncLoadSong()
        }
    }

    private fun aSyncLoadSong() {
        //复位播放器
        mMediaPlayer.reset()
        //网络请求获得播放地址
        Log.i(TAG, "aSyncLoadSong: 加载音乐的下标 $mCurrentSongIndex")
        PresenterManager.getInstance().getSongInfoPresenter()
            .getSongDownLoadUrl(mPlaylist.value!![mCurrentSongIndex].songId, {
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
    fun switchSongByPosition(index: Int) {
        if (index in mPlaylist.value!!.indices) {
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
            if (mCurrentSongIndex >= mPlaylist.value!!.size - 1) {
                mCurrentSongIndex = 0
            } else {
                mCurrentSongIndex++
            }

        } else {
            //判断第一首
            if (mCurrentSongIndex == 0) {
                mCurrentSongIndex = mPlaylist.value!!.size - 1
            } else {
                mCurrentSongIndex--
            }

        }
        aSyncLoadSong()
    }


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
