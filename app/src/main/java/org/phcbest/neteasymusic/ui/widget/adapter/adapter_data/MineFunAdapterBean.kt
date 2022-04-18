package org.phcbest.neteasymusic.ui.widget.adapter.adapter_data

import org.phcbest.neteasymusic.R
import java.util.concurrent.CopyOnWriteArrayList

class MineFunAdapterBean {

    var mineFunItemBeans: CopyOnWriteArrayList<MineFunItemBean> = CopyOnWriteArrayList()

    companion object {
        private val singleton = MineFunAdapterBean()
        fun getInstance() = singleton
    }


    init {
        mineFunItemBeans.add(MineFunItemBean(R.drawable.ic_mine_fun_recently_played, "最近播放"))
        mineFunItemBeans.add(MineFunItemBean(R.drawable.ic_mine_fun_download, "本地/下载"))
        mineFunItemBeans.add(MineFunItemBean(R.drawable.ic_mine_fun_cloud_disk, "云盘"))
        mineFunItemBeans.add(MineFunItemBean(R.drawable.ic_mine_fun_purchased, "已购"))
        mineFunItemBeans.add(MineFunItemBean(R.drawable.ic_mine_fun_friend, "我的好友"))
        mineFunItemBeans.add(MineFunItemBean(R.drawable.ic_mine_fun_star, "收藏和赞"))
        mineFunItemBeans.add(MineFunItemBean(R.drawable.ic_mine_fun_podcast, "我的播客"))
        mineFunItemBeans.add(MineFunItemBean(R.drawable.ic_mine_fun_acg, "ACG专区"))
    }

    class MineFunItemBean(var image: Int, var tag: String)

}