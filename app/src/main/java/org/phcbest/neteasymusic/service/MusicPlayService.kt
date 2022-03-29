package org.phcbest.neteasymusic.service

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import org.phcbest.neteasymusic.presenter.PresenterManager
import org.phcbest.neteasymusic.utils.Constant
import kotlin.math.log

private const val TAG = "MusicPlayService"

/**
 * 播放音乐的服务
 */
class MusicPlayService : Service(), MediaPlayer.OnPreparedListener {
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
        mediaPlayer.setOnPreparedListener(this)
    }

    inner class MyBinder() : Binder() {

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
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
            }
        }

        fun resume() {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
            }
        }

        fun stop() {
            mediaPlayer.stop()
        }

        fun release() {
            mediaPlayer.release()
        }

    }

    override fun onPrepared(mp: MediaPlayer?) {
        Log.i(TAG, "onPrepared: 开始播放")
        mp!!.start()
    }

}