package org.phcbest.neteasymusic.bean


import com.google.gson.annotations.SerializedName

data class RecordRecentSongBean(
    @SerializedName("code")
    val code: Int, // 200
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
) {
    data class Data(
        @SerializedName("list")
        val list: List<ListItem>,
        @SerializedName("total")
        val total: Int, // 10
    ) {
        data class ListItem(
            @SerializedName("data")
            val `data`: Data,
            @SerializedName("playTime")
            val playTime: Long, // 1652062281000
            @SerializedName("resourceId")
            val resourceId: String, // 407485108
            @SerializedName("resourceType")
            val resourceType: String, // SONG
        ) {
            data class Data(
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
                val copyright: Int, // 0
                @SerializedName("cp")
                val cp: Int, // 743010
                @SerializedName("crbt")
                val crbt: Any, // null
                @SerializedName("djId")
                val djId: Int, // 0
                @SerializedName("dt")
                val dt: Int, // 292866
                @SerializedName("fee")
                val fee: Int, // 8
                @SerializedName("ftype")
                val ftype: Int, // 0
                @SerializedName("h")
                val h: H,
                @SerializedName("id")
                val id: Int, // 407485108
                @SerializedName("l")
                val l: L,
                @SerializedName("m")
                val m: M,
                @SerializedName("mark")
                val mark: Int, // 270464
                @SerializedName("mst")
                val mst: Int, // 9
                @SerializedName("mv")
                val mv: Int, // 0
                @SerializedName("name")
                val name: String, // Find My Way
                @SerializedName("no")
                val no: Int, // 13
                @SerializedName("noCopyrightRcmd")
                val noCopyrightRcmd: Any, // null
                @SerializedName("originCoverType")
                val originCoverType: Int, // 0
                @SerializedName("originSongSimpleData")
                val originSongSimpleData: Any, // null
                @SerializedName("pop")
                val pop: Int, // 75
                @SerializedName("pst")
                val pst: Int, // 0
                @SerializedName("publishTime")
                val publishTime: Long, // 1458259200000
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
                @SerializedName("st")
                val st: Int, // 0
                @SerializedName("t")
                val t: Int, // 0
                @SerializedName("v")
                val v: Int, // 33
            ) {
                data class Al(
                    @SerializedName("id")
                    val id: Int, // 34565186
                    @SerializedName("name")
                    val name: String, // The Narrows
                    @SerializedName("pic")
                    val pic: Long, // 109951163352161630
                    @SerializedName("pic_str")
                    val picStr: String, // 109951163352161634
                    @SerializedName("picUrl")
                    val picUrl: String, // http://p3.music.126.net/jZOBDSh3lTMNj6yemtxY_w==/109951163352161634.jpg
                    @SerializedName("tns")
                    val tns: List<Any>,
                )

                data class Ar(
                    @SerializedName("alias")
                    val alias: List<Any>,
                    @SerializedName("id")
                    val id: Int, // 33943
                    @SerializedName("name")
                    val name: String, // Grant-Lee Phillips
                    @SerializedName("tns")
                    val tns: List<Any>,
                )

                data class H(
                    @SerializedName("br")
                    val br: Int, // 320000
                    @SerializedName("fid")
                    val fid: Int, // 0
                    @SerializedName("size")
                    val size: Int, // 11717529
                    @SerializedName("vd")
                    val vd: Int, // 1797
                )

                data class L(
                    @SerializedName("br")
                    val br: Int, // 128000
                    @SerializedName("fid")
                    val fid: Int, // 0
                    @SerializedName("size")
                    val size: Int, // 4687038
                    @SerializedName("vd")
                    val vd: Int, // 0
                )

                data class M(
                    @SerializedName("br")
                    val br: Int, // 192000
                    @SerializedName("fid")
                    val fid: Int, // 0
                    @SerializedName("size")
                    val size: Int, // 7030535
                    @SerializedName("vd")
                    val vd: Int, // 0
                )
            }
        }
    }
}