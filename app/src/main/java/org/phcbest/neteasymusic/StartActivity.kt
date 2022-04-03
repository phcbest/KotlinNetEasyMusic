package org.phcbest.neteasymusic

import android.os.Build
import android.view.WindowInsets
import android.view.WindowManager
import androidx.viewbinding.ViewBinding
import org.phcbest.neteasymusic.base.BaseActivity
import org.phcbest.neteasymusic.databinding.ActivityStartBinding

class StartActivity : BaseActivity() {
    private var binding: ActivityStartBinding? = null

    override fun initPresenter() {
    }

    override fun initView() {
    }

    override fun initEvent() {
        super.initEvent()
    }

    override fun getViewBinding(): ViewBinding {
        binding = ActivityStartBinding.inflate(layoutInflater)
        return binding!!
    }
}