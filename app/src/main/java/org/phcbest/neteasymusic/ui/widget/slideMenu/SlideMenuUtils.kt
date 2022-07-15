package org.phcbest.neteasymusic.ui.widget.slideMenu

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.phcbest.neteasymusic.databinding.ActivityMainBinding
import org.phcbest.neteasymusic.utils.MMKVStorageUtils
import org.phcbest.neteasymusic.utils.RetrofitUtils

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

    fun setAccountInfo(binding: ActivityMainBinding) {
        //通过MMKV获得userId
        MMKVStorageUtils.getInstance().getLoginBean().let { loginBean ->
            //通过UserId 请求用户账号信息
            RetrofitUtils.newInstance().getUserDetail(loginBean?.account?.id.toString())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe({
                    //件用户信息设置进UI
//                    val nickname = it.profile.nickname
//                    val avatarUrl = it.profile.avatarUrl
                    binding.incNavHome.userDetailBean = it
                }, {
                    it.printStackTrace()
                })
        }
        //获得vip等级
        RetrofitUtils.newInstance().getVipGrowthpoint().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe({
                binding.incNavHome.vipGrowthpoint = it
            }, {
                it.printStackTrace()
            })
    }
}