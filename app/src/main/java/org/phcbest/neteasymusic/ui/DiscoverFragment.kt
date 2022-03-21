package org.phcbest.neteasymusic.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.phcbest.neteasymusic.base.BaseFragment
import org.phcbest.neteasymusic.databinding.FragmentDiscoverBinding
import org.phcbest.neteasymusic.ui.widget.banner.BannerItemBean
import org.phcbest.neteasymusic.ui.widget.banner.CustomBanner
import org.phcbest.neteasymusic.utils.RetrofitUtils

private const val TAG = "DiscoverFragment"

class DiscoverFragment : BaseFragment() {

    private var _binding: FragmentDiscoverBinding? = null
    var customBanner: CustomBanner? = null


    private fun doAdapter() {
        val newInstance = RetrofitUtils.newInstance()
        newInstance.getDiscoverBanner("2")
            .observeOn(AndroidSchedulers.mainThread())//观察者运行在AndroidUI主线程上
            .subscribeOn(Schedulers.io())//订阅者运行在普通调度器上
            .subscribe({ result ->
                //开始适配
                val bannerData: MutableList<BannerItemBean> = mutableListOf()
                result.banners.forEach { info ->
                    bannerData.add(BannerItemBean(info.pic, object : BannerItemBean.OnTap {
                        override fun OnClick() {
                            // TODO: 2022/3/11  点击banner的事件
                            Log.i(TAG, "OnClick: 点击banner")
                        }
                    }))
                }

                customBanner =
                    CustomBanner(bannerData).setView(_binding!!.root).startShowAfterAdapter()
            }, { error ->
                Log.e(TAG, "doAdapter: 网络请求错误" + error.printStackTrace())
            })
    }

    override fun initPresenter() {
    }

    override fun initView() {
        //进行ui适配
        doAdapter()
    }

    override fun onBaseDestroyView() {
        if (customBanner != null) {
            customBanner!!.countDownTimer.cancel()
        }
    }

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        return _binding!!
    }

    //这写法类似静态方法,可以直接调用该方法中的静态成员和静态方法
    companion object {
        //如果指定的是函数,那么函数就是静态的
        @JvmStatic
        fun newInstance() = DiscoverFragment()
    }


}