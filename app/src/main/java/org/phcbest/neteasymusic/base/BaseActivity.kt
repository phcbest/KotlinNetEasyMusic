package org.phcbest.neteasymusic.base

import android.annotation.TargetApi
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.utils.ui.StatusBarUtil


abstract class BaseActivity : AppCompatActivity() {

    private var vb: ViewBinding? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //生成viewbinding
        vb = getViewBinding()
        //绑定view
        setContentView(vb!!.root)
        initView()
        setStatusBar()
        initEvent()
        initPresenter()
        observeViewModel()
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(
            R.anim.anim_activity_fade_out,
            R.anim.anim_activity_fade_in
        )
    }

    open fun observeViewModel() {

    }

    //使用open 表示该方法是开放的可以重写也可以不重写
    open fun initEvent() {

    }

    protected abstract fun initPresenter()

    protected abstract fun initView()

    protected abstract fun getViewBinding(): ViewBinding

    override fun onDestroy() {
        super.onDestroy()
        if (vb != null) {
            vb = null
        }
        //该生命周期还需要做的事
        this.release()
    }

    open fun release() {

    }

    //设置状态栏颜色
    @TargetApi(Build.VERSION_CODES.KITKAT)
    open fun setStatusBar() {
//        if (isTransparentStatusBar) {
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            window.statusBarColor = Color.TRANSPARENT
//        } else {
//            //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            //设置状态栏颜色
//            window.statusBarColor = color
//            //状态栏白底黑字的实现方法
//            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//            //去掉系统状态栏下的windowContentOverlay
//            findViewById<View>(android.R.id.content).foreground = null
//        }
        StatusBarUtil.setTransparent(this)

    }
}