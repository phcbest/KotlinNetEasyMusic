package org.phcbest.neteasymusic.bean


import com.google.gson.annotations.SerializedName

data class VipGrowthpointBean(
    @SerializedName("code")
    val code: Int, // 200
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String // success
) {
    data class Data(
        @SerializedName("levelCard")
        val levelCard: LevelCard,
        @SerializedName("userLevel")
        val userLevel: UserLevel
    ) {
        data class LevelCard(
            @SerializedName("backgroundImageUrl")
            val backgroundImageUrl: String, // http://p6.music.126.net/obj/wovDmcKXw6PCn2_CmsOk/4189769377/b289/c689/46ee/af94aab335d43213acafe43a15615790.png
            @SerializedName("blurryBackgroundImageUrl")
            val blurryBackgroundImageUrl: String, // http://p6.music.126.net/obj/wovDmcKXw6PCn2_CmsOk/4189782137/bb8b/d4f1/2dfc/4fd034161868c7eafae58f74ee0ee619.png
            @SerializedName("level")
            val level: Int, // 5
            @SerializedName("levelBackgroundCardExpireImageUrl")
            val levelBackgroundCardExpireImageUrl: String, // http://p6.music.126.net/obj/wovDmcKXw6PCn2_CmsOk/4428485882/8104/a41d/ddfa/d6ebfd7c28b65f67112c9987e37232d8.jpg
            @SerializedName("levelBackgroundCardImageUrl")
            val levelBackgroundCardImageUrl: String, // http://p6.music.126.net/obj/wovDmcKXw6PCn2_CmsOk/4428471557/5278/932a/76dd/4d23c6cd44a591b65c8c71fdc39c00c7.jpg
            @SerializedName("levelMarkExpireImageUrl")
            val levelMarkExpireImageUrl: String, // http://p6.music.126.net/obj/wovDmcKXw6PCn2_CmsOk/4189751842/6f42/c267/c4ae/8a91f74f5187ee3c58fdf3d0e813c50d.png
            @SerializedName("levelMarkImageUrl")
            val levelMarkImageUrl: String, // http://p6.music.126.net/obj/wovDmcKXw6PCn2_CmsOk/4428563516/4168/f446/2030/60bf0a2b5926bb5a2b21333cb0a2e989.jpg
            @SerializedName("levelName")
            val levelName: String, // 黑胶·伍
            @SerializedName("privilegeIconUrl")
            val privilegeIconUrl: String, // http://p6.music.126.net/obj/wovDmcKXw6PCn2_CmsOk/4189760236/ee56/2017/bc5f/97135e925ef7c601a7d0fe5d46d77986.png
            @SerializedName("privilegeName")
            val privilegeName: String, // V5等级标识
            @SerializedName("privilegeSubTitle")
            val privilegeSubTitle: String, // V5尊享
            @SerializedName("redVipBuckleImageUrl")
            val redVipBuckleImageUrl: String, // http://p6.music.126.net/obj/wovDmcKXw6PCn2_CmsOk/4442447345/13a8/4604/0080/3841eb6369414c8e3a33dd8e073f47a9.png
            @SerializedName("redVipExpireBuckleImageUrl")
            val redVipExpireBuckleImageUrl: String, // http://p6.music.126.net/obj/wovDmcKXw6PCn2_CmsOk/4442451154/305d/10e0/69e3/651015eddd06930e0474bca26b5e5c2d.png
            @SerializedName("redVipExpireImageUrl")
            val redVipExpireImageUrl: String, // http://p6.music.126.net/obj/wovDmcKXw6PCn2_CmsOk/4189731543/e0fc/f3ab/65ba/56307284639ea10c4dc2d1bd1427934a.png
            @SerializedName("redVipExpireWholeImageUrl")
            val redVipExpireWholeImageUrl: String, // http://p6.music.126.net/obj/wovDmcKXw6PCn2_CmsOk/4189794776/002b/fc5b/4ac3/f57fcccd449471e6e6a728b6d703d736.png
            @SerializedName("redVipImageUrl")
            val redVipImageUrl: String, // http://p6.music.126.net/obj/wovDmcKXw6PCn2_CmsOk/4189730866/81eb/9288/68a5/a427a0dbf899d616c3f715272a71ee59.png
            @SerializedName("redVipWholeImageUrl")
            val redVipWholeImageUrl: String, // http://p6.music.126.net/obj/wovDmcKXw6PCn2_CmsOk/4400045859/5c0a/8718/47d9/74abf08d6326cb516dd2f9549fb8608c.png
            @SerializedName("resourceId")
            val resourceId: Int, // 0
            @SerializedName("rightId")
            val rightId: Int, // 0
            @SerializedName("upgradeFireworksImageUrl")
            val upgradeFireworksImageUrl: String, // http://p6.music.126.net/obj/wovDmcKXw6PCn2_CmsOk/4440848815/0ad8/b7a5/33e4/15e827ba9f8a74a47ab34ee07a9df9d6.png
            @SerializedName("vipGiftExpireRightBarImageUrl")
            val vipGiftExpireRightBarImageUrl: Any, // null
            @SerializedName("vipGiftRightBarImageUrl")
            val vipGiftRightBarImageUrl: String, // http://p6.music.126.net/obj/wovDmcKXw6PCn2_CmsOk/4428552162/ddd4/e3d1/26e8/e32f425a21293bb988720cad00a7a2c0.jpg
            @SerializedName("vipLevelPageCardImgUrl")
            val vipLevelPageCardImgUrl: String, // http://p6.music.126.net/obj/wovDmcKXw6PCn2_CmsOk/4582306495/abb2/bce5/5010/3e06780c0faba2af564ebf2cb0854372.jpg
            @SerializedName("vipLevelPageExpireCardImgUrl")
            val vipLevelPageExpireCardImgUrl: String // http://p6.music.126.net/obj/wovDmcKXw6PCn2_CmsOk/4582316139/a2a5/c04b/9736/43db12356c7926dffdf6542e181cbce5.jpg
        )

        data class UserLevel(
            @SerializedName("avatarUrl")
            val avatarUrl: Any, // null
            @SerializedName("expireTime")
            val expireTime: Long, // 1720972799000
            @SerializedName("extJson")
            val extJson: String, // {"yearMonth":"20227","lastDay":"2022522","lastDayScore":450,"todayScore":447,"currentDay":"2022714","totalScore":0,"monthTaskTotalScore":0}
            @SerializedName("growthPoint")
            val growthPoint: Int, // 5847
            @SerializedName("level")
            val level: Int, // 5
            @SerializedName("levelName")
            val levelName: String, // 黑胶·伍
            @SerializedName("maxLevel")
            val maxLevel: Boolean, // false
            @SerializedName("normal")
            val normal: Boolean, // true
            @SerializedName("userId")
            val userId: Int, // 298385261
            @SerializedName("vipType")
            val vipType: Int, // 100
            @SerializedName("yesterdayPoint")
            val yesterdayPoint: Int // 0
        )
    }
}