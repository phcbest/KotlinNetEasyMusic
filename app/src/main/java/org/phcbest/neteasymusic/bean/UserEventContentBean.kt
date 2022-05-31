package org.phcbest.neteasymusic.bean


import com.google.gson.annotations.SerializedName

data class UserEventContentBean(
    @SerializedName("msg")
    val msg: String, // 不知道为啥我的专辑怎么没有推送！呜呜呜你们快来听8
    @SerializedName("song")
    val song: Song,
) {
    data class Song(
        @SerializedName("album")
        val album: Album,
        @SerializedName("alias")
        val alias: List<Any>,
        @SerializedName("artists")
        val artists: List<Artist>,
        @SerializedName("audition")
        val audition: Any, // null
        @SerializedName("bMusic")
        val bMusic: BMusic,
        @SerializedName("commentThreadId")
        val commentThreadId: String, // R_SO_4_1381550790
        @SerializedName("copyFrom")
        val copyFrom: String,
        @SerializedName("copyright")
        val copyright: Int, // 0
        @SerializedName("copyrightId")
        val copyrightId: Int, // 0
        @SerializedName("crbt")
        val crbt: Any, // null
        @SerializedName("dayPlays")
        val dayPlays: Int, // 0
        @SerializedName("disc")
        val disc: String, // 01
        @SerializedName("duration")
        val duration: Int, // 244140
        @SerializedName("fee")
        val fee: Int, // 0
        @SerializedName("ftype")
        val ftype: Int, // 0
        @SerializedName("hMusic")
        val hMusic: HMusic,
        @SerializedName("hearTime")
        val hearTime: Int, // 0
        @SerializedName("id")
        val id: Int, // 1381550790
        @SerializedName("lMusic")
        val lMusic: LMusic,
        @SerializedName("mMusic")
        val mMusic: MMusic,
        @SerializedName("mp3Url")
        val mp3Url: Any, // null
        @SerializedName("mvid")
        val mvid: Int, // 0
        @SerializedName("name")
        val name: String, // 呼吸决定（Cover：Fine乐团）
        @SerializedName("no")
        val no: Int, // 1
        @SerializedName("playedNum")
        val playedNum: Int, // 0
        @SerializedName("popularity")
        val popularity: Double, // 0.0
        @SerializedName("position")
        val position: Int, // 0
        @SerializedName("ringtone")
        val ringtone: String,
        @SerializedName("rtUrl")
        val rtUrl: Any, // null
        @SerializedName("rtUrls")
        val rtUrls: List<Any>,
        @SerializedName("rtype")
        val rtype: Int, // 0
        @SerializedName("rurl")
        val rurl: Any, // null
        @SerializedName("score")
        val score: Int, // 0
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
            val blurPicUrl: String, // http://p1.music.126.net/QEnDRFEnpnuatFXe5Epqvw==/109951164257872931.jpg
            @SerializedName("briefDesc")
            val briefDesc: String,
            @SerializedName("commentThreadId")
            val commentThreadId: String, // R_AL_3_80692212
            @SerializedName("company")
            val company: Any, // null
            @SerializedName("companyId")
            val companyId: Int, // 0
            @SerializedName("copyrightId")
            val copyrightId: Int, // 0
            @SerializedName("description")
            val description: String,
            @SerializedName("id")
            val id: Int, // 80692212
            @SerializedName("img80x80")
            val img80x80: String, // https://p2.music.126.net/QEnDRFEnpnuatFXe5Epqvw==/109951164257872931.jpg?param=80x80x1
            @SerializedName("name")
            val name: String, // 呼吸决定
            @SerializedName("pic")
            val pic: Long, // 109951164257872931
            @SerializedName("picId")
            val picId: Long, // 109951164257872931
            @SerializedName("picUrl")
            val picUrl: String, // http://p1.music.126.net/QEnDRFEnpnuatFXe5Epqvw==/109951164257872931.jpg
            @SerializedName("publishTime")
            val publishTime: Long, // 1564638414708
            @SerializedName("size")
            val size: Int, // 1
            @SerializedName("songs")
            val songs: List<Any>,
            @SerializedName("status")
            val status: Int, // 0
            @SerializedName("tags")
            val tags: String,
            @SerializedName("type")
            val type: String, // 专辑
        )

        data class Artist(
            @SerializedName("albumSize")
            val albumSize: Int, // 0
            @SerializedName("alias")
            val alias: List<Any>,
            @SerializedName("briefDesc")
            val briefDesc: String,
            @SerializedName("id")
            val id: Int, // 32828242
            @SerializedName("img1v1Id")
            val img1v1Id: Long, // 0
            @SerializedName("img1v1Url")
            val img1v1Url: String, // http://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
            @SerializedName("musicSize")
            val musicSize: Int, // 0
            @SerializedName("name")
            val name: String, // 阿梓
            @SerializedName("picId")
            val picId: Long, // 0
            @SerializedName("picUrl")
            val picUrl: String, // http://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
            @SerializedName("trans")
            val trans: String,
        )

        data class BMusic(
            @SerializedName("bitrate")
            val bitrate: Int, // 128000
            @SerializedName("dfsId")
            val dfsId: Long, // 0
            @SerializedName("extension")
            val extension: String, // mp3
            @SerializedName("id")
            val id: Long, // 3879699564
            @SerializedName("name")
            val name: Any, // null
            @SerializedName("playTime")
            val playTime: Int, // 244140
            @SerializedName("size")
            val size: Int, // 3906708
            @SerializedName("sr")
            val sr: Int, // 44100
            @SerializedName("volumeDelta")
            val volumeDelta: Double, // -11070.0
        )

        data class HMusic(
            @SerializedName("bitrate")
            val bitrate: Int, // 320000
            @SerializedName("dfsId")
            val dfsId: Long, // 0
            @SerializedName("extension")
            val extension: String, // mp3
            @SerializedName("id")
            val id: Long, // 3879699562
            @SerializedName("name")
            val name: Any, // null
            @SerializedName("playTime")
            val playTime: Int, // 244140
            @SerializedName("size")
            val size: Int, // 9766705
            @SerializedName("sr")
            val sr: Int, // 44100
            @SerializedName("volumeDelta")
            val volumeDelta: Double, // -15478.0
        )

        data class LMusic(
            @SerializedName("bitrate")
            val bitrate: Int, // 128000
            @SerializedName("dfsId")
            val dfsId: Long, // 0
            @SerializedName("extension")
            val extension: String, // mp3
            @SerializedName("id")
            val id: Long, // 3879699564
            @SerializedName("name")
            val name: Any, // null
            @SerializedName("playTime")
            val playTime: Int, // 244140
            @SerializedName("size")
            val size: Int, // 3906708
            @SerializedName("sr")
            val sr: Int, // 44100
            @SerializedName("volumeDelta")
            val volumeDelta: Double, // -11070.0
        )

        data class MMusic(
            @SerializedName("bitrate")
            val bitrate: Int, // 192000
            @SerializedName("dfsId")
            val dfsId: Long, // 0
            @SerializedName("extension")
            val extension: String, // mp3
            @SerializedName("id")
            val id: Long, // 3879699563
            @SerializedName("name")
            val name: Any, // null
            @SerializedName("playTime")
            val playTime: Int, // 244140
            @SerializedName("size")
            val size: Int, // 5860040
            @SerializedName("sr")
            val sr: Int, // 44100
            @SerializedName("volumeDelta")
            val volumeDelta: Double, // -12856.0
        )
    }
}