package org.phcbest.neteasymusic.utils

import io.reactivex.rxjava3.core.Observable
import org.phcbest.neteasymusic.bean.DiscoverBannerBean
import org.phcbest.neteasymusic.bean.SongDetailBean
import org.phcbest.neteasymusic.bean.SongListBean
import org.phcbest.neteasymusic.bean.SongUrlBean
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitApi {
    companion object {
        val baseUrl = "http://192.168.123.166:3000"
    }

    /**
     * 获取banner
     */
    @GET("/banner")
    fun getDiscoverBanner(@Query("type") type: String): Observable<DiscoverBannerBean>

    /**
     * 搜索歌曲
     */
    @GET("/search")
    fun getSongByKeyWords(@Query("keywords") keywords: String): Observable<SongListBean>

    /**
     * 根据歌曲id获得下载url
     */
    @GET("/song/url")
    fun getDownloadUrlById(@Query("id") id: String): Observable<SongUrlBean>

    /**
     * 歌曲详情
     */
    @GET("/song/detail")
    fun getSongDetailByIds(@Query("ids") ids: String): Observable<SongDetailBean>


}