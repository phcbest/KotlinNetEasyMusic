package org.phcbest.neteasymusic.ui.activity

import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.ViewModelProviders
import androidx.viewbinding.ViewBinding
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseActivity
import org.phcbest.neteasymusic.databinding.ActivityStartBinding
import org.phcbest.neteasymusic.ui.activity.viewmodel.SplashActivityViewModel
import org.phcbest.neteasymusic.utils.Constants
import org.phcbest.neteasymusic.utils.RetrofitApi
import org.phcbest.neteasymusic.utils.SpStorageUtils
import org.phcbest.neteasymusic.utils.ToastUtils
import org.phcbest.neteasymusic.utils.ui.StatusBarUtil

private const val TAG = "StartActivity"

class SplashActivity : BaseActivity() {
    private var binding: ActivityStartBinding? = null

    private var splashActivityViewModel: SplashActivityViewModel? = null

    var mIvStartImg: ImageView? = null


    override fun initPresenter() {

        //判断登录状态
        if (SpStorageUtils.newInstance()
                .getCookie().contains(SpStorageUtils.SP_NULL) ||
            SpStorageUtils.newInstance()
                .getLoginBean() == null
        ) {
            handlerStartActivity.sendEmptyMessageDelayed(1, 500)
        } else {
            //检测用户登录是否过期
            splashActivityViewModel?.checkCookieState()
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        splashActivityViewModel?.loginStatus?.observe(this, {
            if (it) {
                handlerStartActivity.sendEmptyMessageDelayed(0, 500)
            } else {
                ToastUtils.SEND_SMG("登录过期需要登录")
                handlerStartActivity.sendEmptyMessageDelayed(0, 500)
            }
        })
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
        }
    }

    override fun initView() {
        splashActivityViewModel = ViewModelProviders.of(this)[SplashActivityViewModel::class.java]
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