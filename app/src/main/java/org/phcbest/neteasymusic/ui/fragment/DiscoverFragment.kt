package org.phcbest.neteasymusic.ui.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewbinding.ViewBinding
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseFragment
import org.phcbest.neteasymusic.base.PAGE_STATE
import org.phcbest.neteasymusic.bean.Banner
import org.phcbest.neteasymusic.bean.SongEntity
import org.phcbest.neteasymusic.databinding.FragmentDiscoverBinding
import org.phcbest.neteasymusic.ui.activity.MainActivity
import org.phcbest.neteasymusic.ui.fragment.viewmodel.DiscoverFragmentViewModel
import org.phcbest.neteasymusic.ui.widget.adapter.DiscoverRecommendPlayListAdapter
import org.phcbest.neteasymusic.ui.widget.adapter.DiscoverSimiSongAdapter
import org.phcbest.neteasymusic.ui.widget.banner.CustomBanner
import org.phcbest.neteasymusic.ui.widget.homefun.CustomHomeFun

private const val TAG = "DiscoverFragment"

class DiscoverFragment : BaseFragment() {

    private var binding: FragmentDiscoverBinding? = null
    private var customBanner: CustomBanner? = null
    private lateinit var discoverFragmentViewModel: DiscoverFragmentViewModel

    private lateinit var discoverRecommendPlayListAdapter: DiscoverRecommendPlayListAdapter
    private lateinit var discoverSimiSongAdapter: DiscoverSimiSongAdapter

    override fun initPresenter() {
        discoverFragmentViewModel.setDiscoverBannerLiveData()
        discoverFragmentViewModel.setRecommendPlayListLiveData()
        discoverFragmentViewModel.setSimiSongLiveDataByRecordRecent(100)
    }

    override fun observeViewModel() {
        super.observeViewModel()
        //设置banner数据
        discoverFragmentViewModel.discoverBannerLiveData.observe(this) {
            if (it != null) {
                customBanner?.startShowAfterAdapter(it)
            }
        }
        //设置推荐歌单
        discoverFragmentViewModel.recommendPlayListLiveData.observe(this) {
            if (it != null) {
                discoverRecommendPlayListAdapter.setItemData(it, 5)
            }
        }
        //设置根据最近歌曲推荐
        discoverFragmentViewModel.recordRecentSongItemLiveData.observe(this) {
            if (it != null) {
                binding?.recommendedBySong = it.data.name
            }
        }
        //设置个性化推荐歌曲
        discoverFragmentViewModel.simiSongLiveData.observe(this) {
            if (it != null) {
                discoverSimiSongAdapter.setItemsData(it)
                binding?.haveSimiRecommend = it.songs.isNotEmpty()
            }
        }


        //管理状态显示
        discoverFragmentViewModel.dataState.observe(this) {
            it.forEach { map ->
                if (map.value == PAGE_STATE.FAIL) {
                    //如果有一个状态是未成功
                    binding?.isLoad = true
                    return@forEach
                }
            }
            //没有未成功状态，设置未成功
            binding?.isLoad = false
        }
    }

    override fun initView() {
        binding?.isLoad = true

        //初始化banner
        customBanner = CustomBanner().setView(binding!!.root)

        discoverRecommendPlayListAdapter = DiscoverRecommendPlayListAdapter()
        binding?.rvRecommendPlaylist?.adapter = discoverRecommendPlayListAdapter

        binding?.rvSimiSong?.layoutManager = StaggeredGridLayoutManager(3, RecyclerView.HORIZONTAL)
        discoverSimiSongAdapter = DiscoverSimiSongAdapter()
        binding?.rvSimiSong?.adapter = discoverSimiSongAdapter

        CustomHomeFun().setView(binding!!.root).startAdapter()
    }

    override fun initEvent() {
        super.initEvent()
        //设置推荐的点击刷新
        binding!!.llSimiReflash.setOnClickListener {
            discoverFragmentViewModel.setSimiSongLiveDataByRecordRecent(100)
            //执行旋转动画
            //设置动画
            val rotateAnimation = RotateAnimation(0F, 360F,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f)
            rotateAnimation.duration = 500
            binding!!.ivSimiReflash.animation = rotateAnimation
            binding!!.ivSimiReflash.startAnimation(rotateAnimation)
        }
        //设置根据最近听歌推荐歌曲的点击回调
        discoverSimiSongAdapter.setClick { song, position ->
            Log.i(TAG, "initEvent discoverSimiSongAdapter: $song")
            val mainActivity = this.activity as MainActivity
            mainActivity.mMusicPlayerService?.addSongToList(SongEntity(
                song.id.toString(),
                song.name,
                song.id.toString(),
                song.album.picUrl,
                song.artists[0].name

            ),
                true)
        }
        //设置customBanner的项目点击事件
        customBanner?.bannerAdapter?.setOnClick { banner: Banner, i: Int ->
            Log.i(TAG, "initEvent: index $i banner $banner")
        }
    }

    override fun onBaseDestroyView() {
        if (customBanner != null) {
            customBanner!!.countDownTimer.cancel()
        }
    }

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        discoverFragmentViewModel =
            ViewModelProviders.of(this)[DiscoverFragmentViewModel::class.java]
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_discover, container, false)
        return binding!!
    }

    //这写法类似静态方法,可以直接调用该方法中的静态成员和静态方法
    companion object {
        //如果指定的是函数,那么函数就是静态的
        @JvmStatic
        fun newInstance() = DiscoverFragment()
    }


}