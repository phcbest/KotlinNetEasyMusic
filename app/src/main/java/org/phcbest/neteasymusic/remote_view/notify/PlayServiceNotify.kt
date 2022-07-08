package org.phcbest.neteasymusic.remote_view.notify

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.graphics.rotationMatrix
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseApplication
import org.phcbest.neteasymusic.ui.activity.MainActivity
import kotlin.math.round
import kotlin.random.Random

class PlayServiceNotify(
    private var doPlayOrPause: () -> Unit,
    private var doPrevious: () -> Unit,
    private var doNext: () -> Unit,
) {
    /**
     * 使用单例模式构建
     */
    companion object {
        private const val TAG = "PlayServiceNotify"
        private var playServiceNotify: PlayServiceNotify? = null
        fun getInstance(
            doPlayOrPause: () -> Unit,
            doPrevious: () -> Unit,
            doNext: () -> Unit,
        ): PlayServiceNotify {
            if (playServiceNotify == null) {
                playServiceNotify = PlayServiceNotify(doPlayOrPause, doPrevious, doNext)
            }
            return playServiceNotify!!
        }
    }

    private var notification: Notification? = null
    private var manager: NotificationManager? = null
    private var remoteViews: RemoteViews? = null

    //注册BroadcastReceiver来接收广播操作
    private val broadcast = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (val stringExtra = intent?.getStringExtra("type")) {
                "play_btn" -> {
                    Log.i(TAG, "onReceive: 播放按钮")
                    doPlayOrPause()
                }
                "previous_btn" -> {
                    Log.i(TAG, "onReceive: 上一首按钮")
                    doPrevious()
                }
                "next_btn" -> {
                    Log.i(TAG, "onReceive: 下一首按钮")
                    doNext()
                }
                else -> {
                    Log.i(TAG, "onReceive: 触发,但是type不匹配，type为${stringExtra}")
                }
            }
        }
    }

    init {

        remoteViews =
            RemoteViews(BaseApplication.appContext?.packageName, R.layout.remote_playservice_ctrl)
        manager =
            BaseApplication.appContext?.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager

        //创建notificationChannel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel("PlayServiceCtrl", "云上音乐", NotificationManager.IMPORTANCE_LOW)
            manager?.createNotificationChannel(notificationChannel)
        }

        //remoteViews点击事件设置
        //播放|暂停
        remoteViews?.setOnClickPendingIntent(R.id.iv_play_btn,
            buildPendingIntent(1, "play_btn"))
//        //上一曲
        remoteViews?.setOnClickPendingIntent(R.id.iv_previous_btn,
            buildPendingIntent(2, "previous_btn"))
//        //下一曲
        remoteViews?.setOnClickPendingIntent(R.id.iv_next_btn,
            buildPendingIntent(3, "next_btn"))

        //设置初始图片预防加载错误
        remoteViews?.setImageViewResource(R.id.iv_song_cover, R.drawable.cd_bg)

        notification =
            NotificationCompat.Builder(BaseApplication.appContext!!, "音乐")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("云音乐")
//                .setContentIntent(pendingIntent)
                .setContent(remoteViews)
                .setOngoing(true)
                .setChannelId("PlayServiceCtrl")
//                .setPriority(if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) NotificationManager.IMPORTANCE_LOW else Notification.PRIORITY_LOW)
                .build()
    }

    /**
     * 构建PendingIntent
     */
    private fun buildPendingIntent(requestCode: Int, type: String): PendingIntent? {
        val intentFilter = IntentFilter()
        intentFilter.addAction(BaseApplication.appContext?.packageName)
        BaseApplication.appContext?.registerReceiver(broadcast, intentFilter)
        val intent = Intent(BaseApplication.appContext?.packageName)
        intent.putExtra("type", type)
        return PendingIntent.getBroadcast(BaseApplication.appContext,
            requestCode,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
    }

    fun showNotify() {
        manager?.notify(1, notification)
    }

    fun cancelNotify() {
        manager?.cancel(1)
    }


    fun setViewInfo(songName: String, author: String, coverUrl: String) {
        remoteViews?.setTextViewText(R.id.tv_song_name, songName)
        remoteViews?.setTextViewText(R.id.tv_author, author)
        var url = "$coverUrl?param=150y150"
        Log.i(TAG, "setViewInfo: $url")
        //网络请求获得bitmap,图片加载需要是同步的
//        val futureTarget = Glide.with(BaseApplication.appContext!!).asBitmap().load(coverUrl)
//            .apply(RequestOptions.bitmapTransform(RoundedCorners(50))).submit()
//        remoteViews?.setImageViewBitmap(R.id.iv_song_cover, futureTarget.get())
//        manager?.notify(1, notification)
//        注意图片不能太大,不然通知加载不出来不显示
        Glide.with(BaseApplication.appContext!!).asBitmap().load(url)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    Log.i(TAG, "onResourceReady: 加载通知图片成功")
                    remoteViews?.setImageViewBitmap(R.id.iv_song_cover, resource)
//                    remoteViews?.setImageViewResource(R.id.iv_song_cover,
//                        R.drawable.cd_bg)
                    manager?.notify(1, notification)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    remoteViews?.setImageViewResource(R.id.iv_song_cover, R.drawable.cd_bg)
                    Log.i(TAG, "onLoadCleared: 取消加载通知栏图片")
                    manager?.notify(1, notification)

                }
            })
    }

    /**
     * 修改播放按钮状态
     * @param isPlaying true是播放状态,false是暂停状态
     */
    fun switchPlayButton(isPlaying: Boolean) {
        if (isPlaying) {
            remoteViews?.setImageViewResource(R.id.iv_play_btn,
                R.drawable.ic_play_detail_play_black)
        } else {
            remoteViews?.setImageViewResource(R.id.iv_play_btn,
                R.drawable.ic_play_detail_pause_black)
        }
        manager?.notify(1, notification)
    }

}