package org.phcbest.neteasymusic.ui.widget.slideMenu

import org.phcbest.neteasymusic.R

class SlideMenuData {
    data class MenuBean(var flag: Int, var title: String, var icon: Int)

    var menuCenterList: MutableList<MenuBean> = mutableListOf()
    var menuMusicServiceList: MutableList<MenuBean> = mutableListOf()
    var menuOtherList: MutableList<MenuBean> = mutableListOf()
    var menuAppInfoList: MutableList<MenuBean> = mutableListOf()

    init {
        //添加center菜单
        menuCenterList.add(MenuBean(11, "我的消息", R.drawable.ic_nav_menu_center_message))
        menuCenterList.add(MenuBean(12, "云贝中心", R.drawable.ic_nav_menu_center_yun_bei))
        menuCenterList.add(MenuBean(13, "创作者中心", R.drawable.ic_nav_menu_center_creater))
        //添加music service菜单
        menuMusicServiceList.add(MenuBean(21, "云村有票", R.drawable.ic_nav_menu_music_service_ticket))
        menuMusicServiceList.add(MenuBean(22, "商城", R.drawable.ic_nav_menu_music_service_mall))
        menuMusicServiceList.add(MenuBean(23,
            "Beat交易平台",
            R.drawable.ic_nav_menu_music_service_beat))
        menuMusicServiceList.add(MenuBean(24, "游戏专区", R.drawable.ic_nav_menu_music_service_game))
        menuMusicServiceList.add(MenuBean(25, "口袋彩铃", R.drawable.ic_nav_menu_music_service_ring))
        //添加other菜单
        menuOtherList.add(MenuBean(31, "设置", R.drawable.ic_nav_menu_other_setting))
        menuOtherList.add(MenuBean(32, "深色模式", R.drawable.ic_nav_menu_other_dark_mode))
        menuOtherList.add(MenuBean(33, "定时关闭", R.drawable.ic_nav_menu_other_close_time))
        menuOtherList.add(MenuBean(34, "个性装扮", R.drawable.ic_nav_menu_other_skin))
        menuOtherList.add(MenuBean(35, "边听边存", R.drawable.ic_nav_menu_other_save_listen))
        menuOtherList.add(MenuBean(36, "在线听歌免流量", R.drawable.ic_nav_menu_other_free_flow))
        menuOtherList.add(MenuBean(37, "音乐黑名单", R.drawable.ic_nav_menu_other_black))
        menuOtherList.add(MenuBean(38, "青少年模式", R.drawable.ic_nav_menu_other_teen_mode))
        menuOtherList.add(MenuBean(39, "音乐闹钟", R.drawable.ic_nav_menu_other_alarm_clock))
        //添加app info菜单
        menuAppInfoList.add(MenuBean(41, "我的订单", R.drawable.ic_nav_menu_info_order))
        menuAppInfoList.add(MenuBean(42, "优惠券", R.drawable.ic_nav_menu_info_coupon))
        menuAppInfoList.add(MenuBean(43, "我的客服", R.drawable.ic_nav_menu_info_customer_service))
        menuAppInfoList.add(MenuBean(44, "分享网易云音乐", R.drawable.ic_nav_menu_info_share))
        menuAppInfoList.add(MenuBean(45, "个人信息手机与使用清单", R.drawable.ic_nav_menu_info_protocol))
        menuAppInfoList.add(MenuBean(46, "个人信息第三方共享清单", R.drawable.ic_nav_menu_info_protocol))
        menuAppInfoList.add(MenuBean(47, "个人信息与隐私保护", R.drawable.ic_nav_menu_info_protocol))
        menuAppInfoList.add(MenuBean(48, "关于", R.drawable.ic_nav_menu_info_about))
    }

    companion object {
        private var slideMenuData: SlideMenuData? = null
        fun getInstance(): SlideMenuData {
            if (slideMenuData == null) {
                slideMenuData = SlideMenuData()
            }
            return slideMenuData!!
        }

    }
}