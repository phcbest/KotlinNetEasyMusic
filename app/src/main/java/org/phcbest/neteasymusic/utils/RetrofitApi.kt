package org.phcbest.neteasymusic.utils

import io.reactivex.rxjava3.core.Observable
import org.phcbest.neteasymusic.bean.*
import retrofit2.Response
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
    ): Observable<Response<LoginBean>>


    /**
     * 发送验证码
     */
    @GET("/captcha/sent")
    fun getCaptcha(@Query("phone") phone: String): Observable<Map<String, Any>>


    /**
     * 获取用户信息,需要携带token
     */
    @GET("/user/account")
    fun getUserAccount(): Observable<UserAccountBean>


    /**
     * 刷新登录
     */
    @GET("/login/refresh")
    fun refreshLogin(): Observable<Map<String, Any>>


    /**
     * 获取用户详情
     */
    @GET("/user/detail")
    fun getUserDetail(@Query("uid") uid: String): Observable<UserDetailBean>

    /**
     * 获得用户歌单
     */
    @GET("user/playlist")
    fun getUserPlaylist(@Query("uid") uid: String): Observable<UserPlaylistBean>
}