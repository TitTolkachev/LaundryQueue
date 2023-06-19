package com.example.washingmachine.data.remote.requests.auth

import com.example.washingmachine.data.remote.dto.LoginRequestDto
import com.example.washingmachine.data.remote.dto.LoginResponseDto
import com.example.washingmachine.data.remote.dto.RefreshRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("api/login")
    suspend fun login(@Body body: LoginRequestDto): Response<LoginResponseDto>

    @POST("api")
    suspend fun refresh(@Body body: RefreshRequestDto): Response<LoginResponseDto>
}