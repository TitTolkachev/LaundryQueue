package com.example.washingmachine.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class StudentProfileRequestDto(
    val email: String?,
    val name: String,
    val surname: String,
    val studentNumber: String,
    val room: String,
    val dormitoryId: String,
)
