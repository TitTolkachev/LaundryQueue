package com.example.washingmachine.data.remote.requests.balance

import com.example.washingmachine.data.remote.dto.AdminProfileResponseDto
import com.example.washingmachine.data.remote.dto.PersonDto
import com.example.washingmachine.data.remote.dto.StudentProfileResponseDto
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface BalanceApi {

    @POST("api/money/increase")
    suspend fun topUpBalance(@Query("delta") delta: Int): Response<PersonDto>


    @POST("api/money/decrease")
    suspend fun takeOutBalance(@Query("delta") delta: Int): Response<PersonDto>
}