package org.phcbest.neteasymusic.utils

import android.os.Build
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import org.phcbest.neteasymusic.bean.PlayListDetailBean
import org.phcbest.neteasymusic.bean.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.sql.Date
import java.sql.Time
import java.time.LocalDateTime
import java.util.*


interface RetrofitApi {
    companion object {
        private const val TAG = "RetrofitApi"

        var baseUrl = if (Build.HARDWARE == "ranchu") {
            "http://10.0.2.2:3000"
        } else {
            "http://192.168.1.100:3000"
        }
    }


    /**
     * 获取banner
     */
    @GET("/banner")
    fun getDiscoverBanner(@Query("type") type: String): Observable<DiscoverBannerBean>


    /**
     * 获得推荐歌单
     */
    @GET("/personalized")
    fun getPersonalizedPlayList(@Query("limit") limit: String): Observable<PersonalizedPlayListBean>

    /**
     * 获得每日推荐歌单
     */
    @GET("/recommend/resource")
    fun getRecommendPlayList(): Observable<RecommendPlayListBean>


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
        @Query("captcha") captcha: String,
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
    fun refreshLogin(): Observable<Response<Map<String, Int>>>


    /**
     * 获取用户详情
     */
    @GET("/user/detail")
    fun getUserDetail(@Query("uid") uid: String): Observable<UserDetailBean>

    /**
     * 获得黑胶会员等级
     */
    @GET("/vip/growthpoint")
    fun getVipGrowthpoint(): Observable<VipGrowthpointBean>

    /**
     * 获得用户所有的歌单
     */
    @GET("/user/playlist")
    fun getUserPlaylist(@Query("uid") uid: String): Observable<UserPlaylistBean>


    /**
     * 获得歌单详情
     */
    @GET("/playlist/detail")
    fun getPlayListDetail(@Query("id") id: String): Observable<PlayListDetailBean>


    /**
     * 获取相似音乐
     */
    @GET("/simi/song")
    fun getSimilaritySong(@Query("id") id: String): Observable<SimilaritySongBean>

    /**
     * 获得最近播放的音乐
     */
    @GET("/record/recent/song")
    fun getRecordRecentSong(@Query("limit") limit: Int): Observable<RecordRecentSongBean>


    /**
     * 获得用户关注
     */
    @GET("/user/follows")
    fun getUserFollows(
        @Query("uid") uid: Long,
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0,
    ): Observable<UserFollowBean>

    /**
     * 获得热门话题
     */
    @GET("/hot/topic")
    fun getHotTopic(
        @Query("limit") limit: Int = 15,
        @Query("offset") offset: Int = 0,
    ): Observable<HotTopicBean>


    /**
     * 获得用户动态
     */
    @GET("/user/event")
    fun getUserEvent(
        @Query("uid") uid: Long,
        @Query("limit") limit: Int = 20,
        @Query("lasttime") lastTime: Long = Calendar.getInstance().timeInMillis,
    ): Observable<UserEventBean>


}