package com.example.washingmachine.domain.util

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : Resource<T>(data)
    class NetworkError<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class ValidationError<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Exception<T>(e: kotlin.Exception) : Resource<T>(message = e.message.toString())
}
