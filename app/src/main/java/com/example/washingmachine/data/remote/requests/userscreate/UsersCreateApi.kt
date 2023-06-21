package com.example.washingmachine.data.remote.requests.userscreate

import com.example.washingmachine.data.remote.dto.CreateEmployeeDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface UsersCreateApi {

    @POST("api/creator/student/{email}")
    suspend fun createStudent(@Path("email") email: String): Response<ResponseBody>

    @POST("api/creator/employee")
    suspend fun createEmployee(@Body body: CreateEmployeeDto): Response<ResponseBody>
}