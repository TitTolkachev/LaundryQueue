package com.example.washingmachine.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class DeviceTokenRequestDto(
    val token: String
)