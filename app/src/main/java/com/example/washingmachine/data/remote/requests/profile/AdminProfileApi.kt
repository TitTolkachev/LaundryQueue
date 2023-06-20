package com.example.washingmachine.data.remote.requests.profile

import com.example.washingmachine.data.remote.dto.AdminProfileRequestDto
import com.example.washingmachine.data.remote.dto.AdminProfileResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface AdminProfileApi {

    @GET("api/account/admin")
    suspend fun getInfo(): Response<AdminProfileResponseDto>

    @PUT("api/account/admin")
    suspend fun edit(@Body body: AdminProfileRequestDto): Response<AdminProfileResponseDto>
}