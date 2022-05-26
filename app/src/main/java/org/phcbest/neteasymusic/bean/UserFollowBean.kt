package org.phcbest.neteasymusic.bean


import com.google.gson.annotations.SerializedName

data class UserFollowBean(
    @SerializedName("code")
    val code: Int, // 200
    @SerializedName("follow")
    val follow: List<Follow>,
    @SerializedName("more")
    val more: Boolean, // true
    @SerializedName("touchCount")
    val touchCount: Int, // 0
) {
    data class Follow(
        @SerializedName("accountStatus")
        val accountStatus: Int, // 0
        @SerializedName("authStatus")
        val authStatus: Int, // 1
        @SerializedName("avatarDetail")
        val avatarDetail: AvatarDetail,
        @SerializedName("avatarUrl")
        val avatarUrl: String, // http://p1.music.126.net/TjUwKehhgYKFfAFoT2XCDQ==/109951162862731564.jpg
        @SerializedName("blacklist")
        val blacklist: Boolean, // false
        @SerializedName("eventCount")
        val eventCount: Int, // 767
        @SerializedName("expertTags")
        val expertTags: Any, // null
        @SerializedName("experts")
        val experts: Any, // null
        @SerializedName("followed")
        val followed: Boolean, // true
        @SerializedName("followeds")
        val followeds: Int, // 2223116
        @SerializedName("follows")
        val follows: Int, // 43
        @SerializedName("gender")
        val gender: Int, // 1
        @SerializedName("mutual")
        val mutual: Boolean, // false
        @SerializedName("nickname")
        val nickname: String, // 音乐人徐梦圆
        @SerializedName("playlistCount")
        val playlistCount: Int, // 195
        @SerializedName("py")
        val py: String, // ylrxmy
        @SerializedName("remarkName")
        val remarkName: Any, // null
        @SerializedName("signature")
        val signature: String, // 希望我的音乐态度能带给大家正能量。工作联系：宋先生 13391668989
        @SerializedName("time")
        val time: Int, // 0
        @SerializedName("userId")
        val userId: Long, // 6480351325
        @SerializedName("userType")
        val userType: Int, // 4
        @SerializedName("vipRights")
        val vipRights: VipRights,
        @SerializedName("vipType")
        val vipType: Int, // 11
    ) {
        data class AvatarDetail(
            @SerializedName("identityIconUrl")
            val identityIconUrl: String, // https://p5.music.126.net/obj/wo3DlcOGw6DClTvDisK1/4874132307/4499/f228/d867/da64b9725e125943ad4e14e4c72d0884.png
            @SerializedName("identityLevel")
            val identityLevel: Int, // 1
            @SerializedName("userType")
            val userType: Int, // 4
        )

        data class VipRights(
            @SerializedName("associator")
            val associator: Associator,
            @SerializedName("musicPackage")
            val musicPackage: Any, // null
            @SerializedName("redVipAnnualCount")
            val redVipAnnualCount: Int, // 1
            @SerializedName("redVipLevel")
            val redVipLevel: Int, // 6
        ) {
            data class Associator(
                @SerializedName("rights")
                val rights: Boolean, // true
                @SerializedName("vipCode")
                val vipCode: Int, // 100
            )
        }
    }
}