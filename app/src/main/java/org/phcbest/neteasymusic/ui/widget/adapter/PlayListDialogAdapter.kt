package org.phcbest.neteasymusic.ui.widget.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.bean.PlayListDetailBean
import org.phcbest.neteasymusic.bean.SongEntity
import org.phcbest.neteasymusic.databinding.AdapterDialogMainPlaylistItemBinding
import org.phcbest.neteasymusic.utils.MMKVStorageUtils

class PlayListDialogAdapter : RecyclerView.Adapter<PlayListDialogAdapter.ViewHolder>() {

    class ViewHolder(var binding: AdapterDialogMainPlaylistItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var songEntities: List<SongEntity>? = null

    private lateinit var clock: (SongEntity, Int) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.adapter_dialog_main_playlist_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.root.setOnClickListener {
            clock(songEntities!![position],
                position)
        }
        holder.binding.songItem = songEntities!![position]
    }

    override fun getItemCount(): Int {
        return if (songEntities == null) {
            0
        } else {
            songEntities!!.size
        }
    }

    fun setPlayListDetailSync() {
        MMKVStorageUtils.newInstance().getPlayList().let {
            this.songEntities = it
            notifyDataSetChanged()
        }
    }

    fun setOnclick(click: (SongEntity, Int) -> Unit) {
        this.clock = click
    }
}