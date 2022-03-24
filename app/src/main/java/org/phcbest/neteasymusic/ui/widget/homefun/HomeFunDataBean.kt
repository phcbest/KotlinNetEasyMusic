package org.phcbest.neteasymusic.ui.widget.homefun

import android.app.Application
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseApplication
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList

/**
 * item数据类
 */
private const val TAG = "HomeFunDataBean"

class HomeFunDataBean {
    //线程安全的list
    var items: CopyOnWriteArrayList<HomeFunItemBean>? = CopyOnWriteArrayList()

    companion object {
        fun newInstance() = HomeFunDataBean()
    }

    var drawable: String = "drawable"

    init {
        //获取当前是几号来设置日推ui
        val day = "ic_home_fun_calendar${SimpleDateFormat("dd", Locale.CHINA).format(Date())}"
        Log.i(TAG, "当前日期：$day ")
        val resID = BaseApplication.appContext!!.resources.getIdentifier(
            day,
            drawable,
            BaseApplication.appContext!!.packageName
        )
        items?.add(HomeFunItemBean(resID, "每日推荐", {}))
        items?.add(HomeFunItemBean(R.drawable.ic_home_fun_persion_fm, "私人FM", {}))
        items?.add(HomeFunItemBean(R.drawable.ic_home_fun_music_list, "歌单©", {}))
        items?.add(HomeFunItemBean(R.drawable.ic_home_fun_rank, "排行榜", {}))
        items?.add(HomeFunItemBean(R.drawable.ic_home_fun_special, "数字专辑", {}))
        items?.add(HomeFunItemBean(R.drawable.ic_home_fun_muse, "专注冥想", {}))
        items?.add(HomeFunItemBean(R.drawable.ic_home_fun_sing_room, "歌房", {}))
        items?.add(HomeFunItemBean(R.drawable.ic_home_fun_gamer, "游戏专区", {}))
    }

    class HomeFunItemBean(var image: Int, var title: String, var onTap: (viewL: View) -> Unit) {
    }
}