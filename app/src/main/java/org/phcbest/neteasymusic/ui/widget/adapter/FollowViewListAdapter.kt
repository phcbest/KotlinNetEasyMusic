package org.phcbest.neteasymusic.ui.widget.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.bean.UserFollowBean
import org.phcbest.neteasymusic.databinding.AdapterFollowListItemBinding

class FollowViewListAdapter : RecyclerView.Adapter<FollowViewListAdapter.ViewHolder>() {
    class ViewHolder(var binding: AdapterFollowListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    var mUserFollowBean: UserFollowBean? = null

    fun setUserFollowBean(userFollowBean: UserFollowBean) {
        this.mUserFollowBean = userFollowBean
        notifyItemRangeChanged(0, userFollowBean.follow.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.adapter_follow_list_item,
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.follow = mUserFollowBean!!.follow[position]

    }

    override fun getItemCount(): Int {
        return if (mUserFollowBean == null || mUserFollowBean?.follow!!.isEmpty()) {
            0
        } else {
            mUserFollowBean?.follow?.size!!
        }
    }
}