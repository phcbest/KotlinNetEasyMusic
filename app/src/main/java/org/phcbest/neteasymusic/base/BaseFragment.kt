package org.phcbest.neteasymusic.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment : Fragment() {

    /**
     * 页面状态
     */
    public enum class State {
        NONE, LOADING, SUCCESS, ERROR, EMPTY
    }

    var vb: ViewBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = getViewBinding(inflater, container)
        initView()
        initEvent()
        initPresenter()
        return vb!!.root
    }

    abstract fun initPresenter()

    open fun initEvent() {

    }

    abstract fun initView()

    override fun onDestroyView() {
        super.onDestroyView()
        if (vb != null) {
            vb = null
        }
        onBaseDestroyView()
    }

    open fun onBaseDestroyView() {

    }

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding


}