package com.example.washingmachine.data.remote

import com.example.washingmachine.data.remote.requests.auth.AuthLogoutApi
import com.example.washingmachine.data.remote.requests.balance.BalanceApi
import com.example.washingmachine.data.remote.requests.devicetoken.DeviceTokenApi
import com.example.washingmachine.data.remote.requests.profile.AdminProfileApi
import com.example.washingmachine.data.remote.requests.profile.StudentProfileApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class AuthNetwork(
    private val authInterceptor: AuthInterceptor,
    private val tokenAuthenticator: TokenAuthenticator,
) {

    private val BASE_URL = "http://kosterror.ru:8080/"

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private fun getHttpClient(
        //useCases: AuthNetworkUseCases
    ): OkHttpClient {
        val client = OkHttpClient.Builder().apply {
            connectTimeout(5, TimeUnit.SECONDS)
            readTimeout(10, TimeUnit.SECONDS)
            retryOnConnectionFailure(false)
            val logLevel = HttpLoggingInterceptor.Level.BODY
            addInterceptor(HttpLoggingInterceptor().setLevel(logLevel))
            //addInterceptor(AuthInterceptor(useCases.getTokenFromLocalStorageUseCase))
            addInterceptor(authInterceptor)
            authenticator(tokenAuthenticator)
//            authenticator(
//                TokenAuthenticator(
//                    useCases.getTokenFromLocalStorageUseCase,
//                    useCases.saveTokenToLocalStorageUseCase,
//                    useCases.refreshTokenUseCase
//                )
//            )
        }

        return client.build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun getAuthRetrofit(
        //useCases: AuthNetworkUseCases
    ): Retrofit {

        if (authRetrofit != null)
            return authRetrofit as Retrofit

        authRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .client(getHttpClient())
            .build()

        return authRetrofit as Retrofit
    }

    private var authRetrofit: Retrofit? = null

    fun getDeviceTokenApi(): DeviceTokenApi =
        getAuthRetrofit().create(DeviceTokenApi::class.java)

    fun getAuthApi(): AuthLogoutApi =
        getAuthRetrofit().create(AuthLogoutApi::class.java)

    fun getStudentProfileApi(): StudentProfileApi =
        getAuthRetrofit().create(StudentProfileApi::class.java)

    fun getAdminProfileApi(): AdminProfileApi =
        getAuthRetrofit().create(AdminProfileApi::class.java)

    fun getBalanceApi(): BalanceApi = getAuthRetrofit().create(BalanceApi::class.java)
}