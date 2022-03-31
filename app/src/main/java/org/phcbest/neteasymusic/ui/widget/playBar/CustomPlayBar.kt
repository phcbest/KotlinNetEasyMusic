package org.phcbest.neteasymusic.ui.widget.playBar

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseApplication
import org.phcbest.neteasymusic.bean.SongDetailBean
import org.phcbest.neteasymusic.service.MusicPlayService

private const val TAG = "CustomPlayBar"

class CustomPlayBar {

    companion object {
        fun newInstance(): CustomPlayBar {
            return CustomPlayBar()
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
                serviceBind!!.play("29732992")
                serviceBind?.setPauseAndResumeEvent({
                    viewHolder?.mPlayCover?.stopTurn()
                }, {
                    viewHolder?.mPlayCover?.startTurn()
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

    fun setData(song: SongDetailBean.Song) {
        viewHolder?.mPlayName?.text = "${song.name}[${song.tns[0]}]"
        viewHolder?.mPlayCover!!.setBackAndFrontGround(-1, song.al.picUrl)
        //设置服务
        initPlayService()
    }

}