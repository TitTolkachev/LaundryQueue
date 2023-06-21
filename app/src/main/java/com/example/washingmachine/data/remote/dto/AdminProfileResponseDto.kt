package com.example.washingmachine.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class AdminProfileResponseDto(
    val id: String?,
    val email: String?,
    val name: String?,
    val surname: String?,
    val money: Double?,
    val role: String?,
    val dormitoryDto: DormitoryDto?,

)