package org.phcbest.neteasymusic.bean

import com.google.gson.annotations.SerializedName


class LoginQrBean {

    data class LoginQrKeyBean(
        @SerializedName("code")
        val code: Int, // 200
        @SerializedName("data")
        val `data`: Data,
    ) {
        data class Data(
            @SerializedName("code")
            val code: Int, // 200
            @SerializedName("unikey")
            val unikey: String, // 86826bb1-b513-427b-a570-09f9e685197c
        )
    }

    data class LoginQrCreateBean(
        @SerializedName("code")
        val code: Int, // 200
        @SerializedName("data")
        val `data`: Data,
    ) {
        data class Data(
            @SerializedName("qrimg")
            val qrimg: String,
            @SerializedName("qrurl")
            val qrurl: String, // https://music.163.com/login?codekey=a3229756-732c-4b7d-a02c-9b7279ac3032
        )
    }

    data class LoginQrCheckBean(
        @SerializedName("code")
        val code: Int, // 803
        @SerializedName("cookie")
        val cookie: String, // MUSIC_SNS=; Max-Age=0; Expires=Thu, 29 Sep 2022 03:26:40 GMT; Path=/;MUSIC_R_T=1467374751660; Max-Age=2147483647; Expires=Tue, 17 Oct 2090 06:40:47 GMT; Path=/neapi/feedback; HTTPOnly;MUSIC_A_T=1467374746375; Max-Age=2147483647; Expires=Tue, 17 Oct 2090 06:40:47 GMT; Path=/eapi/feedback; HTTPOnly;MUSIC_U=84b3c07ed49bbd047151d48cbfcb25409ff285eaf488846b54adb037b0626abe8a08bd5bf851808f3d6f632c02b1d1044e35d7d001d38cff7d35e5dc1ebda9da75873b5ac30ea765a0d2166338885bd7; Max-Age=15552000; Expires=Tue, 28 Mar 2023 03:26:40 GMT; Path=/; HTTPOnly;MUSIC_R_T=1467374751660; Max-Age=2147483647; Expires=Tue, 17 Oct 2090 06:40:47 GMT; Path=/weapi/feedback; HTTPOnly;MUSIC_A_T=1467374746375; Max-Age=2147483647; Expires=Tue, 17 Oct 2090 06:40:47 GMT; Path=/weapi/clientlog; HTTPOnly;MUSIC_R_T=1467374751660; Max-Age=2147483647; Expires=Tue, 17 Oct 2090 06:40:47 GMT; Path=/wapi/feedback; HTTPOnly;MUSIC_A_T=1467374746375; Max-Age=2147483647; Expires=Tue, 17 Oct 2090 06:40:47 GMT; Path=/wapi/feedback; HTTPOnly;MUSIC_A_T=1467374746375; Max-Age=2147483647; Expires=Tue, 17 Oct 2090 06:40:47 GMT; Path=/neapi/clientlog; HTTPOnly;MUSIC_A_T=1467374746375; Max-Age=2147483647; Expires=Tue, 17 Oct 2090 06:40:47 GMT; Path=/api/clientlog; HTTPOnly;MUSIC_R_T=1467374751660; Max-Age=2147483647; Expires=Tue, 17 Oct 2090 06:40:47 GMT; Path=/weapi/clientlog; HTTPOnly;MUSIC_A_T=1467374746375; Max-Age=2147483647; Expires=Tue, 17 Oct 2090 06:40:47 GMT; Path=/neapi/feedback; HTTPOnly;__csrf=038ea8c22ecf7f7a4f9650f449257b3f; Max-Age=1296010; Expires=Fri, 14 Oct 2022 03:26:50 GMT; Path=/;;MUSIC_A_T=1467374746375; Max-Age=2147483647; Expires=Tue, 17 Oct 2090 06:40:47 GMT; Path=/eapi/clientlog; HTTPOnly;MUSIC_A_T=1467374746375; Max-Age=2147483647; Expires=Tue, 17 Oct 2090 06:40:47 GMT; Path=/openapi/clientlog; HTTPOnly;MUSIC_R_T=1467374751660; Max-Age=2147483647; Expires=Tue, 17 Oct 2090 06:40:47 GMT; Path=/wapi/clientlog; HTTPOnly;MUSIC_R_T=1467374751660; Max-Age=2147483647; Expires=Tue, 17 Oct 2090 06:40:47 GMT; Path=/api/feedback; HTTPOnly;MUSIC_R_T=1467374751660; Max-Age=2147483647; Expires=Tue, 17 Oct 2090 06:40:47 GMT; Path=/neapi/clientlog; HTTPOnly;MUSIC_R_T=1467374751660; Max-Age=2147483647; Expires=Tue, 17 Oct 2090 06:40:47 GMT; Path=/eapi/feedback; HTTPOnly;MUSIC_A_T=1467374746375; Max-Age=2147483647; Expires=Tue, 17 Oct 2090 06:40:47 GMT; Path=/weapi/feedback; HTTPOnly;MUSIC_A_T=1467374746375; Max-Age=2147483647; Expires=Tue, 17 Oct 2090 06:40:47 GMT; Path=/wapi/clientlog; HTTPOnly;MUSIC_R_T=1467374751660; Max-Age=2147483647; Expires=Tue, 17 Oct 2090 06:40:47 GMT; Path=/openapi/clientlog; HTTPOnly;MUSIC_R_T=1467374751660; Max-Age=2147483647; Expires=Tue, 17 Oct 2090 06:40:47 GMT; Path=/api/clientlog; HTTPOnly;MUSIC_R_T=1467374751660; Max-Age=2147483647; Expires=Tue, 17 Oct 2090 06:40:47 GMT; Path=/eapi/clientlog; HTTPOnly;MUSIC_A_T=1467374746375; Max-Age=2147483647; Expires=Tue, 17 Oct 2090 06:40:47 GMT; Path=/api/feedback; HTTPOnly
        @SerializedName("message")
        val message: String, // 授权登陆成功
    )
}