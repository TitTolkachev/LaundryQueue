package com.example.washingmachine.data.remote

import com.example.washingmachine.domain.usecase.local.GetTokenFromLocalStorageUseCase
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor(
    private val getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request: Request = chain.request()
        val builder = request.newBuilder()

        if (request.header("Authorization") == null) {

            val token = getTokenFromLocalStorageUseCase.execute()
            builder.addHeader("Authorization", "Bearer ${token?.access}")
        }

        return chain.proceed(builder.build())
    }
}
