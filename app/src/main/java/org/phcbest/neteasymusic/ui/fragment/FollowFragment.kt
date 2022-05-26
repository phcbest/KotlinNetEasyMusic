package org.phcbest.neteasymusic.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseFragment
import org.phcbest.neteasymusic.base.PAGE_STATE
import org.phcbest.neteasymusic.databinding.FragmentFollowBinding
import org.phcbest.neteasymusic.ui.fragment.viewmodel.FollowFragmentViewModel
import org.phcbest.neteasymusic.ui.widget.adapter.FollowViewListAdapter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FollowFragment : BaseFragment() {

    var binding: FragmentFollowBinding? = null
    private var mFollowFragmentViewModel: FollowFragmentViewModel? = null

    companion object {
        /**
         * 这个JvmStatic注释是为了在java中调用kotlin
         */
        @JvmStatic
        fun newInstance() = FollowFragment()
    }

    override fun initPresenter() {
        //获得用户关注列表
        mFollowFragmentViewModel?.getUserFollowBeanLD()
    }

    private var mFollowViewListAdapter: FollowViewListAdapter? = null
    override fun initView() {
        binding?.isLoad = true
        //初始化view
        mFollowViewListAdapter = FollowViewListAdapter()
        binding?.rvFollowList?.adapter = mFollowViewListAdapter
        binding?.rvHotTopic?.adapter
        binding?.rvFollowDynamic?.adapter
    }

    override fun initEvent() {
        super.initEvent()
//        //设置滑动时不加载图片
//        binding?.rvFollowList?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    activity?.let { Glide.with(it).resumeRequests() };//恢复Glide加载图片
//                } else {
//                    activity?.let { Glide.with(it).pauseRequests() };//禁止Glide加载图片
//                }
//            }
//        })
    }

    override fun observeViewModel() {
        super.observeViewModel()
        mFollowFragmentViewModel?.userFollowBeanLD?.observe(this) {
            mFollowViewListAdapter?.setUserFollowBean(it!!)
        }
        mFollowFragmentViewModel?.dataState?.observe(this) {
            it.forEach { map ->
                if (map.value == PAGE_STATE.FAIL) {
                    //如果有一个状态是未成功
                    binding?.isLoad = true
                    return@forEach
                }
            }
            //没有未成功状态，设置未成功
            binding?.isLoad = false
        }
    }

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        mFollowFragmentViewModel = ViewModelProviders.of(this)[FollowFragmentViewModel::class.java]
        binding = DataBindingUtil.inflate<FragmentFollowBinding>(inflater,
            R.layout.fragment_follow,
            container,
            false)
        return binding!!
    }
}