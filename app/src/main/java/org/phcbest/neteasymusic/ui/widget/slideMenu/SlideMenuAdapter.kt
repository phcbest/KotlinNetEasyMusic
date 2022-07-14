package org.phcbest.neteasymusic.ui.widget.slideMenu

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseApplication
import org.phcbest.neteasymusic.databinding.AdapterNavigetionHomeMenuItemBinding
import org.phcbest.neteasymusic.ui.activity.TestNetActivity

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
        //按钮设置一个点击事件用于测试网络连接
        if (menuBeans[position].flag == 12) {
            holder.adapterMenuItem.root.setOnClickListener {
                val intent = Intent(BaseApplication.appContext,
                    TestNetActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                BaseApplication.appContext?.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return menuBeans.size
    }
}