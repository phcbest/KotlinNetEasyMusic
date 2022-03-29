package org.phcbest.neteasymusic

import android.annotation.TargetApi
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import org.phcbest.neteasymusic.base.BaseActivity
import org.phcbest.neteasymusic.databinding.ActivityMainBinding
import org.phcbest.neteasymusic.presenter.IGetSongInfoPresenter
import org.phcbest.neteasymusic.presenter.PresenterManager
import org.phcbest.neteasymusic.service.MusicPlayService
import org.phcbest.neteasymusic.ui.*
import org.phcbest.neteasymusic.ui.widget.playBar.CustomPlayBar
import org.phcbest.neteasymusic.utils.Constant

private const val TAG = "MainActivity"

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    private var discoverFragment: Fragment? = null
    private var radioStationFragment: Fragment? = null
    private var mineFragment: Fragment? = null
    private var followFragment: Fragment? = null
    private var cloudVillageFragment: Fragment? = null
    private var mCustomPlayBar: CustomPlayBar? = null
    private var getSongInfoPresenter: IGetSongInfoPresenter? = null

    override fun initView() {
        setStatusBarColor(window, 0xffffff)
        //设置导航栏tint效果为null
        binding.navMain.itemIconTintList = null
        //初始化fragment
        discoverFragment = DiscoverFragment.newInstance()
        radioStationFragment = RadioStationFragment.newInstance()
        mineFragment = MineFragment.newInstance()
        followFragment = FollowFragment.newInstance()
        cloudVillageFragment = CloudVillageFragment.newInstance()
        //执行playbar初始化
        mCustomPlayBar = CustomPlayBar.newInstance().initView(binding.root)
    }


    override fun initEvent() {
        super.initEvent()
        //kotlin回调demo
        binding.navMain.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_discover -> {
                    switchFragment(discoverFragment!!)
                }
                R.id.menu_radio_station -> {
                    switchFragment(radioStationFragment!!)
                }
                R.id.menu_mine -> {
                    switchFragment(mineFragment!!)
                }
                R.id.menu_follow -> {
                    switchFragment(followFragment!!)
                }
                R.id.menu_cloud_village -> {
                    switchFragment(cloudVillageFragment!!)
                }
            }
            true
        }
        //判断网络状态来 初始化主页位置
        binding.navMain.findViewById<View>(R.id.menu_discover).performClick()

    }

    override fun initPresenter() {
        getSongInfoPresenter = PresenterManager.getInstance().getMainPresenter()
        getSongInfoPresenter!!.getSongDetailByIDs(
            "29732992",
            { songDetailBean ->
                songDetailBean.songs[0].let {
                    mCustomPlayBar!!.setData(it)
                }
            },
            {})
    }

    override fun getViewBinding(): ViewBinding {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding
    }


    var currentFragment: Fragment? = null
    private fun switchFragment(targetFragment: Fragment) {
        currentFragment = currentFragment ?: targetFragment
        val beginTransaction = supportFragmentManager.beginTransaction()
        if (!targetFragment.isAdded) {
            beginTransaction.hide(currentFragment!!)
                .add(R.id.fragment_home, targetFragment)
                .show(targetFragment)
                .commit()
        } else {
            beginTransaction.hide(currentFragment!!).show(targetFragment).commit()
        }
        currentFragment = targetFragment
    }

    //设置状态栏颜色
    @TargetApi(Build.VERSION_CODES.M)
    fun setStatusBarColor(window: Window, color: Int) {
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        //设置状态栏颜色
        window.setStatusBarColor(color)
        //状态栏白底黑字的实现方法
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        //去掉系统状态栏下的windowContentOverlay
        findViewById<View>(android.R.id.content).foreground = null
    }
}