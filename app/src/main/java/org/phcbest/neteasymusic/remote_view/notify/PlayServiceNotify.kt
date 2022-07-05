package org.phcbest.neteasymusic.remote_view.notify

import android.app.*
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import okhttp3.internal.notifyAll
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseApplication
import org.phcbest.neteasymusic.ui.activity.MainActivity

class PlayServiceNotify {
    /**
     * 使用单例模式构建
     */
    companion object {
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

    init {
        val remoteViews =
            RemoteViews(BaseApplication.appContext?.packageName, R.layout.remote_playservice_ctrl)
        manager =
            BaseApplication.appContext?.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager

        //创建notificationChannel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel("PlayServiceCtrl", "云上音乐", NotificationManager.IMPORTANCE_HIGH)
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
                .setContentIntent(pendingIntent)
                .setContent(remoteViews)
                .setOngoing(true)
                .setChannelId("PlayServiceCtrl")
                .setPriority(if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) NotificationManager.IMPORTANCE_HIGH else Notification.PRIORITY_MAX)
                .build()
    }

    fun showNotify() {
        manager?.notify(1, notification)
    }
}