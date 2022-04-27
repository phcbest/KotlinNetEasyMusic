package org.phcbest.neteasymusic.ui.activity

import android.util.Log
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.viewbinding.ViewBinding
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseActivity
import org.phcbest.neteasymusic.bean.UserPlaylistBean
import org.phcbest.neteasymusic.databinding.ActivityPlayListDetailBinding
import org.phcbest.neteasymusic.ui.activity.viewmodel.PlayListDetailViewModel
import org.phcbest.neteasymusic.utils.ui.StatusBarUtil

class PlayListDetailActivity : BaseActivity() {

    private var playlist: UserPlaylistBean.Playlist? = null
    lateinit var binding: ActivityPlayListDetailBinding
    private lateinit var playListDetailViewModel: PlayListDetailViewModel

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
        isTransparentStatusBar = true
        //app_bar_playlist_detail需要设置背景和padding
        val statusBarHeight = StatusBarUtil.getStatusBarHeight(this)
        binding.appBarPlaylistDetail.setPadding(0,
            statusBarHeight, 0, 0)

    }

    override fun initEvent() {
        super.initEvent()
        //设置不同状态下的title显示
    }

    override fun observeViewModel() {
        super.observeViewModel()
        playListDetailViewModel.playListDetail.observe(this,
            { t ->
                Log.i(TAG, "onChanged: 数据变化触发")
                if (t == null) {
                    finish()
                } else {
                    binding.playListDetailBean = t
                    binding.isLoad = false
                }
            })
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