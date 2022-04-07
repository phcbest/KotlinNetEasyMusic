package org.phcbest.neteasymusic.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import org.phcbest.neteasymusic.base.BaseFragment
import org.phcbest.neteasymusic.databinding.FragmentMineBinding

class MineFragment : BaseFragment() {
    var binding: FragmentMineBinding? = null


    companion object {
        @JvmStatic
        fun newInstance() = MineFragment()

    }

    override fun initPresenter() {
    }

    override fun initView() {
    }

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        binding = FragmentMineBinding.inflate(inflater, container, false)
        return binding!!
    }

    override fun initEvent() {
        super.initEvent()
    }
}