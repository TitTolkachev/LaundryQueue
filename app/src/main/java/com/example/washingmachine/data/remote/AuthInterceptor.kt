package com.example.washingmachine.data.remote

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor(
    //private val getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request: Request = chain.request()
        val builder = request.newBuilder()

        if (request.header("Authorization") == null) {

            // TODO
            //val token = getTokenFromLocalStorageUseCase.execute()
            //builder.addHeader("Authorization", "Bearer ${token?.accessToken}")

            builder.addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJsYXVuZHJ5LXF1ZXVlLWFwaSIsInN1YiI6IjRhNDk1ZDI4LTEzY2EtNDI0Mi1iODM4LTI5ZGVkNmM3ZDFiMyIsImlkIjoiNGE0OTVkMjgtMTNjYS00MjQyLWI4MzgtMjlkZWQ2YzdkMWIzIiwicm9sZSI6IlJPTEVfQURNSU4iLCJlbWFpbCI6ImV4YW1wbGVAZG9tYWluLmNvbSIsImlhdCI6MTY4NzAyMzk4NSwiZXhwIjoxNjg3MDI1Nzg1fQ.kFXge0_VLKuWKe1aOpmNq0NMSCCJ5SAMr-eaF3eSys8")
        }

        return chain.proceed(builder.build())
    }
}
