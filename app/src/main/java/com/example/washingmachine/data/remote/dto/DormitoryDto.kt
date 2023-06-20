package com.example.washingmachine.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class DormitoryDto(
    val id: String = "",
    val number: Int = 0
)