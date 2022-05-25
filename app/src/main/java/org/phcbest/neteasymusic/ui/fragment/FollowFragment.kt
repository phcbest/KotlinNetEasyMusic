package org.phcbest.neteasymusic.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseFragment
import org.phcbest.neteasymusic.databinding.FragmentFollowBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FollowFragment : BaseFragment() {


    companion object {
        /**
         * 这个JvmStatic注释是为了在java中调用kotlin
         */
        @JvmStatic
        fun newInstance() = FollowFragment()
    }

    override fun initPresenter() {
    }

    override fun initView() {
    }

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        val binding = DataBindingUtil.inflate<FragmentFollowBinding>(inflater,
            R.layout.fragment_follow,
            container,
            false)
        return binding
    }
}