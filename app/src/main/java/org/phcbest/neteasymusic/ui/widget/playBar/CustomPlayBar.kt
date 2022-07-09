package org.phcbest.neteasymusic.ui.widget.playBar

import android.os.Build
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.bean.SongDetailBean
import java.util.*


class CustomPlayBar {

    companion object {
        private const val TAG = "CustomPlayBar"

        private val instance = CustomPlayBar()
        fun newInstance(): CustomPlayBar {
            return instance
        }
    }

    var viewHolder: ViewHolder? = null
    fun initView(view: View): CustomPlayBar {
        viewHolder = ViewHolder(view)
        viewHolder?.mPlayName?.setOnClickListener {
            playBarClick()
        }
        return this
    }

    private var playBarClick: () -> Unit = {}
    fun setPlayBarClick(click: () -> Unit) {
        playBarClick = click
    }

    fun setUIPause() {
        viewHolder?.mPlayCover?.stopTurn()
        viewHolder?.mPlayBtn?.pause()
    }

    fun setUIResume() {
        viewHolder?.mPlayCover?.startTurn()
        viewHolder?.mPlayBtn?.play()
//                    serviceBind?.resumeOrStart()
    }


    class ViewHolder(var view: View) {
        var mPlayBtn: PlayBarProgressButton? = null
        var mPlayCover: TurnImageCover? = null
        var mPlayList: ImageView? = null
        var mPlayName: TextView? = null

        init {
            mPlayBtn = view.findViewById(R.id.btn_play_bar_play)
            mPlayCover = view.findViewById(R.id.iv_play_bar_cover)
            mPlayList = view.findViewById(R.id.btn_play_bar_list)
            mPlayName = view.findViewById(R.id.tv_play_bar_name)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun setViewPerformance(song: SongDetailBean.Song) {
        Log.i(TAG, "setData: $song")
        if (song.tns == null || song.tns.isEmpty()) {
            viewHolder?.mPlayName?.text = song.name
        } else {
            val nts = StringJoiner("/", "(", ")")
            for (tn in song.tns) {
                nts.add(tn)
            }
            viewHolder?.mPlayName?.text = "${song.name + nts.toString()}"
        }
        viewHolder?.mPlayCover!!.setBackAndFrontGround(-1, song.al.picUrl)
    }

}