package org.phcbest.neteasymusic.ui.widget.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.bean.SimilaritySongBean
import org.phcbest.neteasymusic.databinding.AdapterDiscoverSimiSongItemBinding

class DiscoverSimiSongAdapter : RecyclerView.Adapter<DiscoverSimiSongAdapter.ViewHolder>() {
    class ViewHolder(var binding: AdapterDiscoverSimiSongItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var similaritySongBean: SimilaritySongBean? = null

    fun setItemsData(similaritySongBean: SimilaritySongBean) {
        this.similaritySongBean = similaritySongBean
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.adapter_discover_simi_song_item, parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.song = similaritySongBean!!.songs[position]
    }

    override fun getItemCount(): Int {
        return if (similaritySongBean == null) {
            0
        } else {
            similaritySongBean!!.songs.size
        }
    }
}