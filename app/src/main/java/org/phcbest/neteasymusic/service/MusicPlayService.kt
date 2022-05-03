package org.phcbest.neteasymusic.service

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.annotation.IntRange
import androidx.lifecycle.MutableLiveData
import org.phcbest.neteasymusic.presenter.PresenterManager
import org.phcbest.neteasymusic.utils.CountDownTimeWithPause
import kotlin.math.max

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

    private lateinit var mProgressCDT: CountDownTimeWithPause


    /**
     * MediaPlayer将资源准备好的时候进行回调
     */
    override fun onPrepared(mp: MediaPlayer?) {
        //打印歌曲时间长度
        Log.i(TAG, "歌曲开始播放，时间总共长度: ${mediaPlayer.duration / 60000f}")
//        myBinder!!.resumeOrStart()
//        myBinder!!.pause()
        //初始化定时器
        myBinder!!.initCountdownTime(mediaPlayer.duration)
        //live data 推送-2
        myBinder!!.progressLiveData.postValue(-2)
    }

    /**
     * 歌曲播放完成
     */
    override fun onCompletion(mp: MediaPlayer?) {
        myBinder!!.pause()
    }

    inner class MyBinder() : Binder() {

        /**
         * 这个参数设置播放初始状态,true 为暂停,false为播放
         */
        var playState = true


        //livedata更新进度  -1为播放结束 0-100为进度 -2为加载完成
        var progressLiveData: MutableLiveData<Int> = MutableLiveData()

        /**
         * 初始化倒计时
         * @param duration 歌曲时间
         */
        fun initCountdownTime(duration: Int) {
            //歌曲时间均分100份
            val durationOfOneDegree = duration / 100
            mProgressCDT = object : CountDownTimeWithPause(
                duration.toLong(), durationOfOneDegree.toLong()
            ) {
                override fun onFinish() {
                    //结束发送-1
                    mProgressCDT.cancel()
                    mProgressCDT.start().pause()
                    progressLiveData.postValue(-1)
                    //释放资源
                }

                override fun onTick(lastTickStart: Long) {
                    //百分比进度推出(全部时间减去剩余时间)
                    val x = (((duration - lastTickStart.toFloat()) / duration) * 100).toInt()
                    Log.i(TAG, "onTick: $x")
                    progressLiveData.postValue(x)
                }
            }
            mProgressCDT.start().pause()
        }


        /**
         * 加载音乐
         */
        fun play(songID: String) {
            try {
                PresenterManager.getInstance().getSongInfoPresenter()
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

        /**
         * 控制媒体播放器和CDT的暂停
         */
        fun pause() {
            mProgressCDT.pause()
            mediaPlayer.pause()
            playState = true
        }

        /**
         * 控制媒体播放器和CDT的恢复
         */
        fun resumeOrStart() {
            mProgressCDT.resume()
            mediaPlayer.start()
            playState = false
        }

        fun stop() {
            mediaPlayer.stop()
        }

        fun release() {
            mediaPlayer.release()
        }

    }
}
