package org.phcbest.neteasymusic.ui.activity

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
import org.phcbest.neteasymusic.bean.PlayListDetailBean
import org.phcbest.neteasymusic.databinding.ActivityMainBinding
import org.phcbest.neteasymusic.service.MusicPlayerService
import org.phcbest.neteasymusic.ui.activity.viewmodel.MainActivityViewModel
import org.phcbest.neteasymusic.ui.dialog.DialogBox
import org.phcbest.neteasymusic.ui.fragment.*
import org.phcbest.neteasymusic.ui.widget.adapter.PlayListDialogAdapter
import org.phcbest.neteasymusic.ui.widget.playBar.CustomPlayBar
import kotlin.math.log


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

    //歌单id
    private val playListId = "413126379"


    override fun initView() {
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
        //判断网络状态来 初始化主页位置,这里直接执行点击下发ui事件
        binding.navMain.findViewById<View>(R.id.menu_discover).performClick()

        //设置播放栏的点击按钮事件
        binding.mainPlayBar.btnPlayBarList.setOnClickListener {
            mainPlayListDialog.dialogMainPlaylistBinding.isDialogLoad = true
            //获得歌单
            mainActivityViewModel.setPlayListDetail(playListId)
            //显示dialog
            mainPlayListDialog.dialog.show()
        }
        //dialog playlist的点击事件设置
        playListDialogAdapter.setOnclick { track: PlayListDetailBean.Playlist.Track, i: Int ->
            Log.i(TAG, "initEvent: $track")
            //进行音乐播放
            mMusicPlayerService?.switchSong(index = i)
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
        mainActivityViewModel.setPlayListDetail(playListId)
        //绑定services
        doBindServices()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun observeViewModel() {
        super.observeViewModel()
        mainActivityViewModel.playlistDetailLiveData.observe(this, {
            if (it != null) {
                playListDialogAdapter.setPlayListDetail(it)
                mainPlayListDialog.dialogMainPlaylistBinding.isDialogLoad = false
                //当service不为空的时候设置播放列表x
                mMusicPlayerService?.setPlayList(it.playlist)
            } else {
                mainPlayListDialog.dialogMainPlaylistBinding.isDialogLoad = true
            }
        })

    }


    fun setTheObserverAfterTheServiceIsBound() {
        //用户控制播放暂停的回调
        mMusicPlayerService?.isPlayerLD?.observe(this, {
            if (it) {
                binding.mainPlayBar.btnPlayBarPlay.play()
                binding.mainPlayBar.ivPlayBarCover.startTurn()
            } else {
                binding.mainPlayBar.btnPlayBarPlay.pause()
                binding.mainPlayBar.ivPlayBarCover.stopTurn()
            }
        })

        //加载好音乐后的回调
        mMusicPlayerService?.currentSongEntityLD?.observe(this, {
            binding.mainPlayBar.ivPlayBarCover.setBackAndFrontGround(-1, it.cover)
            binding.mainPlayBar.songName = it.name
            binding.mainPlayBar.songAuthor = it.author
        })

        //进度接收
        mMusicPlayerService?.playProgressLD?.observe(this, {
            Log.i(TAG, "setTheObserverAfterTheServiceIsBound: playProgress $it")
            binding.mainPlayBar.btnPlayBarPlay.updateProgress(it)
        })
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


    //切换fragment
    private var currentFragment: Fragment? = null
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