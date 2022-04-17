package org.phcbest.neteasymusic.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewbinding.ViewBinding
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseFragment
import org.phcbest.neteasymusic.bean.UserAccountBean
import org.phcbest.neteasymusic.databinding.FragmentMineBinding
import org.phcbest.neteasymusic.ui.fragment.viewmodel.MineFragmentViewModel

private const val TAG = "MineFragment"

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //初始化viewmodel
        val viewModel = ViewModelProviders.of(
            this,
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[MineFragmentViewModel::class.java]
        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: MineFragmentViewModel) {
        viewModel.getUserAccount()?.observe(this.viewLifecycleOwner,
            object : Observer<UserAccountBean> {
                override fun onChanged(t: UserAccountBean?) {
                    binding?.tvMineUsername?.text = t?.profile?.nickname
                }
            })
    }

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        //返回viewbinding
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_mine,
            container,
            false
        )
        return binding!!
    }


    override fun initEvent() {
        super.initEvent()
    }
}