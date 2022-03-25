package org.phcbest.neteasymusic.utils

import io.reactivex.rxjava3.core.Observable
import org.phcbest.neteasymusic.bean.DiscoverBannerBean
import org.phcbest.neteasymusic.bean.SongListBean
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitApi {
    companion object {
        val baseUrl = "http://192.168.123.166:3000"
    }

    @GET("/banner")
    fun getDiscoverBanner(@Query("type") type: String): Observable<DiscoverBannerBean>

    @GET("/search")
    fun getSongByKeyWords(@Query("keywords") keywords: String): Observable<SongListBean>
}