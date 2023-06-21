package com.example.washingmachine.domain.model

data class AdminEditRequestBody(
    val email: String?,
    val name: String,
    val surname: String,
    val dormitoryId: String
)
