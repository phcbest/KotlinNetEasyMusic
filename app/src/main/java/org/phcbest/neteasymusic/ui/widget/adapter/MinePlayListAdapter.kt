package org.phcbest.neteasymusic.ui.widget.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.bean.UserPlaylistBean
import org.phcbest.neteasymusic.databinding.AdapterMinePlaylistItemBinding

class MinePlayListAdapter : RecyclerView.Adapter<MinePlayListAdapter.ViewHolder>() {

    class ViewHolder(var binding: AdapterMinePlaylistItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var playlist: List<UserPlaylistBean.Playlist>? = null

    fun setItemData(playlist: List<UserPlaylistBean.Playlist>) {
        this.playlist = playlist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.adapter_mine_playlist_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        playlist?.get(position).let { holder.binding.playlist = it }
    }

    override fun getItemCount(): Int {
        //如果为空返回0
        return playlist?.size ?: 0
    }
}