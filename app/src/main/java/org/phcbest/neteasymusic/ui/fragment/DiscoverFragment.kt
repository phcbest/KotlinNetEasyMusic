package org.phcbest.neteasymusic.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.viewbinding.ViewBinding
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseFragment
import org.phcbest.neteasymusic.databinding.FragmentDiscoverBinding
import org.phcbest.neteasymusic.ui.fragment.viewmodel.DiscoverFragmentViewModel
import org.phcbest.neteasymusic.ui.widget.adapter.DiscoverRecommendPlayListAdapter
import org.phcbest.neteasymusic.ui.widget.banner.CustomBanner
import org.phcbest.neteasymusic.ui.widget.homefun.CustomHomeFun

private const val TAG = "DiscoverFragment"

class DiscoverFragment : BaseFragment() {

    private var binding: FragmentDiscoverBinding? = null
    private var customBanner: CustomBanner? = null
    private lateinit var discoverFragmentViewModel: DiscoverFragmentViewModel

    private lateinit var discoverRecommendPlayListAdapter: DiscoverRecommendPlayListAdapter

    override fun initPresenter() {
        discoverFragmentViewModel.setDiscoverBannerLiveData()
        discoverFragmentViewModel.setRecommendPlayListLiveData()
    }

    override fun observeViewModel() {
        super.observeViewModel()
        discoverFragmentViewModel.discoverBannerLiveData.observe(this, {
            if (it != null) {
                customBanner =
                    CustomBanner(it).setView(binding!!.root).startShowAfterAdapter()
            }
        })
        discoverFragmentViewModel.recommendPlayListLiveData.observe(this, {
            if (it != null) {
                discoverRecommendPlayListAdapter.setItemData(it, 5)
            }
        })

        discoverFragmentViewModel.dataState.observe(this, {
            it.forEach { map ->
                if (map.value == DiscoverFragmentViewModel.STATE.FAIL) {
                    //如果有一个状态是未成功
                    binding?.isLoad = true
                    return@forEach
                }
            }
            //没有未成功状态，设置未成功
            binding?.isLoad = false
        })
    }

    override fun initView() {
        binding?.isLoad = true
        discoverRecommendPlayListAdapter = DiscoverRecommendPlayListAdapter()
        binding?.rvRecommendPlaylist?.adapter = discoverRecommendPlayListAdapter
        CustomHomeFun().setView(binding!!.root).startAdapter()
    }

    override fun onBaseDestroyView() {
        if (customBanner != null) {
            customBanner!!.countDownTimer.cancel()
        }
    }

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        discoverFragmentViewModel =
            ViewModelProviders.of(this)[DiscoverFragmentViewModel::class.java]
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_discover, container, false)
        return binding!!
    }

    //这写法类似静态方法,可以直接调用该方法中的静态成员和静态方法
    companion object {
        //如果指定的是函数,那么函数就是静态的
        @JvmStatic
        fun newInstance() = DiscoverFragment()
    }


}