package org.phcbest.neteasymusic.bean

import com.google.gson.annotations.SerializedName


data class LoginBean(
    @SerializedName("account")
    val account: Account,
    @SerializedName("bindings")
    val bindings: List<Binding>,
    @SerializedName("code")
    val code: Int, // 200
    @SerializedName("cookie")
    val cookie: String, // MUSIC_U=84b3c07ed49bbd047151d48cbfcb2540922f4f689b39dcd5726f2dcd996546ef8a08bd5bf851808fd78b6050a17a35e705925a4e6992f61d07c385928f88e8de; Max-Age=1296000; Expires=Thu, 21 Apr 2022 11:25:34 GMT; Path=/;;MUSIC_R_T=1467374751660; Max-Age=2147483647; Expires=Mon, 24 Apr 2090 14:39:41 GMT; Path=/weapi/feedback;;MUSIC_A_T=1467374746375; Max-Age=2147483647; Expires=Mon, 24 Apr 2090 14:39:41 GMT; Path=/eapi/feedback;;MUSIC_R_T=1467374751660; Max-Age=2147483647; Expires=Mon, 24 Apr 2090 14:39:41 GMT; Path=/neapi/feedback;;MUSIC_A_T=1467374746375; Max-Age=2147483647; Expires=Mon, 24 Apr 2090 14:39:41 GMT; Path=/neapi/feedback;;MUSIC_A_T=1467374746375; Max-Age=2147483647; Expires=Mon, 24 Apr 2090 14:39:41 GMT; Path=/weapi/clientlog;;MUSIC_R_T=1467374751660; Max-Age=2147483647; Expires=Mon, 24 Apr 2090 14:39:41 GMT; Path=/wapi/clientlog;;__remember_me=true; Max-Age=1296000; Expires=Thu, 21 Apr 2022 11:25:34 GMT; Path=/;;MUSIC_A_T=1467374746375; Max-Age=2147483647; Expires=Mon, 24 Apr 2090 14:39:41 GMT; Path=/wapi/feedback;;__csrf=277aa184808067c0f952c56a64afae45; Max-Age=1296010; Expires=Thu, 21 Apr 2022 11:25:44 GMT; Path=/;;MUSIC_R_T=1467374751660; Max-Age=2147483647; Expires=Mon, 24 Apr 2090 14:39:41 GMT; Path=/eapi/feedback;;MUSIC_A_T=1467374746375; Max-Age=2147483647; Expires=Mon, 24 Apr 2090 14:39:41 GMT; Path=/api/feedback;;MUSIC_A_T=1467374746375; Max-Age=2147483647; Expires=Mon, 24 Apr 2090 14:39:41 GMT; Path=/neapi/clientlog;;MUSIC_A_T=1467374746375; Max-Age=2147483647; Expires=Mon, 24 Apr 2090 14:39:41 GMT; Path=/openapi/clientlog;;MUSIC_R_T=1467374751660; Max-Age=2147483647; Expires=Mon, 24 Apr 2090 14:39:41 GMT; Path=/api/clientlog;;MUSIC_A_T=1467374746375; Max-Age=2147483647; Expires=Mon, 24 Apr 2090 14:39:41 GMT; Path=/eapi/clientlog;;MUSIC_R_T=1467374751660; Max-Age=2147483647; Expires=Mon, 24 Apr 2090 14:39:41 GMT; Path=/api/feedback;;MUSIC_SNS=; Max-Age=0; Expires=Wed, 6 Apr 2022 11:25:34 GMT; Path=/;MUSIC_R_T=1467374751660; Max-Age=2147483647; Expires=Mon, 24 Apr 2090 14:39:41 GMT; Path=/wapi/feedback;;MUSIC_A_T=1467374746375; Max-Age=2147483647; Expires=Mon, 24 Apr 2090 14:39:41 GMT; Path=/wapi/clientlog;;MUSIC_R_T=1467374751660; Max-Age=2147483647; Expires=Mon, 24 Apr 2090 14:39:41 GMT; Path=/openapi/clientlog;;MUSIC_R_T=1467374751660; Max-Age=2147483647; Expires=Mon, 24 Apr 2090 14:39:41 GMT; Path=/eapi/clientlog;;MUSIC_R_T=1467374751660; Max-Age=2147483647; Expires=Mon, 24 Apr 2090 14:39:41 GMT; Path=/neapi/clientlog;;MUSIC_R_T=1467374751660; Max-Age=2147483647; Expires=Mon, 24 Apr 2090 14:39:41 GMT; Path=/weapi/clientlog;;MUSIC_A_T=1467374746375; Max-Age=2147483647; Expires=Mon, 24 Apr 2090 14:39:41 GMT; Path=/weapi/feedback;;MUSIC_A_T=1467374746375; Max-Age=2147483647; Expires=Mon, 24 Apr 2090 14:39:41 GMT; Path=/api/clientlog;
    @SerializedName("loginType")
    val loginType: Int, // 1
    @SerializedName("profile")
    val profile: Profile,
    @SerializedName("token")
    val token: String // 84b3c07ed49bbd047151d48cbfcb2540922f4f689b39dcd5726f2dcd996546ef8a08bd5bf851808fd78b6050a17a35e705925a4e6992f61d07c385928f88e8de
) {
    data class Account(
        @SerializedName("anonimousUser")
        val anonimousUser: Boolean, // false
        @SerializedName("ban")
        val ban: Int, // 0
        @SerializedName("baoyueVersion")
        val baoyueVersion: Int, // 1
        @SerializedName("createTime")
        val createTime: Long, // 1467374746375
        @SerializedName("donateVersion")
        val donateVersion: Int, // 0
        @SerializedName("id")
        val id: Int, // 298385261
        @SerializedName("salt")
        val salt: String, // [B@615951b3
        @SerializedName("status")
        val status: Int, // 0
        @SerializedName("tokenVersion")
        val tokenVersion: Int, // 3
        @SerializedName("type")
        val type: Int, // 1
        @SerializedName("uninitialized")
        val uninitialized: Boolean, // false
        @SerializedName("userName")
        val userName: String, // 1_17770708139
        @SerializedName("vipType")
        val vipType: Int, // 11
        @SerializedName("viptypeVersion")
        val viptypeVersion: Long, // 1639784645949
        @SerializedName("whitelistAuthority")
        val whitelistAuthority: Int // 0
    )

    data class Binding(
        @SerializedName("bindingTime")
        val bindingTime: Long, // 1506785390942
        @SerializedName("expired")
        val expired: Boolean, // false
        @SerializedName("expiresIn")
        val expiresIn: Int, // 2147483647
        @SerializedName("id")
        val id: Long, // 3220735947
        @SerializedName("refreshTime")
        val refreshTime: Int, // 1506785390
        @SerializedName("tokenJsonStr")
        val tokenJsonStr: String, // {"countrycode":"","cellphone":"17770708139","hasPassword":true}
        @SerializedName("type")
        val type: Int, // 1
        @SerializedName("url")
        val url: String,
        @SerializedName("userId")
        val userId: Int // 298385261
    )

    data class Profile(
        @SerializedName("accountStatus")
        val accountStatus: Int, // 0
        @SerializedName("authStatus")
        val authStatus: Int, // 0
        @SerializedName("authority")
        val authority: Int, // 0
        @SerializedName("avatarDetail")
        val avatarDetail: Any, // null
        @SerializedName("avatarImgId")
        val avatarImgId: Long, // 109951166781557920
        @SerializedName("avatarImgIdStr")
        val avatarImgIdStr: String, // 109951166781557921
        @SerializedName("avatarImgId_str")
        val avatarImgId_str: String, // 109951166781557921
        @SerializedName("avatarUrl")
        val avatarUrl: String, // https://p4.music.126.net/Gnr50uIm1-T-65yR4LYgzQ==/109951166781557921.jpg
        @SerializedName("backgroundImgId")
        val backgroundImgId: Long, // 109951163267220300
        @SerializedName("backgroundImgIdStr")
        val backgroundImgIdStr: String, // 109951163267220302
        @SerializedName("backgroundUrl")
        val backgroundUrl: String, // https://p3.music.126.net/j0Q2uKCvUu3sbJoWqyKXrQ==/109951163267220302.jpg
        @SerializedName("birthday")
        val birthday: Long, // 631123200000
        @SerializedName("city")
        val city: Int, // 110101
        @SerializedName("defaultAvatar")
        val defaultAvatar: Boolean, // false
        @SerializedName("description")
        val description: String,
        @SerializedName("detailDescription")
        val detailDescription: String,
        @SerializedName("djStatus")
        val djStatus: Int, // 0
        @SerializedName("eventCount")
        val eventCount: Int, // 0
        @SerializedName("expertTags")
        val expertTags: Any, // null
        @SerializedName("experts")
        val experts: Experts,
        @SerializedName("followed")
        val followed: Boolean, // false
        @SerializedName("followeds")
        val followeds: Int, // 12
        @SerializedName("follows")
        val follows: Int, // 71
        @SerializedName("gender")
        val gender: Int, // 1
        @SerializedName("mutual")
        val mutual: Boolean, // false
        @SerializedName("nickname")
        val nickname: String, // 听歌就是为了开心
        @SerializedName("playlistBeSubscribedCount")
        val playlistBeSubscribedCount: Int, // 1
        @SerializedName("playlistCount")
        val playlistCount: Int, // 8
        @SerializedName("province")
        val province: Int, // 110000
        @SerializedName("remarkName")
        val remarkName: Any, // null
        @SerializedName("signature")
        val signature: String,
        @SerializedName("userId")
        val userId: Int, // 298385261
        @SerializedName("userType")
        val userType: Int, // 0
        @SerializedName("vipType")
        val vipType: Int // 11
    ) {
        class Experts
    }
}