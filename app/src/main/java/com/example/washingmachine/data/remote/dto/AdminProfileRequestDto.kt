package com.example.washingmachine.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class AdminProfileRequestDto(
    val email: String?,
    val name: String,
    val surname: String,
    val dormitoryId: String
)
