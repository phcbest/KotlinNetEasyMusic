package org.phcbest.neteasymusic.ui.widget.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.bean.UserFollowBean
import org.phcbest.neteasymusic.databinding.AdapterFollowListItemBinding

class FollowViewListAdapter(var layoutManager: RecyclerView.LayoutManager) :
    RecyclerView.Adapter<FollowViewListAdapter.ViewHolder>() {
    class ViewHolder(var binding: AdapterFollowListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    var mUserFollowBean: UserFollowBean? = null

    fun addUserFollowBean(userFollowBean: UserFollowBean) {
        if (this.mUserFollowBean == null) {
            this.mUserFollowBean = userFollowBean
            notifyItemRangeChanged(0, this.mUserFollowBean!!.follow.size)
        } else {
            val oldSize = this.mUserFollowBean!!.follow.size
            for (follow in userFollowBean.follow) {
                this.mUserFollowBean?.follow?.add(follow)
            }
            notifyItemRangeChanged(0, this.mUserFollowBean!!.follow.size)
            layoutManager.scrollToPosition(oldSize)
        }

    }

    //点击事件的设置
    var mOnItemClickListener: (position: Int, follow: UserFollowBean.Follow) -> Unit =
        { _, _ -> }

//    fun setOnItemClickListener(listener: (position: Int, follow: UserFollowBean.Follow) -> Unit) {
//        this.mOnItemClickListener = listener
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.adapter_follow_list_item,
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.follow = mUserFollowBean!!.follow[position]
        holder.binding.root.setOnClickListener {
            mOnItemClickListener(position, mUserFollowBean!!.follow[position])
        }
    }

    override fun getItemCount(): Int {
        return if (mUserFollowBean == null || mUserFollowBean?.follow!!.isEmpty()) {
            0
        } else {
            mUserFollowBean?.follow?.size!!
        }
    }
}