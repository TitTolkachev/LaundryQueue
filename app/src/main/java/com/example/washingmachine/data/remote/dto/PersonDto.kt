package com.example.washingmachine.data.remote.dto

import com.example.washingmachine.domain.model.Roles
import kotlinx.serialization.Serializable

@Serializable
data class PersonDto(
    val id: String?,
    val email: String?,
    val name: String?,
    val surname: String?,
    val money: Float?,
    val role: Roles?,
    val dormitoryId: String?
)
