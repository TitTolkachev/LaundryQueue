package com.example.washingmachine.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class StudentProfileResponseDto(
    val id: String?,
    val dormitoryId: String?,
    val studentNumber: String?,
    val email: String?,
    val name: String?,
    val surname: String?,
    val room: String?,
    val money: Double?
)
