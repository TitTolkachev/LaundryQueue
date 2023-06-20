package com.example.washingmachine.data.remote.requests.auth

import com.example.washingmachine.data.remote.dto.LogoutRequestDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthLogoutApi {

    @POST("api/logout")
    suspend fun logout(@Body body: LogoutRequestDto): Response<ResponseBody>
}