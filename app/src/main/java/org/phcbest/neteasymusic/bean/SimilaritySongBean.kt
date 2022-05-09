package org.phcbest.neteasymusic.bean


import com.google.gson.annotations.SerializedName

data class SimilaritySongBean(
    @SerializedName("code")
    val code: Int, // 200
    @SerializedName("songs")
    val songs: List<Song>,
) {
    data class Song(
        @SerializedName("album")
        val album: Album,
        @SerializedName("alg")
        val alg: String, // itembased
        @SerializedName("alias")
        val alias: List<Any>,
        @SerializedName("artists")
        val artists: List<Artist>,
        @SerializedName("audition")
        val audition: Any, // null
        @SerializedName("bMusic")
        val bMusic: BMusic,
        @SerializedName("commentThreadId")
        val commentThreadId: String, // R_SO_4_5239700
        @SerializedName("copyFrom")
        val copyFrom: String,
        @SerializedName("copyrightId")
        val copyrightId: Int, // 5003
        @SerializedName("crbt")
        val crbt: Any, // null
        @SerializedName("dayPlays")
        val dayPlays: Int, // 0
        @SerializedName("disc")
        val disc: String, // 1
        @SerializedName("duration")
        val duration: Int, // 250018
        @SerializedName("fee")
        val fee: Int, // 0
        @SerializedName("ftype")
        val ftype: Int, // 0
        @SerializedName("hMusic")
        val hMusic: HMusic,
        @SerializedName("hearTime")
        val hearTime: Int, // 0
        @SerializedName("hrMusic")
        val hrMusic: Any, // null
        @SerializedName("id")
        val id: Int, // 5239700
        @SerializedName("lMusic")
        val lMusic: LMusic,
        @SerializedName("mMusic")
        val mMusic: MMusic,
        @SerializedName("mark")
        val mark: Int, // 262144
        @SerializedName("mp3Url")
        val mp3Url: String, // http://m2.music.126.net/hmZoNQaqzZALvVp0rE7faA==/0.mp3
        @SerializedName("mvid")
        val mvid: Int, // 0
        @SerializedName("name")
        val name: String, // 天国的女儿
        @SerializedName("no")
        val no: Int, // 5
        @SerializedName("noCopyrightRcmd")
        val noCopyrightRcmd: Any, // null
        @SerializedName("originCoverType")
        val originCoverType: Int, // 0
        @SerializedName("originSongSimpleData")
        val originSongSimpleData: Any, // null
        @SerializedName("playedNum")
        val playedNum: Int, // 0
        @SerializedName("popularity")
        val popularity: Int, // 100
        @SerializedName("position")
        val position: Int, // 5
        @SerializedName("privilege")
        val privilege: Privilege,
        @SerializedName("recommendReason")
        val recommendReason: String, // 相似歌曲
        @SerializedName("ringtone")
        val ringtone: String,
        @SerializedName("rtUrl")
        val rtUrl: Any, // null
        @SerializedName("rtUrls")
        val rtUrls: Any, // null
        @SerializedName("rtype")
        val rtype: Int, // 0
        @SerializedName("rurl")
        val rurl: Any, // null
        @SerializedName("score")
        val score: Int, // 100
        @SerializedName("songJumpInfo")
        val songJumpInfo: Any, // null
        @SerializedName("sqMusic")
        val sqMusic: Any, // null
        @SerializedName("starred")
        val starred: Boolean, // false
        @SerializedName("starredNum")
        val starredNum: Int, // 0
        @SerializedName("status")
        val status: Int, // 0
    ) {
        data class Album(
            @SerializedName("alias")
            val alias: List<Any>,
            @SerializedName("artist")
            val artist: Artist,
            @SerializedName("artists")
            val artists: List<Artist>,
            @SerializedName("blurPicUrl")
            val blurPicUrl: String, // https://p2.music.126.net/VofzDZcBDMx3u3WKx_f1BA==/80264348831079.jpg
            @SerializedName("briefDesc")
            val briefDesc: String,
            @SerializedName("commentThreadId")
            val commentThreadId: String, // R_AL_3_511177
            @SerializedName("company")
            val company: String, // 山东文化音像出版社
            @SerializedName("companyId")
            val companyId: Int, // 0
            @SerializedName("copyrightId")
            val copyrightId: Int, // 5003
            @SerializedName("description")
            val description: String,
            @SerializedName("id")
            val id: Int, // 511177
            @SerializedName("mark")
            val mark: Int, // 0
            @SerializedName("name")
            val name: String, // 最好听的声音
            @SerializedName("onSale")
            val onSale: Boolean, // false
            @SerializedName("paid")
            val paid: Boolean, // false
            @SerializedName("pic")
            val pic: Long, // 80264348831079
            @SerializedName("picId")
            val picId: Long, // 80264348831079
            @SerializedName("picUrl")
            val picUrl: String, // https://p2.music.126.net/VofzDZcBDMx3u3WKx_f1BA==/80264348831079.jpg
            @SerializedName("publishTime")
            val publishTime: Long, // 1283702400000
            @SerializedName("size")
            val size: Int, // 15
            @SerializedName("songs")
            val songs: List<Any>,
            @SerializedName("status")
            val status: Int, // 1
            @SerializedName("subType")
            val subType: String, // 录音室版
            @SerializedName("tags")
            val tags: String,
            @SerializedName("type")
            val type: String, // 专辑
        ) {
            data class Artist(
                @SerializedName("albumSize")
                val albumSize: Int, // 0
                @SerializedName("alias")
                val alias: List<Any>,
                @SerializedName("briefDesc")
                val briefDesc: String,
                @SerializedName("followed")
                val followed: Boolean, // false
                @SerializedName("id")
                val id: Int, // 0
                @SerializedName("img1v1Id")
                val img1v1Id: Long, // 18686200114669624
                @SerializedName("img1v1Id_str")
                val img1v1IdStr: String, // 18686200114669622
                @SerializedName("img1v1Url")
                val img1v1Url: String, // https://p2.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg
                @SerializedName("musicSize")
                val musicSize: Int, // 0
                @SerializedName("name")
                val name: String,
                @SerializedName("picId")
                val picId: Int, // 0
                @SerializedName("picUrl")
                val picUrl: String, // https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
                @SerializedName("topicPerson")
                val topicPerson: Int, // 0
                @SerializedName("trans")
                val trans: String,
            )
        }

        data class Artist(
            @SerializedName("albumSize")
            val albumSize: Int, // 0
            @SerializedName("alias")
            val alias: List<Any>,
            @SerializedName("briefDesc")
            val briefDesc: String,
            @SerializedName("followed")
            val followed: Boolean, // false
            @SerializedName("id")
            val id: Int, // 122455
            @SerializedName("img1v1Id")
            val img1v1Id: Long, // 18686200114669624
            @SerializedName("img1v1Id_str")
            val img1v1IdStr: String, // 18686200114669622
            @SerializedName("img1v1Url")
            val img1v1Url: String, // https://p2.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg
            @SerializedName("musicSize")
            val musicSize: Int, // 0
            @SerializedName("name")
            val name: String, // 群星
            @SerializedName("picId")
            val picId: Int, // 0
            @SerializedName("picUrl")
            val picUrl: String, // https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
            @SerializedName("topicPerson")
            val topicPerson: Int, // 0
            @SerializedName("trans")
            val trans: String,
        )

        data class BMusic(
            @SerializedName("bitrate")
            val bitrate: Int, // 96000
            @SerializedName("dfsId")
            val dfsId: Int, // 0
            @SerializedName("extension")
            val extension: String, // mp3
            @SerializedName("id")
            val id: Long, // 3923294693
            @SerializedName("name")
            val name: String,
            @SerializedName("playTime")
            val playTime: Int, // 250018
            @SerializedName("size")
            val size: Int, // 3037803
            @SerializedName("sr")
            val sr: Int, // 44100
            @SerializedName("volumeDelta")
            val volumeDelta: Int, // -22854
        )

        data class HMusic(
            @SerializedName("bitrate")
            val bitrate: Int, // 320000
            @SerializedName("dfsId")
            val dfsId: Int, // 0
            @SerializedName("extension")
            val extension: String, // mp3
            @SerializedName("id")
            val id: Int, // 22832937
            @SerializedName("name")
            val name: String,
            @SerializedName("playTime")
            val playTime: Int, // 250018
            @SerializedName("size")
            val size: Int, // 10033317
            @SerializedName("sr")
            val sr: Int, // 44100
            @SerializedName("volumeDelta")
            val volumeDelta: Int, // -22854
        )

        data class LMusic(
            @SerializedName("bitrate")
            val bitrate: Int, // 96000
            @SerializedName("dfsId")
            val dfsId: Int, // 0
            @SerializedName("extension")
            val extension: String, // mp3
            @SerializedName("id")
            val id: Long, // 3923294693
            @SerializedName("name")
            val name: String,
            @SerializedName("playTime")
            val playTime: Int, // 250018
            @SerializedName("size")
            val size: Int, // 3037803
            @SerializedName("sr")
            val sr: Int, // 44100
            @SerializedName("volumeDelta")
            val volumeDelta: Int, // -22854
        )

        data class MMusic(
            @SerializedName("bitrate")
            val bitrate: Int, // 160000
            @SerializedName("dfsId")
            val dfsId: Int, // 0
            @SerializedName("extension")
            val extension: String, // mp3
            @SerializedName("id")
            val id: Int, // 22832938
            @SerializedName("name")
            val name: String,
            @SerializedName("playTime")
            val playTime: Int, // 250018
            @SerializedName("size")
            val size: Int, // 5036484
            @SerializedName("sr")
            val sr: Int, // 44100
            @SerializedName("volumeDelta")
            val volumeDelta: Int, // -22854
        )

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
            val fee: Int, // 0
            @SerializedName("fl")
            val fl: Int, // 320000
            @SerializedName("flLevel")
            val flLevel: String, // exhigh
            @SerializedName("flag")
            val flag: Int, // 0
            @SerializedName("freeTrialPrivilege")
            val freeTrialPrivilege: FreeTrialPrivilege,
            @SerializedName("id")
            val id: Int, // 5239700
            @SerializedName("maxBrLevel")
            val maxBrLevel: String, // exhigh
            @SerializedName("maxbr")
            val maxbr: Int, // 320000
            @SerializedName("payed")
            val payed: Int, // 0
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
                val chargeType: Int, // 0
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
}