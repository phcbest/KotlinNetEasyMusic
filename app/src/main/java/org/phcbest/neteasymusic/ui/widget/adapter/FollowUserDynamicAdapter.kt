package org.phcbest.neteasymusic.ui.widget.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.bean.UserEventBean
import org.phcbest.neteasymusic.bean.UserEventContentBean
import org.phcbest.neteasymusic.databinding.AdapterFollowUserDynamicBinding

class FollowUserDynamicAdapter : RecyclerView.Adapter<FollowUserDynamicAdapter.ViewHolder>() {

    class ViewHolder(var binding: AdapterFollowUserDynamicBinding) :
        RecyclerView.ViewHolder(binding.root)


    private var userEventBean: UserEventBean? = null

    fun setUserEventBean(userEventBean: UserEventBean?) {
        this.userEventBean = userEventBean
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.adapter_follow_user_dynamic,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.event = userEventBean?.events?.get(position)
        holder.binding.eventContent = try {
            Gson().fromJson(userEventBean?.events?.get(position)?.json,
                UserEventContentBean::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override fun getItemCount(): Int {
        return if (userEventBean == null) 0 else userEventBean!!.events.size
    }
}