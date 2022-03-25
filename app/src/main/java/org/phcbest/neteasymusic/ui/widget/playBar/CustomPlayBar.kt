package org.phcbest.neteasymusic.ui.widget.playBar

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.bean.Song

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

    class ViewHolder(var view: View) {
        var mPlayBtn: PlayBarProgressButton? = null
        var mPlayCover: ImageView? = null
        var mPlayList: ImageView? = null
        var mPlayName: TextView? = null

        init {
            mPlayBtn = view.findViewById(R.id.btn_play_bar_play)
            mPlayCover = view.findViewById(R.id.iv_play_bar_cover)
            mPlayList = view.findViewById(R.id.iv_play_bar_list)
            mPlayName = view.findViewById(R.id.tv_play_bar_name)
        }

    }

    fun setData(song: Song) {
        viewHolder?.mPlayName?.text = "${song.name}[${song.transNames[0]}]"
        Glide.with(viewHolder!!.view).load(song.album.artist.img1v1Url)
            .into(viewHolder!!.mPlayCover!!)
    }

}