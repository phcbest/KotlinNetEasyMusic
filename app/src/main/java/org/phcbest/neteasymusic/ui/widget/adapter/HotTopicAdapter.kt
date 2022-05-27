package org.phcbest.neteasymusic.ui.widget.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.bean.HotTopicBean
import org.phcbest.neteasymusic.databinding.AdapterHotTopicItemBinding

class HotTopicAdapter : RecyclerView.Adapter<HotTopicAdapter.ViewHolder>() {
    class ViewHolder(var binding: AdapterHotTopicItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var mHotTopicBean: HotTopicBean? = null

    fun setHotTopicBean(hotTopicBean: HotTopicBean?) {
        this.mHotTopicBean = hotTopicBean
        notifyItemRangeChanged(0, mHotTopicBean?.hot?.size!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.adapter_hot_topic_item,
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        mHotTopicBean?.hot?.get(position).let {
            holder.binding.title = it?.title
        }
    }

    override fun getItemCount(): Int {
        return if (mHotTopicBean == null) {
            0
        } else {
            mHotTopicBean?.hot?.size!!
        }
    }
}