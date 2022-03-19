package org.phcbest.neteasymusic.bean

import com.google.gson.annotations.SerializedName


data class DiscoverBannerBean(
    @SerializedName("banners")
    val banners: List<Banner>,
    @SerializedName("code")
    val code: Int
)

data class Banner(
    @SerializedName("adDispatchJson")
    val adDispatchJson: Any,
    @SerializedName("adLocation")
    val adLocation: Any,
    @SerializedName("adSource")
    val adSource: Any,
    @SerializedName("adid")
    val adid: Any,
    @SerializedName("adurlV2")
    val adurlV2: Any,
    @SerializedName("alg")
    val alg: Any,
    @SerializedName("bannerId")
    val bannerId: String,
    @SerializedName("dynamicVideoData")
    val dynamicVideoData: Any,
    @SerializedName("encodeId")
    val encodeId: String,
    @SerializedName("event")
    val event: Any,
    @SerializedName("exclusive")
    val exclusive: Boolean,
    @SerializedName("extMonitor")
    val extMonitor: Any,
    @SerializedName("extMonitorInfo")
    val extMonitorInfo: Any,
    @SerializedName("monitorBlackList")
    val monitorBlackList: Any,
    @SerializedName("monitorClick")
    val monitorClick: Any,
    @SerializedName("monitorClickList")
    val monitorClickList: List<Any>,
    @SerializedName("monitorImpress")
    val monitorImpress: Any,
    @SerializedName("monitorImpressList")
    val monitorImpressList: List<Any>,
    @SerializedName("monitorType")
    val monitorType: Any,
    @SerializedName("pic")
    val pic: String,
    @SerializedName("pid")
    val pid: Any,
    @SerializedName("program")
    val program: Any,
    @SerializedName("requestId")
    val requestId: String,
    @SerializedName("scm")
    val scm: String,
    @SerializedName("showAdTag")
    val showAdTag: Boolean,
    @SerializedName("showContext")
    val showContext: Any,
    @SerializedName("song")
    val song: Any,
    @SerializedName("targetId")
    val targetId: Long,
    @SerializedName("targetType")
    val targetType: Int,
    @SerializedName("titleColor")
    val titleColor: String,
    @SerializedName("typeTitle")
    val typeTitle: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("video")
    val video: Any
)