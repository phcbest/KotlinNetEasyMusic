package org.phcbest.neteasymusic.ui.activity

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.viewbinding.ViewBinding
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseActivity
import org.phcbest.neteasymusic.databinding.ActivityStartBinding
import org.phcbest.neteasymusic.utils.SpStorageUtils

private const val TAG = "StartActivity"

class SplashActivity : BaseActivity() {
    private var binding: ActivityStartBinding? = null

    var mIvStartImg: ImageView? = null

    override fun initPresenter() {
        //判断登录状态
        if (SpStorageUtils.newInstance().getLoginInfo() == null) {
            handlerStartActivity.sendEmptyMessageDelayed(1, 500)
        } else {
            //进入主页
            handlerStartActivity.sendEmptyMessageDelayed(0, 500)
        }
    }

    private var handlerStartActivity = object : Handler(Looper.myLooper()!!) {
        override fun handleMessage(msg: Message) {

            if (msg.what == 1) {
                //配置元素共享动画
                val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this@SplashActivity,
                    mIvStartImg!!,
                    getString(R.string.startAndHomeImgElement)
                ).toBundle()
                startActivity(Intent(baseContext, LoginActivity::class.java), bundle)
            } else if (msg.what == 0) {
                startActivity(Intent(baseContext, MainActivity::class.java))
            }
            //结束启动页
            finish()
            //切换动画
            overridePendingTransition(R.anim.anim_activity_fade_out, R.anim.anim_activity_fade_in)
        }
    }

    override fun initView() {
        mIvStartImg = binding?.ivStartImg
    }

    override fun initEvent() {
        super.initEvent()
    }

    override fun getViewBinding(): ViewBinding {
        binding = ActivityStartBinding.inflate(layoutInflater)
        return binding!!
    }
}