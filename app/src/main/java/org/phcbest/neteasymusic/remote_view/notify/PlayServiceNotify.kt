package org.phcbest.neteasymusic.remote_view.notify

import android.app.*
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseApplication
import org.phcbest.neteasymusic.ui.activity.MainActivity

class PlayServiceNotify {
    /**
     * 使用单例模式构建
     */
    companion object {
        private const val TAG = "PlayServiceNotify"
        private var playServiceNotify: PlayServiceNotify? = null
        fun getInstance(): PlayServiceNotify {
            if (playServiceNotify == null) {
                playServiceNotify = PlayServiceNotify()
            }
            return playServiceNotify!!
        }
    }

    private var notification: Notification? = null
    private var manager: NotificationManager? = null
    private var remoteViews: RemoteViews? = null

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


        val pendingIntent = PendingIntent.getActivity(BaseApplication.appContext,
            1,
            Intent(BaseApplication.appContext, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT)
        notification =
            NotificationCompat.Builder(BaseApplication.appContext!!)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("云音乐")
//                .setContentIntent(pendingIntent)
                .setContent(remoteViews)
                .setOngoing(true)
                .setChannelId("PlayServiceCtrl")
//                .setPriority(if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) NotificationManager.IMPORTANCE_HIGH else Notification.PRIORITY_MAX)
                .build()
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

        //网络请求获得bitmap
        Glide.with(BaseApplication.appContext!!).asBitmap().load(coverUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    Log.i(TAG, "onResourceReady: 加载通知图片成功")
                    remoteViews?.setImageViewBitmap(R.id.iv_song_cover, resource)
                    manager?.notify(1, notification)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    remoteViews?.setImageViewResource(R.id.iv_song_cover, R.drawable.cd_bg)
                    manager?.notify(1, notification)
                    Log.i(TAG, "onLoadCleared: 取消加载通知栏图片")
                }
            })
    }


}