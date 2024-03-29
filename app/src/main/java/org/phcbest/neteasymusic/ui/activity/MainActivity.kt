package org.phcbest.neteasymusic.ui.activity

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseActivity
import org.phcbest.neteasymusic.bean.SongEntity
import org.phcbest.neteasymusic.databinding.ActivityMainBinding
import org.phcbest.neteasymusic.service.meida.MusicPlayerService
import org.phcbest.neteasymusic.ui.activity.viewmodel.MainActivityViewModel
import org.phcbest.neteasymusic.ui.dialog.DialogBox
import org.phcbest.neteasymusic.ui.fragment.*
import org.phcbest.neteasymusic.ui.widget.adapter.PlayListDialogAdapter
import org.phcbest.neteasymusic.ui.widget.playBar.CustomPlayBar
import org.phcbest.neteasymusic.ui.widget.slideMenu.SlideMenuUtils
import org.phcbest.neteasymusic.utils.MMKVStorageUtils
import org.phcbest.neteasymusic.utils.ToastUtils
import org.phcbest.neteasymusic.utils.ui.StatusBarUtil


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
    private lateinit var mainActivityViewModel: MainActivityViewModel

    private lateinit var mainPlayListDialog: DialogBox.MainPlayListResult

    private lateinit var playListDialogAdapter: PlayListDialogAdapter


    override fun initView() {
        //状态栏设置透明
        isSemiTransparentStatusBar = true
        isSemiTransparentStatusBarBlackTint = true
        //设置底部导航tint效果为null
        binding.navMain.itemIconTintList = null
//        //设置侧滑菜单的适配器
        SlideMenuUtils.getInstance().let {
            it.doSlideMenuAdapter(binding)
            it.setAccountInfo(binding)
        }

        //初始化fragment
        discoverFragment = DiscoverFragment.newInstance()
        radioStationFragment = RadioStationFragment.newInstance()
        mineFragment = MineFragment.newInstance()
        followFragment = FollowFragment.newInstance()
        cloudVillageFragment = CloudVillageFragment.newInstance()
        //设置侧边栏不允许滑动打开
        binding.dlLayout.setDrawerLockMode(LOCK_MODE_LOCKED_CLOSED)
        //设置侧边栏留出状态栏高度
        binding.incNavHome.llNavList.setPadding(0, StatusBarUtil.getStatusBarHeight(this), 0, 0)
        //设置页面适配器
        val viewPageAdapter = ViewPageAdapter(this,
            discoverFragment!!,
            radioStationFragment!!,
            mineFragment!!,
            followFragment!!,
            cloudVillageFragment!!)

        binding.vpMainContent.adapter = viewPageAdapter
        binding.vpMainContent.offscreenPageLimit = 5 //设置预加载页面数量
//        binding.vpMainContent.setPageTransformer(true, ZoomOutPageTransformer())
        //执行playbar初始化
        mCustomPlayBar = CustomPlayBar.newInstance().initView(binding.root)
        //初始化dialog
        mainPlayListDialog = DialogBox.newInstance().initMainPlayListDialog(this)
        //设置适配器
        playListDialogAdapter = PlayListDialogAdapter()
        mainPlayListDialog.dialogMainPlaylistBinding.rvDialogNowplay.adapter = playListDialogAdapter
    }


    override fun initEvent() {
        super.initEvent()
        //kotlin回调demo
        binding.navMain.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_discover -> {
                    binding.vpMainContent.currentItem = 0
                }
                R.id.menu_radio_station -> {
                    binding.vpMainContent.currentItem = 1
                }
                R.id.menu_mine -> {
                    binding.vpMainContent.currentItem = 2
                }
                R.id.menu_follow -> {
                    binding.vpMainContent.currentItem = 3
                }
                R.id.menu_cloud_village -> {
                    binding.vpMainContent.currentItem = 4
                }
            }
            true
        }
        //设置viewpage的滑动监听
        binding.vpMainContent.registerOnPageChangeCallback(object :
            androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.navMain.selectedItemId = binding.navMain.menu.getItem(position).itemId
            }
        })

        //判断网络状态来 初始化主页位置,这里直接执行点击下发ui事件
        binding.navMain.findViewById<View>(R.id.menu_discover).performClick()

        //获得用户喜欢歌单的id
        mainActivityViewModel.setFavoritePlaylistId()


        //获得歌单,似乎逻辑重复了，改用mvvm后不是必须的
