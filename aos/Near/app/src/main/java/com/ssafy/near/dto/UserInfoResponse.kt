package com.ssafy.near.dto

import com.google.gson.annotations.SerializedName

data class UserInfoResponse(
    @SerializedName("data") val userInfo: UserInfo,
    val msg: String,
    val output: Int
)