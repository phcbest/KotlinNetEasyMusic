package org.phcbest.neteasymusic.ui.widget.adapter.adapter_data

import org.phcbest.neteasymusic.R
import java.util.concurrent.CopyOnWriteArrayList

class MineFunAdapterData {

    var mineFunItemBeans: CopyOnWriteArrayList<MineFunItemBean> = CopyOnWriteArrayList()

    companion object {
        private val singleton = MineFunAdapterData()
        fun getInstance() = singleton
    }


    init {
        mineFunItemBeans.add(
            MineFunItemBean(
                R.drawable.ic_mine_fun_recently_played,
                "最近播放",
                MineFunItemEnum.RECENTLY_PLAYED
            )
        )
        mineFunItemBeans.add(
            MineFunItemBean(
                R.drawable.ic_mine_fun_download,
                "本地/下载",
                MineFunItemEnum.DOWNLOAD
            )
        )
        mineFunItemBeans.add(
            MineFunItemBean(
                R.drawable.ic_mine_fun_cloud_disk,
                "云盘",
                MineFunItemEnum.CLOUD_DISK
            )
        )
        mineFunItemBeans.add(
            MineFunItemBean(
                R.drawable.ic_mine_fun_purchased,
                "已购",
                MineFunItemEnum.PURCHASED
            )
        )
        mineFunItemBeans.add(
            MineFunItemBean(
                R.drawable.ic_mine_fun_friend,
                "我的好友",
                MineFunItemEnum.FRIEND
            )
        )
        mineFunItemBeans.add(
            MineFunItemBean(
                R.drawable.ic_mine_fun_star,
                "收藏和赞",
                MineFunItemEnum.STAR
            )
        )
        mineFunItemBeans.add(
            MineFunItemBean(
                R.drawable.ic_mine_fun_podcast,
                "我的播客",
                MineFunItemEnum.PODCAST
            )
        )
        mineFunItemBeans.add(
            MineFunItemBean(
                R.drawable.ic_mine_fun_acg,
                "ACG专区",
                MineFunItemEnum.ACG
            )
        )
    }

    class MineFunItemBean(var image: Int, var tag: String, var designation: MineFunItemEnum)

    enum class MineFunItemEnum {
        RECENTLY_PLAYED,
        DOWNLOAD,
        CLOUD_DISK,
        PURCHASED,
        FRIEND,
        STAR,
        PODCAST,
        ACG,
    }

}