package org.phcbest.neteasymusic.bean


import com.google.gson.annotations.SerializedName

data class PlayListDetailBean(
    @SerializedName("code")
    val code: Int, // 200
    @SerializedName("playlist")
    val playlist: Playlist,
    @SerializedName("privileges")
    val privileges: List<Privilege>,
    @SerializedName("relatedVideos")
    val relatedVideos: Any, // null
    @SerializedName("resEntrance")
    val resEntrance: Any, // null
    @SerializedName("sharedPrivilege")
    val sharedPrivilege: Any, // null
    @SerializedName("urls")
    val urls: Any, // null
) {
    data class Playlist(
        @SerializedName("adType")
        val adType: Int, // 0
        @SerializedName("backgroundCoverId")
        val backgroundCoverId: Long, // 0
        @SerializedName("backgroundCoverUrl")
        val backgroundCoverUrl: Any, // null
        @SerializedName("cloudTrackCount")
        val cloudTrackCount: Int, // 4
        @SerializedName("commentCount")
        val commentCount: Int, // 0
        @SerializedName("commentThreadId")
        val commentThreadId: String, // A_PL_0_413126379
        @SerializedName("coverImgId")
        val coverImgId: Long, // 109951167058353300
        @SerializedName("coverImgId_str")
        val coverImgIdStr: String, // 109951167058353293
        @SerializedName("coverImgUrl")
        val coverImgUrl: String, // https://p1.music.126.net/2hwWdKTRD0bMLWLXZVe2_Q==/109951167058353293.jpg
        @SerializedName("createTime")
        val createTime: Long, // 1467374567529
        @SerializedName("creator")
        val creator: Creator,
        @SerializedName("description")
        val description: String, // null
        @SerializedName("englishTitle")
        val englishTitle: Any, // null
        @SerializedName("highQuality")
        val highQuality: Boolean, // false
        @SerializedName("historySharedUsers")
        val historySharedUsers: Any, // null
        @SerializedName("id")
        val id: Long, // 413126379
        @SerializedName("name")
        val name: String, // 听歌就是为了开心喜欢的音乐
        @SerializedName("newImported")
        val newImported: Boolean, // false
        @SerializedName("officialPlaylistType")
        val officialPlaylistType: Any, // null
        @SerializedName("opRecommend")
        val opRecommend: Boolean, // false
        @SerializedName("ordered")
        val ordered: Boolean, // true
        @SerializedName("playCount")
        val playCount: Long, // 3220
        @SerializedName("privacy")
        val privacy: Int, // 0
        @SerializedName("remixVideo")
        val remixVideo: Any, // null
        @SerializedName("shareCount")
        val shareCount: Int, // 0
        @SerializedName("sharedUsers")
        val sharedUsers: Any, // null
        @SerializedName("specialType")
        val specialType: Int, // 5
        @SerializedName("status")
        val status: Int, // 0
        @SerializedName("subscribed")
        val subscribed: Boolean, // false
        @SerializedName("subscribedCount")
        val subscribedCount: Int, // 0
        @SerializedName("subscribers")
        val subscribers: List<Any>,
        @SerializedName("tags")
        val tags: List<Any>,
        @SerializedName("titleImage")
        val titleImage: Long, // 0
        @SerializedName("titleImageUrl")
        val titleImageUrl: Any, // null
        @SerializedName("trackCount")
        val trackCount: Int, // 390
        @SerializedName("trackIds")
        val trackIds: List<TrackId>,
        @SerializedName("trackNumberUpdateTime")
        val trackNumberUpdateTime: Long, // 1650542828133
        @SerializedName("trackUpdateTime")
        val trackUpdateTime: Long, // 1651034980280
        @SerializedName("tracks")
        val tracks: List<Track>,
        @SerializedName("updateFrequency")
        val updateFrequency: Any, // null
        @SerializedName("updateTime")
        val updateTime: Long, // 1650542828133
        @SerializedName("userId")
        val userId: Long, // 298385261
        @SerializedName("videoIds")
        val videoIds: Any, // null
        @SerializedName("videos")
        val videos: Any, // null
    ) {
        data class Creator(
            @SerializedName("accountStatus")
            val accountStatus: Int, // 0
            @SerializedName("anchor")
            val anchor: Boolean, // false
            @SerializedName("authStatus")
            val authStatus: Int, // 0
            @SerializedName("authenticationTypes")
            val authenticationTypes: Int, // 0
            @SerializedName("authority")
            val authority: Int, // 0
            @SerializedName("avatarDetail")
            val avatarDetail: Any, // null
            @SerializedName("avatarImgId")
            val avatarImgId: Long, // 109951166781557920
            @SerializedName("avatarImgIdStr")
            val avatarImgIdStr: String, // 109951166781557921
            @SerializedName("avatarImgId_str")
            val avatarImgId_Str: String, // 109951166781557921
            @SerializedName("avatarUrl")
            val avatarUrl: String, // http://p1.music.126.net/Gnr50uIm1-T-65yR4LYgzQ==/109951166781557921.jpg
            @SerializedName("backgroundImgId")
            val backgroundImgId: Long, // 109951163267220300
            @SerializedName("backgroundImgIdStr")
            val backgroundImgIdStr: String, // 109951163267220302
            @SerializedName("backgroundUrl")
            val backgroundUrl: String, // http://p1.music.126.net/j0Q2uKCvUu3sbJoWqyKXrQ==/109951163267220302.jpg
            @SerializedName("birthday")
            val birthday: Int, // 0
            @SerializedName("city")
            val city: Int, // 110101
            @SerializedName("defaultAvatar")
            val defaultAvatar: Boolean, // false
            @SerializedName("description")
            val description: String,
            @SerializedName("detailDescription")
            val detailDescription: String,
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
            @SerializedName("mutual")
            val mutual: Boolean, // false
            @SerializedName("nickname")
            val nickname: String, // 听歌就是为了开心
            @SerializedName("province")
            val province: Int, // 110000
            @SerializedName("remarkName")
            val remarkName: Any, // null
            @SerializedName("signature")
            val signature: String,
            @SerializedName("userId")
            val userId: Long, // 298385261
            @SerializedName("userType")
            val userType: Int, // 0
            @SerializedName("vipType")
            val vipType: Int, // 11
        )

        data class TrackId(
            @SerializedName("alg")
            val alg: Any, // null
            @SerializedName("at")
            val at: Long, // 1650542828132
            @SerializedName("id")
            val id: Int, // 1897376493
            @SerializedName("rcmdReason")
            val rcmdReason: String,
            @SerializedName("sc")
            val sc: Any, // null
            @SerializedName("t")
            val t: Int, // 0
            @SerializedName("uid")
            val uid: Long, // 298385261
            @SerializedName("v")
            val v: Int, // 9
        )

        data class Track(
            @SerializedName("a")
            val a: Any, // null
            @SerializedName("al")
            val al: Al,
            @SerializedName("alia")
            val alia: List<String>,
            @SerializedName("ar")
            val ar: List<Ar>,
            @SerializedName("cd")
            val cd: String, // 01
            @SerializedName("cf")
            val cf: String,
            @SerializedName("copyright")
            val copyright: Int, // 1
            @SerializedName("cp")
            val cp: Int, // 7002
            @SerializedName("crbt")
            val crbt: Any, // null
            @SerializedName("djId")
            val djId: Int, // 0
            @SerializedName("dt")
            val dt: Int, // 198556
            @SerializedName("entertainmentTags")
            val entertainmentTags: Any, // null
            @SerializedName("fee")
            val fee: Int, // 1
            @SerializedName("ftype")
            val ftype: Int, // 0
            @SerializedName("h")
            val h: H,
            @SerializedName("hr")
            val hr: Any, // null
            @SerializedName("id")
            val id: Int, // 1897376493
            @SerializedName("l")
            val l: L,
            @SerializedName("m")
            val m: M,
            @SerializedName("mark")
            val mark: Long, // 8192
            @SerializedName("mst")
            val mst: Int, // 9
            @SerializedName("mv")
            val mv: Int, // 0
            @SerializedName("name")
            val name: String, // 劲浪漫 超温馨
            @SerializedName("no")
            val no: Int, // 1
            @SerializedName("noCopyrightRcmd")
            val noCopyrightRcmd: Any, // null
            @SerializedName("originCoverType")
            val originCoverType: Int, // 0
            @SerializedName("originSongSimpleData")
            val originSongSimpleData: Any, // null
            @SerializedName("pc")
            val pc: Pc,
            @SerializedName("pop")
            val pop: Int, // 100
            @SerializedName("pst")
            val pst: Int, // 0
            @SerializedName("publishTime")
            val publishTime: Long, // 1637856000000
            @SerializedName("resourceState")
            val resourceState: Boolean, // true
            @SerializedName("rt")
            val rt: String,
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
            @SerializedName("sq")
            val sq: Any, // null
            @SerializedName("st")
            val st: Int, // 0
            @SerializedName("t")
            val t: Int, // 0
            @SerializedName("tagPicList")
            val tagPicList: Any, // null
            @SerializedName("tns")
            val tns: List<String>,
            @SerializedName("v")
            val v: Int, // 8
            @SerializedName("version")
            val version: Int, // 8
        ) {
            data class Al(
                @SerializedName("id")
                val id: Int, // 136498952
                @SerializedName("name")
                val name: String, // 劲浪漫 超温馨
                @SerializedName("pic")
                val pic: Long, // 109951167058323500
                @SerializedName("pic_str")
                val picStr: String, // 109951167058323504
                @SerializedName("picUrl")
                val picUrl: String, // http://p4.music.126.net/-05Jb4KzNxz-skmrERxj_A==/109951167058323504.jpg
                @SerializedName("tns")
                val tns: List<String>,
            )

            data class Ar(
                @SerializedName("alias")
                val alias: List<Any>,
                @SerializedName("id")
                val id: Int, // 32944030
                @SerializedName("name")
                val name: String, // Gareth.T
                @SerializedName("tns")
                val tns: List<Any>,
            )

            data class H(
                @SerializedName("br")
                val br: Int, // 320000
                @SerializedName("fid")
                val fid: Int, // 0
                @SerializedName("size")
                val size: Int, // 7943358
                @SerializedName("sr")
                val sr: Int, // 44100
                @SerializedName("vd")
                val vd: Int, // -38902
            )

            data class L(
                @SerializedName("br")
                val br: Int, // 128000
                @SerializedName("fid")
                val fid: Int, // 0
                @SerializedName("size")
                val size: Int, // 3177369
                @SerializedName("sr")
                val sr: Int, // 44100
                @SerializedName("vd")
                val vd: Int, // -34668
            )

            data class M(
                @SerializedName("br")
                val br: Int, // 192000
                @SerializedName("fid")
                val fid: Int, // 0
                @SerializedName("size")
                val size: Int, // 4766032
                @SerializedName("sr")
                val sr: Int, // 44100
                @SerializedName("vd")
                val vd: Int, // -36305
            )

            data class Pc(
                @SerializedName("alb")
                val alb: String,
                @SerializedName("ar")
                val ar: String,
                @SerializedName("br")
                val br: Int, // 257
                @SerializedName("cid")
                val cid: String,
                @SerializedName("fn")
                val fn: String, // 爱丫爱丫.m4a
                @SerializedName("nickname")
                val nickname: String,
                @SerializedName("sn")
                val sn: String, // 爱丫爱丫
                @SerializedName("uid")
                val uid: Long, // 298385261
                @SerializedName("version")
                val version: Int, // 1
            )
        }
    }

    data class Privilege(
        @SerializedName("chargeInfoList")
        val chargeInfoList: List<ChargeInfo>,
        @SerializedName("cp")
        val cp: Int, // 1
        @SerializedName("cs")
        val cs: Boolean, // false
        @SerializedName("dl")
        val dl: Int, // 320000
        @SerializedName("dlLevel")
        val dlLevel: String, // exhigh
        @SerializedName("downloadMaxBrLevel")
        val downloadMaxBrLevel: String, // exhigh
        @SerializedName("downloadMaxbr")
        val downloadMaxbr: Int, // 320000
        @SerializedName("fee")
        val fee: Int, // 1
        @SerializedName("fl")
        val fl: Int, // 0
        @SerializedName("flLevel")
        val flLevel: String, // none
        @SerializedName("flag")
        val flag: Int, // 4
        @SerializedName("freeTrialPrivilege")
        val freeTrialPrivilege: FreeTrialPrivilege,
        @SerializedName("id")
        val id: Int, // 1897376493
        @SerializedName("maxBrLevel")
        val maxBrLevel: String, // exhigh
        @SerializedName("maxbr")
        val maxbr: Int, // 320000
        @SerializedName("paidBigBang")
        val paidBigBang: Boolean, // false
        @SerializedName("payed")
        val payed: Int, // 1
        @SerializedName("pc")
        val pc: Any, // null
        @SerializedName("pl")
        val pl: Int, // 320000
        @SerializedName("plLevel")
        val plLevel: String, // exhigh
        @SerializedName("playMaxBrLevel")
        val playMaxBrLevel: String, // exhigh
        @SerializedName("playMaxbr")
        val playMaxbr: Int, // 320000
        @SerializedName("preSell")
        val preSell: Boolean, // false
        @SerializedName("realPayed")
        val realPayed: Int, // 0
        @SerializedName("rscl")
        val rscl: Any, // null
        @SerializedName("sp")
        val sp: Int, // 7
        @SerializedName("st")
        val st: Int, // 0
        @SerializedName("subp")
        val subp: Int, // 1
        @SerializedName("toast")
        val toast: Boolean, // false
    ) {
        data class ChargeInfo(
            @SerializedName("chargeMessage")
            val chargeMessage: Any, // null
            @SerializedName("chargeType")
            val chargeType: Int, // 1
            @SerializedName("chargeUrl")
            val chargeUrl: Any, // null
            @SerializedName("rate")
            val rate: Int, // 128000
        )

        data class FreeTrialPrivilege(
            @SerializedName("listenType")
            val listenType: Any, // null
            @SerializedName("resConsumable")
            val resConsumable: Boolean, // false
            @SerializedName("userConsumable")
            val userConsumable: Boolean, // false
        )
    }
}