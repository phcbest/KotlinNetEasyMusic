package org.phcbest.neteasymusic.ui.activity

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.viewbinding.ViewBinding
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseActivity
import org.phcbest.neteasymusic.base.BaseApplication
import org.phcbest.neteasymusic.databinding.ActivityMainBinding
import org.phcbest.neteasymusic.presenter.IGetSongInfoPresenter
import org.phcbest.neteasymusic.presenter.PresenterManager
import org.phcbest.neteasymusic.service.MusicPlayService
import org.phcbest.neteasymusic.ui.activity.viewmodel.MainActivityViewModel
import org.phcbest.neteasymusic.ui.fragment.*
import org.phcbest.neteasymusic.ui.widget.playBar.CustomPlayBar


class MainActivity : BaseActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding

    private var discoverFragment: Fragment? = null
    private var radioStationFragment: Fragment? = null
    private var mineFragment: Fragment? = null
    private var followFragment: Fragment? = null
    private var cloudVillageFragment: Fragment? = null
    private var mCustomPlayBar: CustomPlayBar? = null
    private var getSongInfoPresenter: IGetSongInfoPresenter? = null
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun initView() {
        mainActivityViewModel = ViewModelProviders.of(this)[MainActivityViewModel::class.java]
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

    @RequiresApi(Build.VERSION_CODES.N)
    override fun initPresenter() {
        getSongInfoPresenter = PresenterManager.getInstance().getSongInfoPresenter()
        //加载播放bar ui表现
        getSongInfoPresenter!!.getSongDetailByIDs(
            "222799",
            { songDetailBean ->
                songDetailBean.songs[0].let {
                    mCustomPlayBar!!.setData(it)
                    bindPlayService()
                }
            },
            {})

    }

    override fun observeViewModel() {
        super.observeViewModel()
        mainActivityViewModel.liveDataPlaylist.observe(this, {

        })
    }

    /**
     * Bind播放服务
     */
    private fun bindPlayService() {
        var serviceBind: MusicPlayService.MyBinder? = null
        val conn = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
//                serviceBind.progressLiveData.observe()
                serviceBind = service as MusicPlayService.MyBinder
                //播放音乐的url
                serviceBind!!.play("222799")

                serviceBind?.progressLiveData?.observe(this@MainActivity, {
                    when (it) {
                        -1 -> {
                            // TODO: 2022/5/2 设置结束后的ui表现
                            binding.mainPlayBar.btnPlayBarPlay.resetProgress()
                        }
                        -2 -> {
//                            binding.mainPlayBar.btnPlayBarPlay
                        }
                        else -> {
                            binding.mainPlayBar.btnPlayBarPlay.updateProgress(it)
                        }
                    }
                })
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Log.i(TAG, "onServiceDisconnected: 服务断开")
            }
        }
        BaseApplication.appContext?.bindService(
            Intent(BaseApplication.appContext, MusicPlayService::class.java),
            conn,
            Service.BIND_AUTO_CREATE
        )
        mCustomPlayBar?.viewHolder?.mPlayBtn?.setOnClickListener {
            //设置ui表现和service表现
            if (serviceBind?.playState!!) {
                mCustomPlayBar!!.setUIResume()
                serviceBind?.resumeOrStart()
            } else {
                mCustomPlayBar!!.setUIPause()
                serviceBind?.pause()
            }
        }
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


}