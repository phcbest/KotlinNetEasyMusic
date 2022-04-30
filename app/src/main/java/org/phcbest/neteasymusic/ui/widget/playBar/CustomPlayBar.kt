package org.phcbest.neteasymusic.ui.widget.playBar

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseApplication
import org.phcbest.neteasymusic.bean.SongDetailBean
import org.phcbest.neteasymusic.service.MusicPlayService
import java.util.*

private const val TAG = "CustomPlayBar"

class CustomPlayBar {

    companion object {
        private val instance = CustomPlayBar()
        fun newInstance(): CustomPlayBar {
            return instance
        }
    }

    private var viewHolder: ViewHolder? = null
    fun initView(view: View): CustomPlayBar {
        viewHolder = ViewHolder(view)
        return this
    }

    private fun initPlayService() {
        var serviceBind: MusicPlayService.MyBinder? = null
        val conn = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                serviceBind = service as MusicPlayService.MyBinder
                serviceBind!!.play("1930171356")
                serviceBind?.setEvent({
                    viewHolder?.mPlayCover?.stopTurn()
                    viewHolder?.mPlayBtn?.pause()
                }, {
                    viewHolder?.mPlayCover?.startTurn()
                    viewHolder?.mPlayBtn?.play()
//                    serviceBind?.resumeOrStart()
                }, {
                    viewHolder?.mPlayBtn?.setMusicDuration(it)
                })
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Log.i(TAG, "onServiceDisconnected: 服务断开")
            }
        }
        BaseApplication.appContext?.bindService(
            Intent(BaseApplication.appContext, MusicPlayService::class.java),
            conn,
            Service.BIND_AUTO_CREATE
        )
        viewHolder?.mPlayBtn?.setOnClickListener { v ->
            if (serviceBind?.playState!!) {
                serviceBind?.resumeOrStart()
            } else {
                serviceBind?.pause()
            }
        }
    }

    class ViewHolder(var view: View) {
        var mPlayBtn: PlayBarProgressButton? = null
        var mPlayCover: TurnImageCover? = null
        var mPlayList: ImageView? = null
        var mPlayName: TextView? = null

        init {
            mPlayBtn = view.findViewById(R.id.btn_play_bar_play)
            mPlayCover = view.findViewById(R.id.iv_play_bar_cover)
            mPlayList = view.findViewById(R.id.iv_play_bar_list)
            mPlayName = view.findViewById(R.id.tv_play_bar_name)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun setData(song: SongDetailBean.Song) {
        if (song.tns.isEmpty()) {
            viewHolder?.mPlayName?.text = song.name
        } else {
            val nts = StringJoiner("/", "(", ")")
            for (tn in song.tns) {
                nts.add(tn)
            }
            viewHolder?.mPlayName?.text = "${song.name + nts.toString()}"
        }
        viewHolder?.mPlayCover!!.setBackAndFrontGround(-1, song.al.picUrl)
        //设置服务
        initPlayService()
    }

}