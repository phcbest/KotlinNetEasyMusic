package org.phcbest.neteasymusic.bean


import com.google.gson.annotations.SerializedName

data class SongUrlBean(
    @SerializedName("code")
    val code: Int, // 200
    @SerializedName("data")
    val `data`: List<Data>
) {
    data class Data(
        @SerializedName("br")
        val br: Int, // 128001
        @SerializedName("canExtend")
        val canExtend: Boolean, // false
        @SerializedName("code")
        val code: Int, // 200
        @SerializedName("encodeType")
        val encodeType: Any, // null
        @SerializedName("expi")
        val expi: Int, // 1200
        @SerializedName("fee")
        val fee: Int, // 8
        @SerializedName("flag")
        val flag: Int, // 4
        @SerializedName("freeTimeTrialPrivilege")
        val freeTimeTrialPrivilege: FreeTimeTrialPrivilege,
        @SerializedName("freeTrialInfo")
        val freeTrialInfo: Any, // null
        @SerializedName("freeTrialPrivilege")
        val freeTrialPrivilege: FreeTrialPrivilege,
        @SerializedName("gain")
        val gain: Int, // 0
        @SerializedName("id")
        val id: Int, // 29732992
        @SerializedName("level")
        val level: Any, // null
        @SerializedName("md5")
        val md5: String, // cd16800967e08d8d048f07333223e0bd
        @SerializedName("payed")
        val payed: Int, // 0
        @SerializedName("size")
        val size: Int, // 4067204
        @SerializedName("type")
        val type: String, // mp3
        @SerializedName("uf")
        val uf: Any, // null
        @SerializedName("url")
        val url: String, // http://m801.music.126.net/20220325204752/dc3acec9539cf02ddca0f88408a98f05/jdymusic/obj/wo3DlMOGwrbDjj7DisKw/8955009583/a44c/8b30/9bfe/cd16800967e08d8d048f07333223e0bd.mp3
        @SerializedName("urlSource")
        val urlSource: Int // 0
    ) {
        data class FreeTimeTrialPrivilege(
            @SerializedName("remainTime")
            val remainTime: Int, // 0
            @SerializedName("resConsumable")
            val resConsumable: Boolean, // false
            @SerializedName("type")
            val type: Int, // 0
            @SerializedName("userConsumable")
            val userConsumable: Boolean // false
        )

        data class FreeTrialPrivilege(
            @SerializedName("listenType")
            val listenType: Any, // null
            @SerializedName("resConsumable")
            val resConsumable: Boolean, // false
            @SerializedName("userConsumable")
            val userConsumable: Boolean // false
        )
    }
}