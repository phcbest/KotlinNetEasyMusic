package org.phcbest.neteasymusic.ui.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseFragment
import org.phcbest.neteasymusic.base.PAGE_STATE
import org.phcbest.neteasymusic.databinding.FragmentFollowBinding
import org.phcbest.neteasymusic.ui.fragment.viewmodel.FollowFragmentViewModel
import org.phcbest.neteasymusic.ui.widget.adapter.FollowViewListAdapter
import org.phcbest.neteasymusic.ui.widget.adapter.HotTopicAdapter


class FollowFragment : BaseFragment() {

    var binding: FragmentFollowBinding? = null
    private var mFollowFragmentViewModel: FollowFragmentViewModel? = null

    companion object {
        private const val TAG = "FollowFragment"

        /**
         * 这个JvmStatic注释是为了在java中调用kotlin
         */
        @JvmStatic
        fun newInstance() = FollowFragment()
    }

    override fun initPresenter() {
        //获得用户关注列表
        mFollowFragmentViewModel?.getUserFollowBeanLD(10, 0)
        mFollowFragmentViewModel?.getHotTopicBeanLD()
    }

    private var mFollowViewListAdapter: FollowViewListAdapter? = null
    private var mHotTopicAdapter: HotTopicAdapter? = null
    override fun initView() {
        binding?.isLoad = true
        //初始化view
        mFollowViewListAdapter = FollowViewListAdapter(binding?.rvFollowList?.layoutManager!!)
        binding?.rvFollowList?.adapter = mFollowViewListAdapter

        mHotTopicAdapter = HotTopicAdapter()
        val gridLayoutManager = GridLayoutManager(context, 5, GridLayoutManager.HORIZONTAL, false)
        binding?.rvHotTopic?.layoutManager =
            gridLayoutManager
        binding?.rvHotTopic?.adapter = mHotTopicAdapter

        binding?.rvFollowDynamic?.adapter
    }

    override fun initEvent() {
        super.initEvent()
        binding?.rvFollowList?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
//                Log.i(TAG, "onScrolled: dx$dx dy$dy ")
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val pos = layoutManager.findLastCompletelyVisibleItemPosition()
                val numItems = recyclerView.adapter?.itemCount
                if (pos >= numItems!! - 1) {
                    Log.i(TAG, "onScrolled: size$numItems 最后一个渲染出来了")
                    //进行后续的请求
                    mFollowFragmentViewModel?.getUserFollowBeanLD(10, numItems)
                }
            }
        })
    }

    override fun observeViewModel() {
        super.observeViewModel()
        mFollowFragmentViewModel?.userFollowBeanLD?.observe(this) {
            mFollowViewListAdapter?.addUserFollowBean(it!!)
        }
        mFollowFragmentViewModel?.hotTopicBeanLD?.observe(this) {
            mHotTopicAdapter?.setHotTopicBean(it)
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

    override fun setViewBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        mFollowFragmentViewModel = ViewModelProviders.of(this)[FollowFragmentViewModel::class.java]
        binding = DataBindingUtil.inflate<FragmentFollowBinding>(inflater,
            R.layout.fragment_follow,
            container,
            false)
        return binding!!
    }
}