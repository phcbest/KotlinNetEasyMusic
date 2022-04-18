package org.phcbest.neteasymusic.bean


import com.google.gson.annotations.SerializedName

data class UserDetailBean(
    @SerializedName("adValid")
    val adValid: Boolean, // false
    @SerializedName("bindings")
    val bindings: List<Binding>,
    @SerializedName("code")
    val code: Int, // 200
    @SerializedName("createDays")
    val createDays: Int, // 2117
    @SerializedName("createTime")
    val createTime: Long, // 1467374751660
    @SerializedName("level")
    val level: Int, // 8
    @SerializedName("listenSongs")
    val listenSongs: Int, // 3902
    @SerializedName("mobileSign")
    val mobileSign: Boolean, // false
    @SerializedName("pcSign")
    val pcSign: Boolean, // false
    @SerializedName("peopleCanSeeMyPlayRecord")
    val peopleCanSeeMyPlayRecord: Boolean, // true
    @SerializedName("profile")
    val profile: Profile,
    @SerializedName("profileVillageInfo")
    val profileVillageInfo: ProfileVillageInfo,
    @SerializedName("userPoint")
    val userPoint: UserPoint
) {
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
        val tokenJsonStr: Any, // null
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
        @SerializedName("allSubscribedCount")
        val allSubscribedCount: Int, // 0
        @SerializedName("artistIdentity")
        val artistIdentity: List<Any>,
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
        val avatarImgId_Str: String, // 109951166781557921
        @SerializedName("avatarUrl")
        val avatarUrl: String, // http://p1.music.126.net/Gnr50uIm1-T-65yR4LYgzQ==/109951166781557921.jpg
        @SerializedName("backgroundImgId")
        val backgroundImgId: Long, // 109951163267220300
        @SerializedName("backgroundImgIdStr")
        val backgroundImgIdStr: String, // 109951163267220302
        @SerializedName("backgroundUrl")
        val backgroundUrl: String, // http://p1.music.126.net/j0Q2uKCvUu3sbJoWqyKXrQ==/109951163267220302.jpg
        @SerializedName("birthday")
        val birthday: Long, // 631123200000
        @SerializedName("blacklist")
        val blacklist: Boolean, // false
        @SerializedName("cCount")
        val cCount: Int, // 0
        @SerializedName("city")
        val city: Int, // 110101
        @SerializedName("createTime")
        val createTime: Long, // 1467374751660
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
        @SerializedName("followMe")
        val followMe: Boolean, // false
        @SerializedName("followTime")
        val followTime: Any, // null
        @SerializedName("followed")
        val followed: Boolean, // false
        @SerializedName("followeds")
        val followeds: Int, // 12
        @SerializedName("follows")
        val follows: Int, // 71
        @SerializedName("gender")
        val gender: Int, // 1
        @SerializedName("inBlacklist")
        val inBlacklist: Boolean, // false
        @SerializedName("mutual")
        val mutual: Boolean, // false
        @SerializedName("newFollows")
        val newFollows: Int, // 71
        @SerializedName("nickname")
        val nickname: String, // 听歌就是为了开心
        @SerializedName("playlistBeSubscribedCount")
        val playlistBeSubscribedCount: Int, // 1
        @SerializedName("playlistCount")
        val playlistCount: Int, // 8
        @SerializedName("privacyItemUnlimit")
        val privacyItemUnlimit: PrivacyItemUnlimit,
        @SerializedName("province")
        val province: Int, // 110000
        @SerializedName("remarkName")
        val remarkName: Any, // null
        @SerializedName("sCount")
        val sCount: Int, // 0
        @SerializedName("sDJPCount")
        val sDJPCount: Int, // 0
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

        data class PrivacyItemUnlimit(
            @SerializedName("age")
            val age: Boolean, // true
            @SerializedName("area")
            val area: Boolean, // true
            @SerializedName("college")
            val college: Boolean, // true
            @SerializedName("villageAge")
            val villageAge: Boolean // true
        )
    }

    data class ProfileVillageInfo(
        @SerializedName("imageUrl")
        val imageUrl: Any, // null
        @SerializedName("targetUrl")
        val targetUrl: String, // https://sg.music.163.com/g/cloud-card?full_screen=true
        @SerializedName("title")
        val title: String // 领取村民证
    )

    data class UserPoint(
        @SerializedName("balance")
        val balance: Int, // 6
        @SerializedName("blockBalance")
        val blockBalance: Int, // 0
        @SerializedName("status")
        val status: Int, // 0
        @SerializedName("updateTime")
        val updateTime: Long, // 1650248849192
        @SerializedName("userId")
        val userId: Int, // 298385261
        @SerializedName("version")
        val version: Int // 10
    )
}