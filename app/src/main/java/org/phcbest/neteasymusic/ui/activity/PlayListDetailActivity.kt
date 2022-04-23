package org.phcbest.neteasymusic.ui.activity

import android.graphics.Color
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseActivity
import org.phcbest.neteasymusic.bean.UserPlaylistBean
import org.phcbest.neteasymusic.databinding.ActivityPlayListDetailBinding

class PlayListDetailActivity : BaseActivity() {

    private var playlist: UserPlaylistBean.Playlist? = null

    companion object {
        const val TAG = "PlayListDetailActivity"
    }

    override fun initPresenter() {
    }

    override fun initView() {
        playlist = intent.getSerializableExtra(TAG) as UserPlaylistBean.Playlist
    }

    override fun initEvent() {
        super.initEvent()
    }


    override fun getViewBinding(): ViewBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.activity_play_list_detail,
            null, false
        ) as ActivityPlayListDetailBinding
    }
}