package com.example.washingmachine.data.remote.requests.devicetoken

import com.example.washingmachine.data.remote.dto.DeviceTokenRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface DeviceTokenApi {

    @POST("api/token")
    suspend fun sendToken(@Body body: DeviceTokenRequestDto): Response<Any>
}