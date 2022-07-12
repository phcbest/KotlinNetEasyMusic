package org.phcbest.neteasymusic.ui.activity

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.viewbinding.ViewBinding
import com.google.android.material.appbar.AppBarLayout
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseActivity
import org.phcbest.neteasymusic.bean.UserPlaylistBean
import org.phcbest.neteasymusic.databinding.ActivityPlayListDetailBinding
import org.phcbest.neteasymusic.ui.activity.viewmodel.PlayListDetailViewModel
import org.phcbest.neteasymusic.ui.widget.adapter.PlayListDetailAdapter
import org.phcbest.neteasymusic.utils.ui.StatusBarUtil
import kotlin.math.abs

class PlayListDetailActivity : BaseActivity() {

    private var playlist: UserPlaylistBean.Playlist? = null
    lateinit var binding: ActivityPlayListDetailBinding
    private lateinit var playListDetailViewModel: PlayListDetailViewModel
    private lateinit var playListDetailAdapter: PlayListDetailAdapter

    companion object {
        const val TAG = "PlayListDetailActivity"
    }

    override fun initPresenter() {
        playlist.let {
            val id = it?.id.toString()
            Log.i(TAG, "initPresenter 歌单id: $id")
            playListDetailViewModel.setPlayListDetail(id)
        }
    }

    override fun initView() {
        binding.isLoad = true
        isSemiTransparentStatusBar = true
        //app_bar_playlist_detail需要设置背景和padding
        val statusBarHeight = StatusBarUtil.getStatusBarHeight(this)
        binding.appBarPlaylistDetail.setPadding(0,
            statusBarHeight, 0, 0)
        playListDetailAdapter = PlayListDetailAdapter()
        binding.rvPlaylistDetail.adapter = playListDetailAdapter

    }

    override fun initEvent() {
        super.initEvent()
        //设置不同状态下的title显示
        binding.appBarPlaylistDetail.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            //检查是否折叠
            when {
                abs(verticalOffset) == appBarLayout?.totalScrollRange -> {
                    // 折叠
                    binding.toolbarPlaylistDetail.title = playlist?.name
                    binding.toolbarPlaylistDetail.setTitleTextColor(Color.BLACK)
                    binding.toolbarPlaylistDetail.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
                    //设置状态栏字体颜色为黑色
                    isSemiTransparentStatusBarBlackTint = true
                    setStatusBar(0xffffff)

                }
                verticalOffset == 0 -> {
                    // 展开
                    binding.toolbarPlaylistDetail.title = "歌单"
                    binding.toolbarPlaylistDetail.setTitleTextColor(Color.WHITE)
                    binding.toolbarPlaylistDetail.setNavigationIcon(R.drawable.ic_baseline_arrow_back_white_24)
                    //设置状态栏字体颜色为白色
                    isSemiTransparentStatusBarBlackTint = false
                    setStatusBar(0xffffff)
                }
                else -> {
                    // 中间某个地方
                }
            }
        })
        binding.toolbarPlaylistDetail.setNavigationOnClickListener {
            finish()
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        playListDetailViewModel.playListDetail.observe(this
        ) { t ->
            Log.i(TAG, "onChanged: 数据变化触发")
            if (t == null) {
                finish()
            } else {
                binding.playlist = t.playlist
                playListDetailAdapter.setSongTracks(t.playlist.tracks)
                binding.isLoad = false
            }
        }
    }

    override fun getViewBinding(): ViewBinding {
        playlist = intent.getSerializableExtra(TAG) as UserPlaylistBean.Playlist
        playListDetailViewModel = ViewModelProviders.of(this)[PlayListDetailViewModel::class.java]
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.activity_play_list_detail,
            null, false
        ) as ActivityPlayListDetailBinding
        return binding
    }
}