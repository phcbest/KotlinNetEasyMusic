package org.phcbest.neteasymusic.bean


import com.google.gson.annotations.SerializedName

data class PersonalizedPlayListBean(
    @SerializedName("category")
    val category: Int, // 0
    @SerializedName("code")
    val code: Int, // 200
    @SerializedName("hasTaste")
    val hasTaste: Boolean, // false
    @SerializedName("result")
    val result: List<Result>
) {
    data class Result(
        @SerializedName("alg")
        val alg: String, // cityLevel_B
        @SerializedName("canDislike")
        val canDislike: Boolean, // true
        @SerializedName("copywriter")
        val copywriter: String, // 热门推荐
        @SerializedName("highQuality")
        val highQuality: Boolean, // false
        @SerializedName("id")
        val id: Long, // 6952220954
        @SerializedName("name")
        val name: String, // 短视频变装神曲【舞曲 卡点 慢摇】
        @SerializedName("picUrl")
        val picUrl: String, // https://p2.music.126.net/8WiQ8y6zgNcpO0IoiBVbzg==/109951166884472394.jpg
        @SerializedName("playCount")
        val playCount: Int, // 625433
        @SerializedName("trackCount")
        val trackCount: Int, // 86
        @SerializedName("trackNumberUpdateTime")
        val trackNumberUpdateTime: Long, // 1651973881929
        @SerializedName("type")
        val type: Int // 0
    )
}