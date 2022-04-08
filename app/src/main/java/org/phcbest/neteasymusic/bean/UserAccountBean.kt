package org.phcbest.neteasymusic.bean


import com.google.gson.annotations.SerializedName

data class UserAccountBean(
    @SerializedName("account")
    val account: Account,
    @SerializedName("code")
    val code: Int, // 200
    @SerializedName("profile")
    val profile: Profile
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
        @SerializedName("paidFee")
        val paidFee: Boolean, // false
        @SerializedName("status")
        val status: Int, // 0
        @SerializedName("tokenVersion")
        val tokenVersion: Int, // 3
        @SerializedName("type")
        val type: Int, // 1
        @SerializedName("userName")
        val userName: String, // 1_********139
        @SerializedName("vipType")
        val vipType: Int, // 11
        @SerializedName("whitelistAuthority")
        val whitelistAuthority: Int // 0
    )

    data class Profile(
        @SerializedName("accountStatus")
        val accountStatus: Int, // 0
        @SerializedName("accountType")
        val accountType: Int, // 1
        @SerializedName("anchor")
        val anchor: Boolean, // false
        @SerializedName("authStatus")
        val authStatus: Int, // 0
        @SerializedName("authenticated")
        val authenticated: Boolean, // false
        @SerializedName("authenticationTypes")
        val authenticationTypes: Int, // 0
        @SerializedName("authority")
        val authority: Int, // 0
        @SerializedName("avatarDetail")
        val avatarDetail: Any, // null
        @SerializedName("avatarImgId")
        val avatarImgId: Long, // 109951166781557920
        @SerializedName("avatarUrl")
        val avatarUrl: String, // http://p1.music.126.net/Gnr50uIm1-T-65yR4LYgzQ==/109951166781557921.jpg
        @SerializedName("backgroundImgId")
        val backgroundImgId: Long, // 109951163267220300
        @SerializedName("backgroundUrl")
        val backgroundUrl: String, // http://p1.music.126.net/j0Q2uKCvUu3sbJoWqyKXrQ==/109951163267220302.jpg
        @SerializedName("birthday")
        val birthday: Long, // 631123200000
        @SerializedName("city")
        val city: Int, // 110101
        @SerializedName("createTime")
        val createTime: Long, // 1467374751660
        @SerializedName("defaultAvatar")
        val defaultAvatar: Boolean, // false
        @SerializedName("description")
        val description: Any, // null
        @SerializedName("detailDescription")
        val detailDescription: Any, // null
        @SerializedName("djStatus")
        val djStatus: Int, // 0
        @SerializedName("expertTags")
        val expertTags: Any, // null
        @SerializedName("experts")
        val experts: Any, // null
        @SerializedName("followed")
        val followed: Boolean, // false
        @SerializedName("gender")
        val gender: Int, // 1
        @SerializedName("lastLoginIP")
        val lastLoginIP: String, // 218.64.115.221
        @SerializedName("lastLoginTime")
        val lastLoginTime: Long, // 1649393270626
        @SerializedName("locationStatus")
        val locationStatus: Int, // 30
        @SerializedName("mutual")
        val mutual: Boolean, // false
        @SerializedName("nickname")
        val nickname: String, // 听歌就是为了开心
        @SerializedName("province")
        val province: Int, // 110000
        @SerializedName("remarkName")
        val remarkName: Any, // null
        @SerializedName("shortUserName")
        val shortUserName: String, // ********139
        @SerializedName("signature")
        val signature: String,
        @SerializedName("userId")
        val userId: Int, // 298385261
        @SerializedName("userName")
        val userName: String, // 1_********139
        @SerializedName("userType")
        val userType: Int, // 0
        @SerializedName("vipType")
        val vipType: Int, // 11
        @SerializedName("viptypeVersion")
        val viptypeVersion: Long // 1639784645949
    )
}