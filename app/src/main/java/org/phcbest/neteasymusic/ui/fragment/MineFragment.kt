package org.phcbest.neteasymusic.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewbinding.ViewBinding
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseFragment
import org.phcbest.neteasymusic.bean.UserDetailBean
import org.phcbest.neteasymusic.bean.UserPlaylistBean
import org.phcbest.neteasymusic.databinding.FragmentMineBinding
import org.phcbest.neteasymusic.ui.activity.PlayListDetailActivity
import org.phcbest.neteasymusic.ui.fragment.viewmodel.MineFragmentViewModel
import org.phcbest.neteasymusic.ui.widget.adapter.MineFunAdapter
import org.phcbest.neteasymusic.ui.widget.adapter.MinePlayListAdapter
import org.phcbest.neteasymusic.ui.widget.adapter.adapter_data.MineFunAdapterData
import org.phcbest.neteasymusic.utils.MMKVStorageUtils

private const val TAG = "MineFragment"

class MineFragment : BaseFragment() {
    var binding: FragmentMineBinding? = null

    var minePlayListStarAdapter: MinePlayListAdapter? = null
    var minePlayListCreateAdapter: MinePlayListAdapter? = null

    var uid = MMKVStorageUtils.newInstance().getLoginBean()?.profile?.userId

    companion object {
        @JvmStatic
        fun newInstance() = MineFragment()

    }

    override fun initPresenter() {
    }

    private var onItemClick: (MineFunAdapterData.MineFunItemEnum) -> Unit =
        { minefunItemEnum ->
            Log.i(TAG, "onItemClick:${minefunItemEnum} ")
        }

    private var onScrollChange =
        object : View.OnScrollChangeListener {
            override fun onScrollChange(
                v: View?,
                scrollX: Int,
                scrollY: Int,
                oldScrollX: Int,
                oldScrollY: Int
            ) {

            }
        }
    private var onClick: (View, UserPlaylistBean.Playlist) -> Unit =
        { view: View, playlist: UserPlaylistBean.Playlist ->
            //跳转到歌单详情页面
            val intent = Intent(this.context, PlayListDetailActivity::class.java)
            intent.putExtra(PlayListDetailActivity.TAG, playlist)
            startActivity(intent)

        }

    override fun initView() {
//        binding?.lifecycleOwner = this
        binding?.rvMineFun?.layoutManager = GridLayoutManager(this.context, 4)
        binding?.rvMineFun?.adapter = MineFunAdapter(onItemClick)
        //适配两个RecyclerView
        minePlayListCreateAdapter = MinePlayListAdapter(onClick)
        minePlayListStarAdapter = MinePlayListAdapter(onClick)
        binding?.rvMineCreateList?.adapter = minePlayListCreateAdapter
        binding?.rvMineStarList?.adapter = minePlayListStarAdapter
        binding?.isLoading = true

        //todo 设置吸顶效果
        binding?.scrollMime?.setOnScrollChangeListener(onScrollChange)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //初始化viewmodel
        val viewModel = ViewModelProviders.of(
            this
//            , ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[MineFragmentViewModel::class.java]
        observeViewModel(viewModel)
//        viewModel.setUserDetail()
    }

    private fun observeViewModel(viewModel: MineFragmentViewModel) {
        //数据变化触发
        viewModel.getUserDetail().observe(this.viewLifecycleOwner,
            object : Observer<UserDetailBean> {
                override fun onChanged(t: UserDetailBean?) {
                    Log.i(TAG, "onChanged:UserDetailBean 数据变化触发")
                    //设置viewmodel数据
                    binding?.userDetailBean = t
//                    binding?.executePendingBindings()
                }
            })

        viewModel.getUserPlaylist()
            .observe(this.viewLifecycleOwner,
                object : Observer<UserPlaylistBean> {
                    override fun onChanged(t: UserPlaylistBean?) {
                        Log.i(TAG, "onChanged:UserPlaylistBean 数据变化触发")
                        binding?.playlist = t?.playlist?.get(0)
                        //设置我喜欢的点击事件
                        binding?.llMineMyLike?.setOnClickListener {
                            onClick(it, t?.playlist?.get(0)!!)
                        }

                        //过滤数据
                        val userPlaylist = t!!.playlist.toMutableList()
                        userPlaylist.let {
                            //去除默认歌单我喜欢的
                            it.removeAt(0)
                            val create = it.filter { playlist ->
                                playlist.creator.userId == uid
                            }
                            val star = it.filterNot { playlist ->
                                Log.i(TAG, "onChanged: ${playlist.subscribed}")
                                playlist.creator.userId == uid
                            }
                            binding?.createPlaylistNum = create.size
                            binding?.starPlaylistNum = star.size
                            minePlayListCreateAdapter?.setItemData(create)
                            minePlayListStarAdapter?.setItemData(star)

                            binding?.isLoading = false
                        }
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