package org.phcbest.neteasymusic.ui.fragment.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers
import org.phcbest.neteasymusic.bean.*
import org.phcbest.neteasymusic.utils.RetrofitUtils
import kotlin.random.Random

class DiscoverFragmentViewModel : ViewModel() {
    companion object {
        private const val TAG = "DiscoverFragmentViewMod"
    }

    var discoverBannerLiveData: MutableLiveData<DiscoverBannerBean?> = MutableLiveData()
    var recommendPlayListLiveData: MutableLiveData<RecommendPlayListBean?> = MutableLiveData()
    var simiSongLiveData: MutableLiveData<SimilaritySongBean?> = MutableLiveData()

    //liveData状态管理
    val dataState: MutableLiveData<Map<String, STATE>> = MutableLiveData()
    private var dataStateMap = HashMap<String, STATE>()

    private val stateBanner = "banner"
    private val stateRecommendPlayList = "RecommendPlayList"
    private val stateSimiSong = "SimiSong"

    init {
        dataStateMap[stateBanner] = STATE.FAIL
        dataStateMap[stateRecommendPlayList] = STATE.FAIL
        dataStateMap[stateSimiSong] = STATE.FAIL
    }

    fun setDiscoverBannerLiveData() {
        RetrofitUtils.newInstance().getDiscoverBanner("2")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe({
                discoverBannerLiveData.postValue(it)
                dataStateMap[stateBanner] = STATE.SUCCESS
                dataState.postValue(dataStateMap)
            }, {
                it.printStackTrace()
                discoverBannerLiveData.postValue(null)
                dataStateMap[stateBanner] = STATE.FAIL
                dataState.postValue(dataStateMap)
            })
    }

    fun setRecommendPlayListLiveData() {
        RetrofitUtils.newInstance().getRecommendPlayList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                recommendPlayListLiveData.postValue(it)
                dataStateMap[stateRecommendPlayList] = STATE.SUCCESS
                dataState.postValue(dataStateMap)
            }, {
                it.printStackTrace()
                recommendPlayListLiveData.postValue(null)
                dataStateMap[stateRecommendPlayList] = STATE.FAIL
                dataState.postValue(dataStateMap)
            })
    }

    /**
     * 通过最近听歌记录获得推荐歌曲
     */
    fun setSimiSongLiveDataByRecordRecent(recentSongLimit: Int) {
        //使用操作符实现连续请求
        RetrofitUtils.newInstance().getRecordRecentSong(recentSongLimit)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                //第一次网络请求成功
                Log.i(TAG, "setSimiSongLiveDataByRecordRecent: 获取最近听的 $recentSongLimit 首歌成功")
            }.doOnError {
                //第一次网络请求失败
                it.printStackTrace()
            }.observeOn(Schedulers.io())//切换到非ui线程执行flatMap操作
            .flatMap(//给操作符提供一个方法,其中有两个泛型参数,分别是提供的数据和二段请求的封装
                object : Function<RecordRecentSongBean, Observable<SimilaritySongBean>> {
                    override fun apply(t: RecordRecentSongBean?): Observable<SimilaritySongBean> {
                        //随机获得最几个听过的歌曲
                        val randomItem = (0 until t?.data?.list?.size!!).random()
                        val listItem = t.data.list[randomItem]
                        val id = listItem.data.id

                        return RetrofitUtils.newInstance().getSimilaritySong(id.toString())
                    }
                }
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<SimilaritySongBean> {
                //第二次网络请求
                override fun onSubscribe(d: Disposable?) {

                }

                override fun onNext(t: SimilaritySongBean?) {
                    if (t != null) {
                        simiSongLiveData.postValue(t)
                        dataStateMap[stateSimiSong] = STATE.SUCCESS
                        dataState.postValue(dataStateMap)
                    }
                }

                override fun onError(e: Throwable?) {
                    e!!.printStackTrace()
                    simiSongLiveData.postValue(null)
                    dataStateMap[stateSimiSong] = STATE.SUCCESS
                    dataState.postValue(dataStateMap)
                }

                override fun onComplete() {

                }

            })
    }


    enum class STATE {
        SUCCESS, FAIL
    }

}