package org.phcbest.neteasymusic.ui.widget.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.bean.PlayListDetailBean
import org.phcbest.neteasymusic.databinding.AdapterPlaylistDetailItemBinding
import java.util.*

class PlayListDetailAdapter : RecyclerView.Adapter<PlayListDetailAdapter.ViewHolder>() {

    private lateinit var songTracks: List<PlayListDetailBean.Playlist.Track>

    class ViewHolder(var binding: AdapterPlaylistDetailItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.adapter_playlist_detail_item, parent, false) as
                AdapterPlaylistDetailItemBinding
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val track = songTracks[position]
        holder.binding.tvSongIndex.text = (position + 1).toString()
        //设置名字
        val name = StringJoiner("/", "(", ")")
        track.alia.forEach { name.add(it) }
        holder.binding.tvSongName.text = track.name + name.toString()
        //设置创建者
        val creator = StringJoiner("/")
        track.ar.forEach { creator.add(it.name) }
        holder.binding.tvSongCreator.text = "${creator.toString()}-${track.al.name}"
    }

    override fun getItemCount(): Int {
        return songTracks.size ?: 0
    }

    public fun setSongTracks(songTracks: List<PlayListDetailBean.Playlist.Track>) {
        this.songTracks = songTracks
        notifyDataSetChanged()
    }

}