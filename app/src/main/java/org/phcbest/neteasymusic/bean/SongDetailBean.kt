package org.phcbest.neteasymusic.bean


import com.google.gson.annotations.SerializedName

data class SongDetailBean(
    @SerializedName("code")
    val code: Int, // 200
    @SerializedName("privileges")
    val privileges: List<Privilege>,
    @SerializedName("songs")
    val songs: List<Song>
) {
    data class Privilege(
        @SerializedName("chargeInfoList")
        val chargeInfoList: List<ChargeInfo>,
        @SerializedName("cp")
        val cp: Int, // 1
        @SerializedName("cs")
        val cs: Boolean, // false
        @SerializedName("dl")
        val dl: Int, // 0
        @SerializedName("downloadMaxbr")
        val downloadMaxbr: Int, // 320000
        @SerializedName("fee")
        val fee: Int, // 8
        @SerializedName("fl")
        val fl: Int, // 128000
        @SerializedName("flag")
        val flag: Int, // 4
        @SerializedName("freeTrialPrivilege")
        val freeTrialPrivilege: FreeTrialPrivilege,
        @SerializedName("id")
        val id: Int, // 29732992
        @SerializedName("maxbr")
        val maxbr: Int, // 320000
        @SerializedName("payed")
        val payed: Int, // 0
        @SerializedName("pl")
        val pl: Int, // 128000
        @SerializedName("playMaxbr")
        val playMaxbr: Int, // 320000
        @SerializedName("preSell")
        val preSell: Boolean, // false
        @SerializedName("rscl")
        val rscl: Any, // null
        @SerializedName("sp")
        val sp: Int, // 7
        @SerializedName("st")
        val st: Int, // 0
        @SerializedName("subp")
        val subp: Int, // 1
        @SerializedName("toast")
        val toast: Boolean // false
    ) {
        data class ChargeInfo(
            @SerializedName("chargeMessage")
            val chargeMessage: Any, // null
            @SerializedName("chargeType")
            val chargeType: Int, // 0
            @SerializedName("chargeUrl")
            val chargeUrl: Any, // null
            @SerializedName("rate")
            val rate: Int // 128000
        )

        data class FreeTrialPrivilege(
            @SerializedName("resConsumable")
            val resConsumable: Boolean, // false
            @SerializedName("userConsumable")
            val userConsumable: Boolean // false
        )
    }

    data class Song(
        @SerializedName("a")
        val a: Any, // null
        @SerializedName("al")
        val al: Al,
        @SerializedName("alia")
        val alia: List<String>,
        @SerializedName("ar")
        val ar: List<Ar>,
        @SerializedName("cd")
        val cd: String, // 1
        @SerializedName("cf")
        val cf: String,
        @SerializedName("copyright")
        val copyright: Int, // 1
        @SerializedName("cp")
        val cp: Int, // 2706476
        @SerializedName("crbt")
        val crbt: Any, // null
        @SerializedName("djId")
        val djId: Int, // 0
        @SerializedName("dt")
        val dt: Int, // 254197
        @SerializedName("entertainmentTags")
        val entertainmentTags: Any, // null
        @SerializedName("fee")
        val fee: Int, // 8
        @SerializedName("ftype")
        val ftype: Int, // 0
        @SerializedName("h")
        val h: H,
        @SerializedName("id")
        val id: Int, // 29732992
        @SerializedName("l")
        val l: L,
        @SerializedName("m")
        val m: M,
        @SerializedName("mark")
        val mark: Long, // 9007199255011328
        @SerializedName("mst")
        val mst: Int, // 9
        @SerializedName("mv")
        val mv: Int, // 365266
        @SerializedName("name")
        val name: String, // 光るなら
        @SerializedName("no")
        val no: Int, // 1
        @SerializedName("noCopyrightRcmd")
        val noCopyrightRcmd: Any, // null
        @SerializedName("originCoverType")
        val originCoverType: Int, // 1
        @SerializedName("originSongSimpleData")
        val originSongSimpleData: Any, // null
        @SerializedName("pop")
        val pop: Int, // 100
        @SerializedName("pst")
        val pst: Int, // 0
        @SerializedName("publishTime")
        val publishTime: Long, // 1416326400000
        @SerializedName("resourceState")
        val resourceState: Boolean, // true
        @SerializedName("rt")
        val rt: Any, // null
        @SerializedName("rtUrl")
        val rtUrl: Any, // null
        @SerializedName("rtUrls")
        val rtUrls: List<Any>,
        @SerializedName("rtype")
        val rtype: Int, // 0
        @SerializedName("rurl")
        val rurl: Any, // null
        @SerializedName("s_id")
        val sId: Int, // 0
        @SerializedName("single")
        val single: Int, // 0
        @SerializedName("songJumpInfo")
        val songJumpInfo: Any, // null
        @SerializedName("st")
        val st: Int, // 0
        @SerializedName("t")
        val t: Int, // 0
        @SerializedName("tagPicList")
        val tagPicList: Any, // null
        @SerializedName("tns")
        val tns: List<String>?,
        @SerializedName("v")
        val v: Int, // 33
        @SerializedName("version")
        val version: Int // 33
    ) {
        data class Al(
            @SerializedName("id")
            val id: Int, // 3069158
            @SerializedName("name")
            val name: String, // 光るなら
            @SerializedName("pic")
            val pic: Long, // 109951166200199780
            @SerializedName("pic_str")
            val picStr: String, // 109951166200199773
            @SerializedName("picUrl")
            val picUrl: String, // https://p2.music.126.net/w1pQ83qhoVMJ_eMpRyoAtA==/109951166200199773.jpg
            @SerializedName("tns")
            val tns: List<Any>
        )

        data class Ar(
            @SerializedName("alias")
            val alias: List<Any>,
            @SerializedName("id")
            val id: Int, // 19618
            @SerializedName("name")
            val name: String, // Goose house
            @SerializedName("tns")
            val tns: List<Any>
        )

        data class H(
            @SerializedName("br")
            val br: Int, // 320001
            @SerializedName("fid")
            val fid: Int, // 0
            @SerializedName("size")
            val size: Int, // 10167946
            @SerializedName("vd")
            val vd: Int // -74805
        )

        data class L(
            @SerializedName("br")
            val br: Int, // 128001
            @SerializedName("fid")
            val fid: Int, // 0
            @SerializedName("size")
            val size: Int, // 4067204
            @SerializedName("vd")
            val vd: Int // -70739
        )

        data class M(
            @SerializedName("br")
            val br: Int, // 192001
            @SerializedName("fid")
            val fid: Int, // 0
            @SerializedName("size")
            val size: Int, // 6100785
            @SerializedName("vd")
            val vd: Int // -72309
        )
    }
}