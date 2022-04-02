package org.phcbest.neteasymusic.service

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import org.phcbest.neteasymusic.presenter.PresenterManager

private const val TAG = "MusicPlayService"

/**
 * 播放音乐的服务
 */
class MusicPlayService : Service(), MediaPlayer.OnPreparedListener,
    MediaPlayer.OnCompletionListener {
    //音频播放器
    private val mediaPlayer = MediaPlayer()


    //操作器
    private var myBinder: MyBinder? = null


    override fun onCreate() {
        super.onCreate()
        //获取歌曲ID
        Log.i(TAG, "onCreate: ")
        init()
    }

    /**
     * seavice的启动命令,用于设置启动状态
     * @return 如果service被kill,系统会带着最后一次传入的intent参数重新启动
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        myBinder?.release()
    }


    override fun onBind(intent: Intent): IBinder {
        myBinder = MyBinder()
        return myBinder!!
    }

    private fun init() {
        mediaPlayer.setAudioAttributes(
            AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()
        )
        //加载完成回调
        mediaPlayer.setOnPreparedListener(this)
        //播放完成回调
        mediaPlayer.setOnCompletionListener(this)
    }

    inner class MyBinder() : Binder() {

        var playState = false

        private var mPause: () -> Unit = {
            Log.i(TAG, "暂停逻辑空执行")
        }
        private var mResume: () -> Unit = {
            Log.i(TAG, "继续逻辑空执行")
        }

        var mLoadSongSuccess: (duration: Int) -> Unit = {}


        fun play(songID: String) {
            try {
                PresenterManager.getInstance().getMainPresenter()
                    .getSongDownLoadUrl(songID, success = { songUrlBean ->
                        Log.i(TAG, "play: 加载音乐")
                        mediaPlayer.setDataSource(songUrlBean.data[0].url)
                        mediaPlayer.prepareAsync()
                    }, error = { throwable ->

                    })
            } catch (e: Exception) {
                Log.e(TAG, "play: 准备阶段资源加载出错", e)
            }
        }

        fun pause() {
            mediaPlayer.pause()
            mPause()
            playState = true
        }

        fun resumeOrStart() {
            mediaPlayer.start()
            mResume()
            playState = false
        }

        fun stop() {
            mediaPlayer.stop()
        }

        fun release() {
            mediaPlayer.release()
        }

        /**
         * 切换播放的位置
         */
        fun setPosition() {

        }

        /**
         * 设置暂停和继续的事件
         */
        fun setEvent(
            pause: () -> Unit,
            resume: () -> Unit,
            loadSongSuccess: (duration: Int) -> Unit
        ) {
            mPause = pause
            mResume = resume
            mLoadSongSuccess = loadSongSuccess
        }

    }

    override fun onPrepared(mp: MediaPlayer?) {
        //打印歌曲时间长度
        Log.i(TAG, "歌曲开始播放，时间总共长度: ${mediaPlayer.duration / 60000f}")
//        myBinder!!.resumeOrStart()
        //加载外部逻辑
        myBinder!!.mLoadSongSuccess(mediaPlayer.duration)
    }

    override fun onCompletion(mp: MediaPlayer?) {
        myBinder!!.pause()
    }

}