//        mainActivityViewModel.setPlayListDetail(playListId)

        //设置播放栏的点击按钮事件
        binding.mainPlayBar.btnPlayBarList.setOnClickListener {
//            mainPlayListDialog.dialogMainPlaylistBinding.isDialogLoad = true
            //获得歌单
//            mainActivityViewModel.setPlayListDetail(playListId)
            //显示dialog
            mainPlayListDialog.dialog.show()
        }
        //dialog playlist的点击事件设置
        playListDialogAdapter.setOnclick { songEntity: SongEntity, i: Int ->
            Log.i(TAG, "initEvent: $songEntity")
            //进行音乐播放
            mMusicPlayerService?.switchSongByPosition(index = i)
        }
        //播放栏按钮的事件
        mCustomPlayBar?.viewHolder?.mPlayBtn?.setOnClickListener {
            if (mMusicPlayerService?.isPlayerLD?.value!!) {
                mMusicPlayerService?.playControl(1)
            } else {
                mMusicPlayerService?.playControl(2)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun initPresenter() {
        //获取一次播放列表
        mainActivityViewModel.favoritePlayListID.observe(this) {
            if (it != -1L) {
                mainActivityViewModel.setPlayListDetail(it.toString())
            }
        }
        //绑定services
        doBindServices()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun observeViewModel() {
        super.observeViewModel()
        mainActivityViewModel.playlistDetailLiveData.observe(this) {
            if (it != null) {
                MMKVStorageUtils.getInstance().storagePlayList(it)
                //设置dialog的歌单
                playListDialogAdapter.setPlayListDetailSync()
                mainPlayListDialog.dialogMainPlaylistBinding.isDialogLoad = false
                //当service不为空的时候设置播放列表x
                mMusicPlayerService?.setPlayListSync()
            } else {
                mainPlayListDialog.dialogMainPlaylistBinding.isDialogLoad = true
            }
        }

    }


    fun setTheObserverAfterTheServiceIsBound() {
        //用户控制播放暂停的回调
        mMusicPlayerService?.isPlayerLD?.observe(this) {
            if (it) {
                binding.mainPlayBar.btnPlayBarPlay.play()
                binding.mainPlayBar.ivPlayBarCover.startTurn()
            } else {
                binding.mainPlayBar.btnPlayBarPlay.pause()
                binding.mainPlayBar.ivPlayBarCover.stopTurn()
            }
        }

        //加载好音乐后的回调
        mMusicPlayerService?.currentSongEntityLD?.observe(this) {
            binding.mainPlayBar.ivPlayBarCover.setBackAndFrontGround(-1, it.cover)
            binding.mainPlayBar.songName = it.name
            binding.mainPlayBar.songAuthor = it.author
        }

        //进度接收
        mMusicPlayerService?.playProgressLD?.observe(this) {
            Log.i(TAG, "setTheObserverAfterTheServiceIsBound: playProgress $it")
            //计算出角度
            if (it != null) {
                binding.mainPlayBar.btnPlayBarPlay.updateProgress(100F * it.currentProgress / it.fullProgress)
            }
        }

        //歌单的变动
        mMusicPlayerService?.mPlaylist?.observe(this) {
            playListDialogAdapter.setPlayListDetailSync()
        }

        //设置播放栏的点击事件
        mCustomPlayBar?.setPlayBarClick {
            startActivity(Intent(this, PlayDetailActivity::class.java))
        }
    }


    /**
     * Bind播放服务
     */
    var mMusicPlayerService: MusicPlayerService? = null
    private fun doBindServices() {

        val serviceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                Log.i(TAG, "onServiceConnected: 服务和活动连接完成")
                val binder = service as MusicPlayerService.MyBinder
                mMusicPlayerService = binder.getService()
                setTheObserverAfterTheServiceIsBound()
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Log.i(TAG, "onServiceDisconnected: 服务和活动断开连接")
                mMusicPlayerService = null
            }
        }
        bindService(Intent(this, MusicPlayerService::class.java),
            serviceConnection,
            BIND_AUTO_CREATE)
    }


    override fun getViewBinding(): ViewBinding {
        mainActivityViewModel = ViewModelProviders.of(this)[MainActivityViewModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding
    }

    /**
     * 打开侧边栏的操作
     */
    fun showLeftNavigation() {
        if (binding.dlLayout.isDrawerOpen(GravityCompat.START)) return
        binding.dlLayout.openDrawer(GravityCompat.START)
    }


//    //切换fragment
//    private var currentFragment: Fragment? = null
//    private fun switchFragment(targetFragment: Fragment) {
//        currentFragment = currentFragment ?: targetFragment
//        val beginTransaction = supportFragmentManager.beginTransaction()
//        if (!targetFragment.isAdded) {
//            beginTransaction.hide(currentFragment!!)
//                .add(R.id.fragment_home, targetFragment)
//                .show(targetFragment)
//                .commit()
//        } else {
//            beginTransaction.hide(currentFragment!!).show(targetFragment).commit()
//        }
//        currentFragment = targetFragment
//    }

    //主页切换的适配器
    private inner class ViewPageAdapter(fa: FragmentActivity, vararg var pageList: Fragment) :
        FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = pageList.size

        override fun createFragment(position: Int): Fragment = pageList[position]
    }

    //上次点击返回按钮的时间
    private var lastBackPressTime = -1L

    //重写返回按钮事件
    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()
        if (lastBackPressTime == -1L || currentTime - lastBackPressTime >= 2000) {
            ToastUtils.SEND_SMG("再次点击确认退出")
            lastBackPressTime = currentTime
        } else {
            finish()
        }
    }

}