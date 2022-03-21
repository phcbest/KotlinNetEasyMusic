package org.phcbest.neteasymusic.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.phcbest.neteasymusic.databinding.FragmentDiscoverBinding
import org.phcbest.neteasymusic.ui.widget.banner.BannerItemBean
import org.phcbest.neteasymusic.ui.widget.banner.CustomBanner
import org.phcbest.neteasymusic.utils.RetrofitUtils
import java.net.Socket
import kotlin.math.log

private const val TAG = "DiscoverFragment"

class DiscoverFragment : Fragment() {

    private var _binding: FragmentDiscoverBinding? = null
    var customBanner: CustomBanner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        //进行ui适配
        doAdapter()

        return _binding!!.root
    }

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
                Log.e(TAG, "onCreateView: 网络请求错误" + error.message)
            })
    }

    //摧毁fragment的生命周期
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        //销毁定时器
        if (customBanner != null) {
            customBanner!!.countDownTimer.cancel()
        }
    }

    //这写法类似静态方法,可以直接调用该方法中的静态成员和静态方法
    companion object {
        //如果指定的是函数,那么函数就是静态的
        @JvmStatic
        fun newInstance() = DiscoverFragment()
    }


}