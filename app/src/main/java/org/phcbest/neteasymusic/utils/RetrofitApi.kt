package org.phcbest.neteasymusic.utils

import io.reactivex.rxjava3.core.Observable
import org.phcbest.neteasymusic.bean.*
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitApi {
    companion object {
        val baseUrl = "http://10.0.2.2:3000"
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

    /**
     * 登录
     */
    @GET("/login/cellphone")
    fun login(
        @Query("phone") phone: String,
        @Query("captcha") captcha: String
    ): Observable<LoginBean>


    @GET("/captcha/sent")
    fun getCaptcha(@Query("phone") phone: String): Observable<Map<String, Any>>
}