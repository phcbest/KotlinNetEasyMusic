package org.phcbest.neteasymusic.bean

import com.google.gson.annotations.SerializedName


data class SongListBean(
    @SerializedName("code")
    val code: Int,
    @SerializedName("result")
    val result: Result
)

data class Result(
    @SerializedName("hasMore")
    val hasMore: Boolean,
    @SerializedName("songCount")
    val songCount: Int,
    @SerializedName("songs")
    val songs: List<Song>
)

data class Song(
    @SerializedName("album")
    val album: Album,
    @SerializedName("alias")
    val alias: List<String>,
    @SerializedName("artists")
    val artists: List<ArtistX>,
    @SerializedName("copyrightId")
    val copyrightId: Int,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("fee")
    val fee: Int,
    @SerializedName("ftype")
    val ftype: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("mark")
    val mark: Long,
    @SerializedName("mvid")
    val mvid: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("rUrl")
    val rUrl: Any,
    @SerializedName("rtype")
    val rtype: Int,
    @SerializedName("status")
    val status: Int,
    @SerializedName("transNames")
    val transNames: List<String>
)

data class Album(
    @SerializedName("artist")
    val artist: Artist,
    @SerializedName("copyrightId")
    val copyrightId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("mark")
    val mark: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("picId")
    val picId: Long,
    @SerializedName("publishTime")
    val publishTime: Long,
    @SerializedName("size")
    val size: Int,
    @SerializedName("status")
    val status: Int
)

data class ArtistX(
    @SerializedName("albumSize")
    val albumSize: Int,
    @SerializedName("alias")
    val alias: List<Any>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("img1v1")
    val img1v1: Int,
    @SerializedName("img1v1Url")
    val img1v1Url: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("picId")
    val picId: Int,
    @SerializedName("picUrl")
    val picUrl: Any,
    @SerializedName("trans")
    val trans: Any
)

data class Artist(
    @SerializedName("albumSize")
    val albumSize: Int,
    @SerializedName("alias")
    val alias: List<Any>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("img1v1")
    val img1v1: Int,
    @SerializedName("img1v1Url")
    val img1v1Url: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("picId")
    val picId: Int,
    @SerializedName("picUrl")
    val picUrl: Any,
    @SerializedName("trans")
    val trans: Any
)