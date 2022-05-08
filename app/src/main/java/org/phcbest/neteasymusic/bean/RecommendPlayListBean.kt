package org.phcbest.neteasymusic.bean


import com.google.gson.annotations.SerializedName

data class RecommendPlayListBean(
    @SerializedName("code")
    val code: Int, // 200
    @SerializedName("featureFirst")
    val featureFirst: Boolean, // true
    @SerializedName("haveRcmdSongs")
    val haveRcmdSongs: Boolean, // false
    @SerializedName("recommend")
    val recommend: List<Recommend>?,
) {
    data class Recommend(
        @SerializedName("alg")
        val alg: String, // alg_mgc_red
        @SerializedName("copywriter")
        val copywriter: Any, // null
        @SerializedName("createTime")
        val createTime: Long, // 1577330551437
        @SerializedName("creator")
        val creator: Creator,
        @SerializedName("id")
        val id: Long, // 3136952023
        @SerializedName("name")
        val name: String, // 私人雷达 | 根据听歌记录为你打造
        @SerializedName("picUrl")
        val picUrl: String, // https://p2.music.126.net/3I-73aQn3YCw-2cZdK1fQw==/109951166027478939.jpg
        @SerializedName("playcount")
        val playcount: Long, // 11204155392
        @SerializedName("trackCount")
        val trackCount: Int, // 55
        @SerializedName("type")
        val type: Int, // 1
        @SerializedName("userId")
        val userId: Long, // 1287293193
    ) {
        data class Creator(
            @SerializedName("accountStatus")
            val accountStatus: Int, // 0
            @SerializedName("authStatus")
            val authStatus: Int, // 1
            @SerializedName("authority")
            val authority: Int, // 0
            @SerializedName("avatarImgId")
            val avatarImgId: Long, // 109951165005238080
            @SerializedName("avatarImgIdStr")
            val avatarImgIdStr: String, // 109951165005238078
            @SerializedName("avatarUrl")
            val avatarUrl: String, // https://p1.music.126.net/4ZjO1oj0WTeN5U19FpnQFw==/109951165005238078.jpg
            @SerializedName("backgroundImgId")
            val backgroundImgId: Long, // 109951165449481020
            @SerializedName("backgroundImgIdStr")
            val backgroundImgIdStr: String, // 109951165449481018
            @SerializedName("backgroundUrl")
            val backgroundUrl: String, // http://p1.music.126.net/-7gz68N_fr_bikp_-Q3hjA==/109951165449481018.jpg
            @SerializedName("birthday")
            val birthday: Int, // 0
            @SerializedName("city")
            val city: Int, // 310101
            @SerializedName("defaultAvatar")
            val defaultAvatar: Boolean, // false
            @SerializedName("description")
            val description: String, // 云音乐私人雷达官方账号
            @SerializedName("detailDescription")
            val detailDescription: String, // 云音乐私人雷达官方账号
            @SerializedName("djStatus")
            val djStatus: Int, // 0
            @SerializedName("expertTags")
            val expertTags: Any, // null
            @SerializedName("followed")
            val followed: Boolean, // false
            @SerializedName("gender")
            val gender: Int, // 0
            @SerializedName("mutual")
            val mutual: Boolean, // false
            @SerializedName("nickname")
            val nickname: String, // 云音乐私人雷达
            @SerializedName("province")
            val province: Int, // 310000
            @SerializedName("remarkName")
            val remarkName: Any, // null
            @SerializedName("signature")
            val signature: String, // 喵~~~
            @SerializedName("userId")
            val userId: Long, // 1287293193
            @SerializedName("userType")
            val userType: Int, // 10
            @SerializedName("vipType")
            val vipType: Int, // 0
        )
    }
}