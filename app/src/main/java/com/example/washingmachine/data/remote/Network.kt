package com.example.washingmachine.data.remote

import com.example.washingmachine.data.remote.requests.auth.AuthApi
import com.example.washingmachine.data.remote.requests.dormitory.DormitoryApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class Network {

    private val BASE_URL = "http://95.142.46.40:8080/"

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private fun getHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder().apply {
            connectTimeout(5, TimeUnit.SECONDS)
            readTimeout(10, TimeUnit.SECONDS)
            retryOnConnectionFailure(false)
            val logLevel = HttpLoggingInterceptor.Level.BODY
            addInterceptor(HttpLoggingInterceptor().setLevel(logLevel))
        }

        return client.build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun getRetrofit(): Retrofit {

        if (retrofit != null)
            return retrofit as Retrofit

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .client(getHttpClient())
            .build()

        return retrofit as Retrofit
    }

    private var retrofit: Retrofit? = null

    fun getAuthApi(): AuthApi = getRetrofit().create(AuthApi::class.java)

    fun getDormitoryApi(): DormitoryApi = getRetrofit().create(DormitoryApi::class.java)

}