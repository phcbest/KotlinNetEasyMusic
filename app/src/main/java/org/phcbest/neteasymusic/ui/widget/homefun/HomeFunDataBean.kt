package org.phcbest.neteasymusic.ui.widget.homefun

import android.view.View
import org.phcbest.neteasymusic.R
import java.util.concurrent.CopyOnWriteArrayList

/**
 * item数据类
 */
class HomeFunDataBean {
    //线程安全的list
    var items: CopyOnWriteArrayList<HomeFunItemBean>? = CopyOnWriteArrayList()

    companion object {
        fun newInstance() = HomeFunDataBean()
    }

    init {
        items?.add(HomeFunItemBean(R.drawable.ic_mic, "每日推荐", {}))
        items?.add(HomeFunItemBean(R.drawable.ic_mic, "私人FM", {}))
        items?.add(HomeFunItemBean(R.drawable.ic_mic, "歌单©", {}))
        items?.add(HomeFunItemBean(R.drawable.ic_mic, "排行榜", {}))
        items?.add(HomeFunItemBean(R.drawable.ic_mic, "数字专辑", {}))
        items?.add(HomeFunItemBean(R.drawable.ic_mic, "专注冥想", {}))
        items?.add(HomeFunItemBean(R.drawable.ic_mic, "歌房", {}))
        items?.add(HomeFunItemBean(R.drawable.ic_mic, "游戏专区", {}))
    }

    class HomeFunItemBean(image: Int, title: String, onTap: (viewL: View) -> Unit) {
        var image: Int = image
        var title: String = title
        var onTap = onTap
    }
}