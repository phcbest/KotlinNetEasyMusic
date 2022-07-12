package org.phcbest.neteasymusic.ui.widget.slideMenu

import androidx.recyclerview.widget.RecyclerView
import org.phcbest.neteasymusic.databinding.ActivityMainBinding
import org.phcbest.neteasymusic.databinding.AdapterNavigetionHomeMenuItemBinding

class SlideMenuUtils {

    companion object {
        private var slideMenuUtils: SlideMenuUtils? = null
        fun getInstance(): SlideMenuUtils {
            if (slideMenuUtils == null) {
                slideMenuUtils = SlideMenuUtils()
            }
            return slideMenuUtils!!
        }
    }

    fun doSlideMenuAdapter(
        binding: ActivityMainBinding,
    ) {
        binding.incNavHome.rvCenter.adapter =
            SlideMenuAdapter(SlideMenuData.getInstance().menuCenterList)
        binding.incNavHome.rvMusicService.adapter =
            SlideMenuAdapter(SlideMenuData.getInstance().menuMusicServiceList)
        binding.incNavHome.rvOther.adapter =
            SlideMenuAdapter(SlideMenuData.getInstance().menuOtherList)
        binding.incNavHome.rvAppInfo.adapter =
            SlideMenuAdapter(SlideMenuData.getInstance().menuAppInfoList)

    }
}