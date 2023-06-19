package com.example.washingmachine.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RefreshRequestDto(
    val accessToken: String,
)
