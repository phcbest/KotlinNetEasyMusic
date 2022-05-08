package org.phcbest.neteasymusic.ui.widget.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.bean.RecommendPlayListBean
import org.phcbest.neteasymusic.databinding.AdapterDiscoverPlaylistCoverItemBinding

class DiscoverRecommendPlayListAdapter :
    RecyclerView.Adapter<DiscoverRecommendPlayListAdapter.ViewHolder>() {

    private var recommendPlayListBean: RecommendPlayListBean? = null
    private var limitSize = 0


    class ViewHolder(var binding: AdapterDiscoverPlaylistCoverItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.adapter_discover_playlist_cover_item, parent, false))
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        holder.binding.recommend = recommendPlayListBean?.recommend!![position]
    }

    override fun getItemCount(): Int {
        return if (recommendPlayListBean?.recommend == null) {
            0
        } else {
            recommendPlayListBean?.recommend!!.size
        }
    }

    fun setItemData(recommendPlayListBean: RecommendPlayListBean, limitSize: Int) {
        this.recommendPlayListBean = recommendPlayListBean
        this.limitSize = limitSize
        notifyItemRangeChanged(0, limitSize - 1)
    }
}