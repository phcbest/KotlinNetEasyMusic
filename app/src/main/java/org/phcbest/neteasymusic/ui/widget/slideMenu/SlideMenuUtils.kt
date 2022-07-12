package org.phcbest.neteasymusic.ui.widget.slideMenu

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

    fun doSlideMenuAdapter() {
        SlideMenuData.getInstance().menuCenterList
    }
}