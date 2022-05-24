package org.phcbest.neteasymusic.ui.widget.banner

import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.bean.Banner
import org.phcbest.neteasymusic.bean.DiscoverBannerBean

private const val TAG = "CustomBanner"

//var bannerItemInfo: DiscoverBannerBean?
class CustomBanner() {


    var vpBanner: ViewPager2? = null
    var rvBannerIndex: RecyclerView? = null
    var bannerAdapter: BannerAdapter? = null

    fun setView(view: View): CustomBanner {
        vpBanner = view.findViewById(R.id.vp_banner)
        rvBannerIndex = view.findViewById(R.id.rv_banner_index)
        //初始化recycler排序顺序
        rvBannerIndex!!.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        //初始化适配器
        bannerAdapter = BannerAdapter()
        vpBanner!!.adapter = bannerAdapter
        return this
    }

    //计算下标数，生成下标对应数组
    private fun mutableList(
        position: Int,
        bannerItemInfo: DiscoverBannerBean,
    ): MutableList<Boolean> {
        val indexState = mutableListOf<Boolean>()
        for (index in bannerItemInfo.banners.indices) {
            if (index == position) indexState.add(true) else indexState.add(false)
        }
        return indexState
    }

    fun startShowAfterAdapter(bannerItemInfo: DiscoverBannerBean) {
        //设置banner数据
        bannerAdapter?.setBannerItem(bannerItemInfo)
        //适配轮播图和轮播下标
        rvBannerIndex!!.adapter = BannerIndexAdapter(mutableList(0, bannerItemInfo))
        //设置滑动切换下标
        vpBanner!!.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                rvBannerIndex!!.adapter =
                    BannerIndexAdapter(mutableList(position % bannerItemInfo.banners.size,
                        bannerItemInfo))
            }
        })
        //设置初始页
        vpBanner!!.setCurrentItem(
            Int.MAX_VALUE / 2 - (Int.MAX_VALUE / 2 % bannerItemInfo.banners.size),
            false
        )
        //启动定时器
        countDownTimer.start()

    }


    /**
     *    设置定时器切换页面
     */
    val countDownTimer = object : CountDownTimer(Long.MAX_VALUE, 5000) {
        override fun onTick(millisUntilFinished: Long) {
            vpBanner!!.currentItem++
        }

        override fun onFinish() {
            Log.i(TAG, "onFinish: 定时器结束")
        }
    }

    /**
     * 这个是下标的适配器
     */
    class BannerIndexAdapter(indexState: List<Boolean>) :
        RecyclerView.Adapter<BannerIndexAdapter.ViewHolder>() {
        private var _indexState = indexState

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val indexView = ImageView(parent.context)
            return ViewHolder(indexView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.indexImage.setImageDrawable(
                holder.itemView.context.getDrawable(
                    if (_indexState[position]) R.drawable.shape_banner_index_select else R.drawable.shape_banner_index
                )
            )
            holder.indexImage.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            holder.indexImage.setPadding(5, 0, 5, 0)
        }

        override fun getItemCount(): Int {
            return _indexState.size
        }

        class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
            val indexImage = item as ImageView
        }
    }

    /**
     * 这个是viewpage2的适配器,内部类需要添加inner关键字
     */
    inner class BannerAdapter() :
        RecyclerView.Adapter<BannerAdapter.ViewHolder>() {

        var bannerItemInfo: List<Banner>? = null;

        fun setBannerItem(bannerItemInfo: DiscoverBannerBean) {
            this.bannerItemInfo = bannerItemInfo.banners
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_banner_item, parent,
                false
            )
            return ViewHolder(view)
        }

        private var click: ((banner: Banner, index: Int) -> Unit)? = null
        fun setOnClick(click: (banner: Banner, index: Int) -> Unit) {
            this.click = click
        }

        @SuppressLint("ClickableViewAccessibility")
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val p = position % bannerItemInfo!!.size
            val itemData = bannerItemInfo!![p]
            Glide.with(holder.itemView).load(itemData.pic).centerCrop()
                .into(holder.bannerImageView)
            holder.bannerImageView.setOnClickListener { v ->
                this.click?.let { it(itemData, p) }
            }
            //用户触摸时暂停定时器,因为viewpage底层将相关事件拦截了，不能用viewpage的setOnTouchListener
            holder.bannerImageView.setOnTouchListener { _, event ->
                Log.i(TAG, "onTouch: ${event!!.action}")
                if (event.action == MotionEvent.ACTION_DOWN) {
                    countDownTimer.cancel()
                } else if (event.action == MotionEvent.ACTION_CANCEL) {
                    countDownTimer.start()
                }
                //我们希望进行onClick的事件，所以返回false，没消费这个事件
                false
            }

        }

        override fun getItemCount(): Int {
            return Int.MAX_VALUE
        }

        inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
            var bannerImageView: ImageView = item.findViewById(R.id.img_banner_item)
        }


    }

}