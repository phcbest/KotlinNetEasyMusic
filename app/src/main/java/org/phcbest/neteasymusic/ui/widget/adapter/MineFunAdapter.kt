package org.phcbest.neteasymusic.ui.widget.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.databinding.AdapterMineFunBinding
import org.phcbest.neteasymusic.ui.widget.adapter.adapter_data.MineFunAdapterData
import java.util.concurrent.CopyOnWriteArrayList

class MineFunAdapter(var onItemClick: (MineFunAdapterData.MineFunItemEnum) -> Unit) :
    RecyclerView.Adapter<MineFunAdapter.ViewHolder>() {

    var mineFunItemBeans: CopyOnWriteArrayList<MineFunAdapterData.MineFunItemBean> =
        MineFunAdapterData.getInstance().mineFunItemBeans

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
        holder.itemView.setOnClickListener { _ ->
            onItemClick(mineFunItemBeans[position].designation)
        }
    }

    override fun getItemCount(): Int {
        return mineFunItemBeans.size
    }
}