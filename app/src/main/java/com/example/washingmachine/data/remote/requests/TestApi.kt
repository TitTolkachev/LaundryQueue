package com.example.washingmachine.data.remote.requests

import kotlinx.serialization.Serializable
import retrofit2.http.Body
import retrofit2.http.POST

@Serializable
data class TokenBody(
    val token: String
)

interface TestApi {

    @POST("api/token")
    suspend fun sendToken(@Body body: TokenBody)
}