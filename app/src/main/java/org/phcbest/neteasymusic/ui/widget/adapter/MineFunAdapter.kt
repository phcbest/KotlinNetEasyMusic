package org.phcbest.neteasymusic.ui.widget.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.databinding.AdapterMineFunBinding
import org.phcbest.neteasymusic.ui.widget.adapter.adapter_data.MineFunAdapterBean
import java.util.concurrent.CopyOnWriteArrayList

class MineFunAdapter : RecyclerView.Adapter<MineFunAdapter.ViewHolder>() {

    var mineFunItemBeans: CopyOnWriteArrayList<MineFunAdapterBean.MineFunItemBean> =
        MineFunAdapterBean.getInstance().mineFunItemBeans

    class ViewHolder(var binding: AdapterMineFunBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.adapter_mine_fun,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.ivHomeFunItem.setImageResource(mineFunItemBeans[position].image)
        holder.binding.tvHomeFunItem.text = mineFunItemBeans[position].tag
    }

    override fun getItemCount(): Int {
        return mineFunItemBeans.size
    }
}