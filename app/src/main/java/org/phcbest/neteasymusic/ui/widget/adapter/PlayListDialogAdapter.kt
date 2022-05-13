package org.phcbest.neteasymusic.ui.widget.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.bean.PlayListDetailBean
import org.phcbest.neteasymusic.databinding.AdapterDialogMainPlaylistItemBinding

class PlayListDialogAdapter : RecyclerView.Adapter<PlayListDialogAdapter.ViewHolder>() {

    class ViewHolder(var binding: AdapterDialogMainPlaylistItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var playListDetailBean: PlayListDetailBean? = null

    private lateinit var clock: (PlayListDetailBean.Playlist.Track) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.adapter_dialog_main_playlist_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.root.setOnClickListener { clock(playListDetailBean!!.playlist.tracks[position]) }
        holder.binding.songItem = playListDetailBean!!.playlist.tracks[position]
    }

    override fun getItemCount(): Int {
        return if (playListDetailBean == null) {
            0
        } else {
            playListDetailBean!!.playlist.tracks.size
        }
    }

    fun setPlayListDetail(playListDetailBean: PlayListDetailBean) {
        this.playListDetailBean = playListDetailBean
        notifyDataSetChanged()
    }

    fun setOnclick(clock: (PlayListDetailBean.Playlist.Track) -> Unit) {
        this.clock = clock
    }
}