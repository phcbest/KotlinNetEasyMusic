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

    //??????id
    private val playListId = "413126379"


    override fun initView() {
        //?????????????????????
        isSemiTransparentStatusBar = true
        isSemiTransparentStatusBarBlackTint = true
        //??????????????????tint?????????null
        binding.navMain.itemIconTintList = null
//        //??????????????????????????????
        SlideMenuUtils.getInstance().let {
            it.doSlideMenuAdapter(binding)
            it.setAccountInfo(binding)
        }

        //?????????fragment
        discoverFragment = DiscoverFragment.newInstance()
        radioStationFragment = RadioStationFragment.newInstance()
        mineFragment = MineFragment.newInstance()
        followFragment = FollowFragment.newInstance()
        cloudVillageFragment = CloudVillageFragment.newInstance()
        //????????????????????????????????????
        binding.dlLayout.setDrawerLockMode(LOCK_MODE_LOCKED_CLOSED)
        //????????????????????????????????????
        binding.incNavHome.llNavList.setPadding(0, StatusBarUtil.getStatusBarHeight(this), 0, 0)
        //?????????????????????
        val viewPageAdapter = ViewPageAdapter(this,
            discoverFragment!!,
            radioStationFragment!!,
            mineFragment!!,
            followFragment!!,
            cloudVillageFragment!!)

        binding.vpMainContent.adapter = viewPageAdapter
        binding.vpMainContent.offscreenPageLimit = 5 //???????????????????????????
//        binding.vpMainContent.setPageTransformer(true, ZoomOutPageTransformer())
        //??????playbar?????????
        mCustomPlayBar = CustomPlayBar.newInstance().initView(binding.root)
        //?????????dialog
        mainPlayListDialog = DialogBox.newInstance().initMainPlayListDialog(this)
        //???????????????
        playListDialogAdapter = PlayListDialogAdapter()
        mainPlayListDialog.dialogMainPlaylistBinding.rvDialogNowplay.adapter = playListDialogAdapter
    }


    override fun initEvent() {
        super.initEvent()
        //kotlin??????demo
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
        //??????viewpage???????????????
        binding.vpMainContent.registerOnPageChangeCallback(object :
            androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.navMain.selectedItemId = binding.navMain.menu.getItem(position).itemId
            }
        })

        //????????????????????? ?????????????????????,??????????????????????????????ui??????
        binding.navMain.findViewById<View>(R.id.menu_discover).performClick()

        //????????????
        mainActivityViewModel.setPlayListDetail(playListId)

        //????????????????????????????????????
        binding.mainPlayBar.btnPlayBarList.setOnClickListener {
//            mainPlayListDialog.dialogMainPlaylistBinding.isDialogLoad = true
            //????????????
//            mainActivityViewModel.setPlayListDetail(playListId)
            //??????dialog
            mainPlayListDialog.dialog.show()
        }
        //dialog playlist?????????????????????
        playListDialogAdapter.setOnclick { songEntity: SongEntity, i: Int ->
            Log.i(TAG, "initEvent: $songEntity")
            //??????????????????
            mMusicPlayerService?.switchSongByPosition(index = i)
        }
        //????????????????????????
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
        //????????????????????????
        mainActivityViewModel.setPlayListDetail(playListId)
        //??????services
        doBindServices()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun observeViewModel() {
        super.observeViewModel()
        mainActivityViewModel.playlistDetailLiveData.observe(this) {
            if (it != null) {
                MMKVStorageUtils.getInstance().storagePlayList(it)
                //??????dialog?????????
                playListDialogAdapter.setPlayListDetailSync()
                mainPlayListDialog.dialogMainPlaylistBinding.isDialogLoad = false
                //???service????????????????????????????????????x
                mMusicPlayerService?.setPlayListSync()
            } else {
                mainPlayListDialog.dialogMainPlaylistBinding.isDialogLoad = true
            }
        }

    }


    fun setTheObserverAfterTheServiceIsBound() {
        //?????????????????????????????????
        mMusicPlayerService?.isPlayerLD?.observe(this) {
            if (it) {
                binding.mainPlayBar.btnPlayBarPlay.play()
                binding.mainPlayBar.ivPlayBarCover.startTurn()
            } else {
                binding.mainPlayBar.btnPlayBarPlay.pause()
                binding.mainPlayBar.ivPlayBarCover.stopTurn()
            }
        }

        //???????????????????????????
        mMusicPlayerService?.currentSongEntityLD?.observe(this) {
            binding.mainPlayBar.ivPlayBarCover.setBackAndFrontGround(-1, it.cover)
            binding.mainPlayBar.songName = it.name
            binding.mainPlayBar.songAuthor = it.author
        }

        //????????????
        mMusicPlayerService?.playProgressLD?.observe(this) {
            Log.i(TAG, "setTheObserverAfterTheServiceIsBound: playProgress $it")
            //???????????????
            if (it != null) {
                binding.mainPlayBar.btnPlayBarPlay.updateProgress(100F * it.currentProgress / it.fullProgress)
            }
        }

        //???????????????
        mMusicPlayerService?.mPlaylist?.observe(this) {
            playListDialogAdapter.setPlayListDetailSync()
        }

        //??????????????????????????????
        mCustomPlayBar?.setPlayBarClick {
            startActivity(Intent(this, PlayDetailActivity::class.java))
        }
    }


    /**
     * Bind????????????
     */
    var mMusicPlayerService: MusicPlayerService? = null
    private fun doBindServices() {

        val serviceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                Log.i(TAG, "onServiceConnected: ???????????????????????????")
                val binder = service as MusicPlayerService.MyBinder
                mMusicPlayerService = binder.getService()
                setTheObserverAfterTheServiceIsBound()
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Log.i(TAG, "onServiceDisconnected: ???????????????????????????")
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
     * ????????????????????????
     */
    fun showLeftNavigation() {
        if (binding.dlLayout.isDrawerOpen(GravityCompat.START)) return
        binding.dlLayout.openDrawer(GravityCompat.START)
    }


//    //??????fragment
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

    //????????????????????????
    private inner class ViewPageAdapter(fa: FragmentActivity, vararg var pageList: Fragment) :
        FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = pageList.size

        override fun createFragment(position: Int): Fragment = pageList[position]
    }

    //?????????????????????????????????
    private var lastBackPressTime = -1L

    //????????????????????????
    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()
        if (lastBackPressTime == -1L || currentTime - lastBackPressTime >= 2000) {
            ToastUtils.SEND_SMG("????????????????????????")
            lastBackPressTime = currentTime
        } else {
            finish()
        }
    }

}