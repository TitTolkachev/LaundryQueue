package com.example.washingmachine.data.remote.requests.profile

import com.example.washingmachine.data.remote.dto.StudentProfileRequestDto
import com.example.washingmachine.data.remote.dto.StudentProfileResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface StudentProfileApi {

    @GET("api/account/student")
    suspend fun getInfo(): Response<StudentProfileResponseDto>

    @PUT("api/account/student")
    suspend fun edit(@Body body: StudentProfileRequestDto): Response<StudentProfileResponseDto>
}