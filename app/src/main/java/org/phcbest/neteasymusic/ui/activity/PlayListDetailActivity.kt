package org.phcbest.neteasymusic.ui.activity

import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseActivity
import org.phcbest.neteasymusic.bean.UserPlaylistBean
import org.phcbest.neteasymusic.databinding.ActivityPlayListDetailBinding
import org.phcbest.neteasymusic.utils.ndk_link.GaussianBlurUtils

class PlayListDetailActivity : BaseActivity() {

    private var playlist: UserPlaylistBean.Playlist? = null
    lateinit var binding: ActivityPlayListDetailBinding

    companion object {
        const val TAG = "PlayListDetailActivity"
    }

    override fun initPresenter() {
    }

    override fun initView() {
        playlist = intent.getSerializableExtra(TAG) as UserPlaylistBean.Playlist

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.sample_avatar)
        GaussianBlurUtils.newInstance().getGaussBlurBmp(bitmap, 100)
        binding.ivPlaylistDetailCover.setImageBitmap(bitmap)
    }

    override fun initEvent() {
        super.initEvent()
    }


    override fun getViewBinding(): ViewBinding {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.activity_play_list_detail,
            null, false
        ) as ActivityPlayListDetailBinding
        return binding
    }
}