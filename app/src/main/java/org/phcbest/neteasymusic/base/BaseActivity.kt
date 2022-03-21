package org.phcbest.neteasymusic.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity : AppCompatActivity() {

    private var vb: ViewBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //生成viewbinding
        vb = getViewBinding()
        //绑定view
        setContentView(vb!!.root)
        initView()
        initEvent()
        initPresenter()
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

}