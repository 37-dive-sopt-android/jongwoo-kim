package com.sopt.dive.data.dto.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    @SerialName("success")
    val success: Boolean,
    @SerialName("code")
    val code: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: LoginResponseData
)

@Serializable
data class LoginResponseData(
    @SerialName("userId")
    val userId: Int,
    @SerialName("message")
    val message: String
)