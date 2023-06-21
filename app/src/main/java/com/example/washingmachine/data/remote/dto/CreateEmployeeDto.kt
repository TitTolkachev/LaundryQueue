package com.example.washingmachine.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateEmployeeDto(
    val email: String?,
    val name: String,
    val surname: String,
)
