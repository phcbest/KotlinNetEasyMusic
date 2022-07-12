package org.phcbest.neteasymusic.ui.widget.slideMenu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.databinding.AdapterNavigetionHomeMenuItemBinding

class SlideMenuAdapter(private var menuBeans: MutableList<SlideMenuData.MenuBean>) :
    RecyclerView.Adapter<SlideMenuAdapter.ViewHolder>() {


    class ViewHolder(var adapterMenuItem: AdapterNavigetionHomeMenuItemBinding) :
        RecyclerView.ViewHolder(adapterMenuItem.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.adapter_navigetion_home_menu_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.adapterMenuItem.menuBean = menuBeans[position]
    }

    override fun getItemCount(): Int {
        return menuBeans.size
    }
}