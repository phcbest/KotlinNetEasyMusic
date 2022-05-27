package org.phcbest.neteasymusic.bean

import com.google.gson.annotations.SerializedName


data class HotTopicBean(
    @SerializedName("code")
    val code: Int, // 200
    @SerializedName("hot")
    val hot: List<Hot>,
) {
    data class Hot(
        @SerializedName("actId")
        val actId: Int, // 115842105
        @SerializedName("alg")
        val alg: String, // featured
        @SerializedName("bizId")
        val bizId: Any, // null
        @SerializedName("bizType")
        val bizType: Any, // null
        @SerializedName("iconUrl")
        val iconUrl: Any, // null
        @SerializedName("isDefaultImg")
        val isDefaultImg: Boolean, // false
        @SerializedName("memberCount")
        val memberCount: Any, // null
        @SerializedName("onlineNum")
        val onlineNum: Any, // null
        @SerializedName("participateCount")
        val participateCount: Int, // 2812
        @SerializedName("readCnt")
        val readCnt: Any, // null
        @SerializedName("reason")
        val reason: String,
        @SerializedName("sharePicUrl")
        val sharePicUrl: String, // https://p1.music.126.net/wdKjMvO2A7XTbti5wDqtpg==/109951166784247177.jpg
        @SerializedName("text")
        val text: List<String>,
        @SerializedName("title")
        val title: String, // 最后的水族馆
        @SerializedName("topicDisplayType")
        val topicDisplayType: Any, // null
    )
}