package org.phcbest.neteasymusic.bean


import com.google.gson.annotations.SerializedName

data class UserEventBean(
    @SerializedName("code")
    val code: Int, // 200
    @SerializedName("events")
    val events: List<Event>,
    @SerializedName("lasttime")
    val lasttime: Long, // 1568728000406
    @SerializedName("more")
    val more: Boolean, // true
    @SerializedName("size")
    val size: Int, // 16
) {
    data class Event(
        @SerializedName("actId")
        val actId: Int, // 0
        @SerializedName("actName")
        val actName: Any, // null
        @SerializedName("alterLinkUrl")
        val alterLinkUrl: Any, // null
        @SerializedName("alterLinkWebviewUrl")
        val alterLinkWebviewUrl: Any, // null
        @SerializedName("anonymityInfo")
        val anonymityInfo: Any, // null
        @SerializedName("bottomActivityInfos")
        val bottomActivityInfos: Any, // null
        @SerializedName("discussId")
        val discussId: String, // a1ZyNEP2dw5eV68V
        @SerializedName("eventActionToast")
        val eventActionToast: Any, // null
        @SerializedName("eventTime")
        val eventTime: Long, // 1568728000406
        @SerializedName("expireTime")
        val expireTime: Int, // 0
        @SerializedName("extJsonInfo")
        val extJsonInfo: ExtJsonInfo,
        @SerializedName("extSource")
        val extSource: Any, // null
        @SerializedName("extType")
        val extType: String,
        @SerializedName("forwardCount")
        val forwardCount: Int, // 1
        @SerializedName("h5Target")
        val h5Target: Any, // null
        @SerializedName("id")
        val id: Long, // 7715379737
        @SerializedName("info")
        val info: Info,
        @SerializedName("insiteForwardCount")
        val insiteForwardCount: Int, // 1
        @SerializedName("json")
        val json: String, // {"msg":"来啦","song":{"name":"孽海记（Cover：黄诗扶）","id":1391517472,"position":0,"alias":[],"status":0,"fee":0,"copyrightId":0,"disc":"01","no":1,"artists":[{"name":"阿梓","id":32828242,"picId":0,"img1v1Id":0,"briefDesc":"","picUrl":"http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1Url":"http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","albumSize":0,"alias":[],"trans":"","musicSize":0}],"album":{"name":"孽海记","id":81700625,"type":"专辑","size":1,"picId":109951164370530675,"blurPicUrl":"http://p2.music.126.net/3ZxO9MlN13Z0qSOvTN7w8Q==/109951164370530675.jpg","companyId":0,"pic":109951164370530675,"picUrl":"http://p2.music.126.net/3ZxO9MlN13Z0qSOvTN7w8Q==/109951164370530675.jpg","publishTime":1568725481053,"description":"","tags":"","company":null,"briefDesc":"","artist":{"name":"","id":0,"picId":0,"img1v1Id":0,"briefDesc":"","picUrl":"http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1Url":"http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","albumSize":0,"alias":[],"trans":"","musicSize":0},"songs":[],"alias":[],"status":0,"copyrightId":0,"commentThreadId":"R_AL_3_81700625","artists":[{"name":"阿梓","id":32828242,"picId":0,"img1v1Id":0,"briefDesc":"","picUrl":"http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1Url":"http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","albumSize":0,"alias":[],"trans":"","musicSize":0}],"img80x80":"http://p2.music.126.net/3ZxO9MlN13Z0qSOvTN7w8Q==/109951164370530675.jpg?param=80x80x1"},"starred":false,"popularity":0.0,"score":0,"starredNum":0,"duration":239804,"playedNum":0,"dayPlays":0,"hearTime":0,"ringtone":"","crbt":null,"audition":null,"copyFrom":"","commentThreadId":"R_SO_4_1391517472","rtUrl":null,"ftype":0,"rtUrls":[],"copyright":0,"hMusic":{"name":null,"id":3972490245,"size":9594297,"extension":"mp3","sr":44100,"dfsId":0,"bitrate":320000,"playTime":239804,"volumeDelta":-29778.0},"mMusic":{"name":null,"id":3972490246,"size":5756595,"extension":"mp3","sr":44100,"dfsId":0,"bitrate":192000,"playTime":239804,"volumeDelta":-27176.0},"lMusic":{"name":null,"id":3972490247,"size":3837745,"extension":"mp3","sr":44100,"dfsId":0,"bitrate":128000,"playTime":239804,"volumeDelta":-25516.0},"bMusic":{"name":null,"id":3972490247,"size":3837745,"extension":"mp3","sr":44100,"dfsId":0,"bitrate":128000,"playTime":239804,"volumeDelta":-25516.0},"rtype":0,"rurl":null,"mvid":0,"mp3Url":null}}
        @SerializedName("logInfo")
        val logInfo: Any, // null
        @SerializedName("lotteryEventData")
        val lotteryEventData: Any, // null
        @SerializedName("more")
        val more: Boolean, // false
        @SerializedName("pendantData")
        val pendantData: PendantData,
        @SerializedName("pics")
        val pics: List<Any>,
        @SerializedName("pointTopicInfo")
        val pointTopicInfo: Any, // null
        @SerializedName("privacySetting")
        val privacySetting: Any, // null
        @SerializedName("question")
        val question: Any, // null
        @SerializedName("rcmdInfo")
        val rcmdInfo: Any, // null
        @SerializedName("relationTopic")
        val relationTopic: Any, // null
        @SerializedName("showTime")
        val showTime: Long, // 1568728000406
        @SerializedName("tailMark")
        val tailMark: Any, // null
        @SerializedName("tmplId")
        val tmplId: Int, // 0
        @SerializedName("topActivityInfos")
        val topActivityInfos: Any, // null
        @SerializedName("topEvent")
        val topEvent: Boolean, // false
        @SerializedName("type")
        val type: Int, // 18
        @SerializedName("typeDesc")
        val typeDesc: Any, // null
        @SerializedName("user")
        val user: User,
        @SerializedName("uuid")
        val uuid: Any, // null
        @SerializedName("voice")
        val voice: Any, // null
        @SerializedName("xInfo")
        val xInfo: XInfo,
    ) {
        data class ExtJsonInfo(
            @SerializedName("actId")
            val actId: Int, // 0
            @SerializedName("actIds")
            val actIds: List<Any>,
            @SerializedName("activityInfos")
            val activityInfos: Any, // null
            @SerializedName("anonymityInfo")
            val anonymityInfo: Any, // null
            @SerializedName("circleId")
            val circleId: Any, // null
            @SerializedName("circlePubType")
            val circlePubType: Any, // null
            @SerializedName("extId")
            val extId: String,
            @SerializedName("extParams")
            val extParams: ExtParams,
            @SerializedName("extSource")
            val extSource: Any, // null
            @SerializedName("extType")
            val extType: String,
            @SerializedName("pointTopicInfo")
            val pointTopicInfo: Any, // null
            @SerializedName("privacySetting")
            val privacySetting: Any, // null
            @SerializedName("questionId")
            val questionId: Any, // null
            @SerializedName("tailMark")
            val tailMark: Any, // null
            @SerializedName("titleAlias")
            val titleAlias: Any, // null
            @SerializedName("typeDesc")
            val typeDesc: Any, // null
            @SerializedName("uuid")
            val uuid: Any, // null
            @SerializedName("voiceInfo")
            val voiceInfo: Any, // null
        ) {
            class ExtParams
        }

        data class Info(
            @SerializedName("commentCount")
            val commentCount: Int, // 72
            @SerializedName("commentThread")
            val commentThread: CommentThread,
            @SerializedName("comments")
            val comments: Any, // null
            @SerializedName("latestLikedUsers")
            val latestLikedUsers: Any, // null
            @SerializedName("liked")
            val liked: Boolean, // false
            @SerializedName("likedCount")
            val likedCount: Int, // 284
            @SerializedName("resourceId")
            val resourceId: Long, // 7715379737
            @SerializedName("resourceType")
            val resourceType: Int, // 2
            @SerializedName("shareCount")
            val shareCount: Int, // 1
            @SerializedName("threadId")
            val threadId: String, // A_EV_2_7715379737_81498444
        ) {
            data class CommentThread(
                @SerializedName("commentCount")
                val commentCount: Int, // 72
                @SerializedName("extProperties")
                val extProperties: Any, // null
                @SerializedName("hotCount")
                val hotCount: Int, // 9
                @SerializedName("id")
                val id: String, // A_EV_2_7715379737_81498444
                @SerializedName("latestLikedUsers")
                val latestLikedUsers: List<LatestLikedUser>,
                @SerializedName("likedCount")
                val likedCount: Int, // 284
                @SerializedName("resourceId")
                val resourceId: Long, // 7715379737
                @SerializedName("resourceInfo")
                val resourceInfo: ResourceInfo,
                @SerializedName("resourceOwnerId")
                val resourceOwnerId: Int, // 81498444
                @SerializedName("resourceTitle")
                val resourceTitle: String, // 分享单曲：「孽海记（Cover：黄诗扶）」
                @SerializedName("resourceType")
                val resourceType: Int, // 2
                @SerializedName("shareCount")
                val shareCount: Int, // 1
                @SerializedName("xInfo")
                val xInfo: Any, // null
            ) {
                data class LatestLikedUser(
                    @SerializedName("s")
                    val s: Long, // 1366061199
                    @SerializedName("t")
                    val t: Long, // 1653534197787
                )

                data class ResourceInfo(
                    @SerializedName("artistAreaCode")
                    val artistAreaCode: Int, // 0
                    @SerializedName("artistId")
                    val artistId: Int, // 0
                    @SerializedName("creator")
                    val creator: Any, // null
                    @SerializedName("encodedId")
                    val encodedId: Any, // null
                    @SerializedName("eventType")
                    val eventType: Int, // 18
                    @SerializedName("id")
                    val id: Long, // 7715379737
                    @SerializedName("imgUrl")
                    val imgUrl: Any, // null
                    @SerializedName("name")
                    val name: String, // 分享单曲：「孽海记（Cover：黄诗扶）」
                    @SerializedName("nativeUrl")
                    val nativeUrl: Any, // null
                    @SerializedName("resourceSpecialType")
                    val resourceSpecialType: Any, // null
                    @SerializedName("subTitle")
                    val subTitle: Any, // null
                    @SerializedName("userId")
                    val userId: Int, // 81498444
                    @SerializedName("webUrl")
                    val webUrl: Any, // null
                )
            }
        }

        data class PendantData(
            @SerializedName("id")
            val id: Int, // 24000
            @SerializedName("imageAndroidUrl")
            val imageAndroidUrl: String, // http://p1.music.126.net/jHQe01L7vuXGmgme7heNbg==/109951164255589642.jpg
            @SerializedName("imageIosUrl")
            val imageIosUrl: String, // http://p1.music.126.net/d6gG8X7T0gsyhAtAx77HeA==/109951164255586743.jpg
            @SerializedName("imageUrl")
            val imageUrl: String, // http://p1.music.126.net/EJMMnKyBo0bsmVhggxGQDw==/109951164219710144.jpg
        )

        data class User(
            @SerializedName("accountStatus")
            val accountStatus: Int, // 0
            @SerializedName("anchor")
            val anchor: Boolean, // false
            @SerializedName("authStatus")
            val authStatus: Int, // 1
            @SerializedName("authenticationTypes")
            val authenticationTypes: Int, // 8
            @SerializedName("authority")
            val authority: Int, // 0
            @SerializedName("avatarDetail")
            val avatarDetail: AvatarDetail,
            @SerializedName("avatarImgId")
            val avatarImgId: Long, // 109951166385459260
            @SerializedName("avatarImgIdStr")
            val avatarImgIdStr: String, // 109951166385459264
            @SerializedName("avatarImgId_str")
            val avatarImgId_Str: String, // 109951166385459264
            @SerializedName("avatarUrl")
            val avatarUrl: String, // http://p1.music.126.net/vOvh9fa0kUcV_NEnlFxcfA==/109951166385459264.jpg
            @SerializedName("backgroundImgId")
            val backgroundImgId: Long, // 109951166385467380
            @SerializedName("backgroundImgIdStr")
            val backgroundImgIdStr: String, // 109951166385467379
            @SerializedName("backgroundUrl")
            val backgroundUrl: String, // http://p1.music.126.net/mIpyaICx7BJMzjqFqUR9ow==/109951166385467379.jpg
            @SerializedName("birthday")
            val birthday: Long, // 1608393600000
            @SerializedName("city")
            val city: Int, // 1010000
            @SerializedName("commonIdentity")
            val commonIdentity: Any, // null
            @SerializedName("defaultAvatar")
            val defaultAvatar: Boolean, // false
            @SerializedName("description")
            val description: String,
            @SerializedName("detailDescription")
            val detailDescription: String,
            @SerializedName("djStatus")
            val djStatus: Int, // 10
            @SerializedName("expertTags")
            val expertTags: Any, // null
            @SerializedName("experts")
            val experts: Any, // null
            @SerializedName("followed")
            val followed: Boolean, // true
            @SerializedName("followeds")
            val followeds: Int, // 32909
            @SerializedName("gender")
            val gender: Int, // 2
            @SerializedName("mutual")
            val mutual: Boolean, // false
            @SerializedName("nickname")
            val nickname: String, // 阿梓从小就很可爱
            @SerializedName("province")
            val province: Int, // 1000000
            @SerializedName("remarkName")
            val remarkName: Any, // null
            @SerializedName("signature")
            val signature: String, // 阿梓从小就很可爱
            @SerializedName("urlAnalyze")
            val urlAnalyze: Boolean, // false
            @SerializedName("userId")
            val userId: Int, // 81498444
            @SerializedName("userType")
            val userType: Int, // 4
            @SerializedName("vipRights")
            val vipRights: VipRights,
            @SerializedName("vipType")
            val vipType: Int, // 11
        ) {
            data class AvatarDetail(
                @SerializedName("identityIconUrl")
                val identityIconUrl: String, // https://p5.music.126.net/obj/wo3DlcOGw6DClTvDisK1/4874132307/4499/f228/d867/da64b9725e125943ad4e14e4c72d0884.png
                @SerializedName("identityLevel")
                val identityLevel: Int, // 1
                @SerializedName("userType")
                val userType: Int, // 4
            )

            data class VipRights(
                @SerializedName("associator")
                val associator: Associator,
                @SerializedName("musicPackage")
                val musicPackage: Any, // null
                @SerializedName("redVipAnnualCount")
                val redVipAnnualCount: Int, // -1
                @SerializedName("redVipLevel")
                val redVipLevel: Int, // 6
            ) {
                data class Associator(
                    @SerializedName("rights")
                    val rights: Boolean, // true
                    @SerializedName("vipCode")
                    val vipCode: Int, // 100
                )
            }
        }

        data class XInfo(
            @SerializedName("info")
            val info: Info,
            @SerializedName("insiteForwardCount")
            val insiteForwardCount: Int, // 1
            @SerializedName("topEvent")
            val topEvent: Boolean, // false
        ) {
            data class Info(
                @SerializedName("commentCount")
                val commentCount: Int, // 72
                @SerializedName("commentThread")
                val commentThread: CommentThread,
                @SerializedName("comments")
                val comments: Any, // null
                @SerializedName("latestLikedUsers")
                val latestLikedUsers: Any, // null
                @SerializedName("liked")
                val liked: Boolean, // false
                @SerializedName("likedCount")
                val likedCount: Int, // 284
                @SerializedName("resourceId")
                val resourceId: Long, // 7715379737
                @SerializedName("resourceType")
                val resourceType: Int, // 2
                @SerializedName("shareCount")
                val shareCount: Int, // 1
                @SerializedName("threadId")
                val threadId: String, // A_EV_2_7715379737_81498444
            ) {
                data class CommentThread(
                    @SerializedName("commentCount")
                    val commentCount: Int, // 72
                    @SerializedName("extProperties")
                    val extProperties: Any, // null
                    @SerializedName("hotCount")
                    val hotCount: Int, // 9
                    @SerializedName("id")
                    val id: String, // A_EV_2_7715379737_81498444
                    @SerializedName("latestLikedUsers")
                    val latestLikedUsers: List<LatestLikedUser>,
                    @SerializedName("likedCount")
                    val likedCount: Int, // 284
                    @SerializedName("resourceId")
                    val resourceId: Long, // 7715379737
                    @SerializedName("resourceInfo")
                    val resourceInfo: ResourceInfo,
                    @SerializedName("resourceOwnerId")
                    val resourceOwnerId: Int, // 81498444
                    @SerializedName("resourceTitle")
                    val resourceTitle: String, // 分享单曲：「孽海记（Cover：黄诗扶）」
                    @SerializedName("resourceType")
                    val resourceType: Int, // 2
                    @SerializedName("shareCount")
                    val shareCount: Int, // 1
                    @SerializedName("xInfo")
                    val xInfo: Any, // null
                ) {
                    data class LatestLikedUser(
                        @SerializedName("s")
                        val s: Long, // 1366061199
                        @SerializedName("t")
                        val t: Long, // 1653534197787
                    )

                    data class ResourceInfo(
                        @SerializedName("artistAreaCode")
                        val artistAreaCode: Int, // 0
                        @SerializedName("artistId")
                        val artistId: Int, // 0
                        @SerializedName("creator")
                        val creator: Any, // null
                        @SerializedName("encodedId")
                        val encodedId: Any, // null
                        @SerializedName("eventType")
                        val eventType: Int, // 18
                        @SerializedName("id")
                        val id: Long, // 7715379737
                        @SerializedName("imgUrl")
                        val imgUrl: Any, // null
                        @SerializedName("name")
                        val name: String, // 分享单曲：「孽海记（Cover：黄诗扶）」
                        @SerializedName("nativeUrl")
                        val nativeUrl: Any, // null
                        @SerializedName("resourceSpecialType")
                        val resourceSpecialType: Any, // null
                        @SerializedName("subTitle")
                        val subTitle: Any, // null
                        @SerializedName("userId")
                        val userId: Int, // 81498444
                        @SerializedName("webUrl")
                        val webUrl: Any, // null
                    )
                }
            }
        }
    }
}