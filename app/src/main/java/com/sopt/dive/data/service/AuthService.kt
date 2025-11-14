package com.sopt.dive.data.service

import com.sopt.dive.data.dto.login.LoginRequestDto
import com.sopt.dive.data.dto.login.LoginResponseDto
import com.sopt.dive.data.dto.signup.SignupRequestDto
import com.sopt.dive.data.dto.signup.SignupResponseDto
import com.sopt.dive.data.dto.user.GetUserInfoResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {
    @POST("/api/v1/users")
    suspend fun signup(@Body request: SignupRequestDto) : Response<SignupResponseDto>

    @POST("/api/v1/auth/login")
    suspend fun login(@Body request: LoginRequestDto) : Response<LoginResponseDto>

    @GET("/api/v1/users/{id}")
    suspend fun getUserInfo(@Path("id") id: Long) : Response<GetUserInfoResponseDto>
}