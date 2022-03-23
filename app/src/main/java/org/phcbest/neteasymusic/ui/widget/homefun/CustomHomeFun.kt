package org.phcbest.neteasymusic.ui.widget.homefun

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.databinding.AdapterHomeFunItemBinding

class CustomHomeFun {

    private var rvHomeFun: RecyclerView? = null

    fun setView(view: View): CustomHomeFun {
        rvHomeFun = view.findViewById(R.id.rv_home_function)
        rvHomeFun!!.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        return this
    }

    fun startAdapter() {
        rvHomeFun!!.adapter = HomeFunAdapter()
    }

    inner class HomeFunAdapter : RecyclerView.Adapter<HomeFunAdapter.ViewHolder>() {
        //item数据
        val itemData = HomeFunDataBean.newInstance().items

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): HomeFunAdapter.ViewHolder {
            val view = AdapterHomeFunItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ViewHolder(view)
        }


        override fun onBindViewHolder(holder: HomeFunAdapter.ViewHolder, position: Int) {
            val homeFunItemBean = itemData!![position]
            holder.ivHomeFun.setImageResource(homeFunItemBean.image)
            holder.tvHomeFun.text = homeFunItemBean.title
            holder.itemView.setOnClickListener(homeFunItemBean.onTap)
        }

        override fun getItemCount(): Int {
            return itemData!!.size
        }

        inner class ViewHolder(view: AdapterHomeFunItemBinding) :
            RecyclerView.ViewHolder(view.root) {
            var ivHomeFun: ImageView = view.ivHomeFun
            var tvHomeFun: TextView = view.tvHomeFun
        }
    }
}