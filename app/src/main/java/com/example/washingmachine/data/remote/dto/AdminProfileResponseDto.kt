package com.example.washingmachine.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class AdminProfileResponseDto(
    val id: String?,
    val email: String?,
    val dormitoryDto: DormitoryDto?,
    val name: String?,
    val surname: String?,
    val money: Int?,
    val role: String?,
    val dormitoryId: String?,


    )