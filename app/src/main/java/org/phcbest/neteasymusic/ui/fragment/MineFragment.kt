package org.phcbest.neteasymusic.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewbinding.ViewBinding
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseFragment
import org.phcbest.neteasymusic.bean.UserDetailBean
import org.phcbest.neteasymusic.databinding.FragmentMineBinding
import org.phcbest.neteasymusic.ui.fragment.viewmodel.MineFragmentViewModel
import org.phcbest.neteasymusic.ui.widget.adapter.MineFunAdapter

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
        binding?.rvMineFun?.layoutManager = GridLayoutManager(this.context, 4)
        binding?.rvMineFun?.adapter = MineFunAdapter()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //初始化viewmodel
        val viewModel = ViewModelProviders.of(
            this
//            , ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[MineFragmentViewModel::class.java]

        observeViewModel(viewModel)
        viewModel.setUserDetail()
    }

    private fun observeViewModel(viewModel: MineFragmentViewModel) {
        //数据变化触发
        viewModel.getUserDetail().observe(this.viewLifecycleOwner,
            object : Observer<UserDetailBean> {
                override fun onChanged(t: UserDetailBean?) {
                    Log.i(TAG, "onChanged: 数据变化触发")
                    //设置viewmodel数据
                    binding?.userDetailBean = t
//                    binding?.executePendingBindings()
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