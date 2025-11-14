package com.sopt.dive.data.dto.signup

import com.sopt.dive.data.dto.user.UserInfoData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignupResponseDto(
    @SerialName("success")
    val success: Boolean,
    @SerialName("code")
    val code: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: UserInfoData
)
