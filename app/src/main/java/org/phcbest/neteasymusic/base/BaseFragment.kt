package org.phcbest.neteasymusic.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.viewbinding.ViewBinding
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.databinding.BaseFragmentBinding

abstract class BaseFragment : Fragment() {

    /**
     * 页面状态
     */
    public enum class State {
        NONE, LOADING, SUCCESS, ERROR, EMPTY
    }

    private var vb: BaseFragmentBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        //创建根fragment
        vb = DataBindingUtil.inflate<BaseFragmentBinding>(inflater,
            R.layout.base_fragment,
            container,
            false)
        val upperView = setViewBinding(inflater, container)
        vb?.baseFragment?.addView(upperView.root)
        //设置页面状态
//        initViewDataState()
        initView()
        initEvent()
        initPresenter()
        observeViewModel()
        return vb!!.root
    }

//    private val dataState: MutableLiveData<Map<String, PAGE_STATE>> = MutableLiveData()
//    private fun initViewDataState() {
//        val viewDataState = setViewDataState()
//        val viewDataStateMap: MutableMap<String, PAGE_STATE> = mutableMapOf()
//        for (s in viewDataState) {
//            viewDataStateMap[s] = PAGE_STATE.FAIL
//        }
//        dataState.postValue(viewDataStateMap)
//        //设置live响应
//        dataState.observe(this.viewLifecycleOwner) {
//            it.forEach { map ->
//                if (map.value == PAGE_STATE.FAIL) {
//                    //如果有一个状态是未成功
//                    vb?.isLoad = true
//                    return@forEach
//                }
//            }
//            //设置为成功
//            vb?.isLoad = false
//        }
//    }

//    abstract fun setViewDataState(): List<String>

    open fun observeViewModel() {

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

    abstract fun setViewBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding


}