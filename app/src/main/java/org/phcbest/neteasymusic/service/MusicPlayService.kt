package org.phcbest.neteasymusic.service

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import org.phcbest.neteasymusic.utils.RetrofitApi
import org.phcbest.neteasymusic.utils.RetrofitUtils

private const val TAG = "MusicPlayService"

/**
 * 播放音乐的服务
 */
class MusicPlayService : Service() {
    //音频播放器
    private val mediaPlayer = MediaPlayer()


    override fun onCreate() {
        super.onCreate()
        //获取歌曲ID
        
        //获取歌曲下载路径
        RetrofitUtils.newInstance().getDownloadUrlById("")
        init()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    private fun init() {
        try {

            mediaPlayer.setAudioAttributes(
                AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()
            )
            mediaPlayer.setDataSource("http://m701.music.126.net/20220327231803/cf9664b4efc6447a3c4da4a4801ac015/jdymusic/obj/wo3DlMOGwrbDjj7DisKw/8955009583/a44c/8b30/9bfe/cd16800967e08d8d048f07333223e0bd.mp3")
            mediaPlayer.prepareAsync()
        } catch (e: Exception) {
            Log.e(TAG, "init: 设置资源,准备阶段出错", e)
        }
        mediaPlayer.setOnPreparedListener() { mp ->
            Log.i(TAG, "init: 开始播放")
            mediaPlayer.start()
        }
    }


}