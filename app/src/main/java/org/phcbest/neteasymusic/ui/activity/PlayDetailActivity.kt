package org.phcbest.neteasymusic.ui.activity

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseActivity
import org.phcbest.neteasymusic.databinding.ActivityPlayDetailBinding
import org.phcbest.neteasymusic.utils.ui.StatusBarUtil

class PlayDetailActivity : BaseActivity() {

    override fun initPresenter() {
    }

    override fun initView() {
        //设置透明状态栏
        isTransparentStatusBar = true
        //设置状态栏沉浸
        binding?.llTop?.setPadding(0, StatusBarUtil.getStatusBarHeight(this), 0, 0)
    }

    var binding: ActivityPlayDetailBinding? = null
    override fun getViewBinding(): ViewBinding {
        binding = DataBindingUtil.inflate(layoutInflater,
            R.layout.activity_play_detail,
            null,
            false)
        return binding!!
    }
}