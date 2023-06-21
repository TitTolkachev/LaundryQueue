package com.example.washingmachine.data.remote.requests.dormitory

import com.example.washingmachine.data.remote.dto.DormitoryDto
import retrofit2.Response
import retrofit2.http.GET

interface DormitoryApi {

    @GET("api/dormitories")
    suspend fun getDormitories(): Response<List<DormitoryDto>>

}