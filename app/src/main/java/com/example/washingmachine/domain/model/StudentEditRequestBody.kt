package com.example.washingmachine.domain.model

data class StudentEditRequestBody(
    val email: String?,
    val name: String,
    val surname: String,
    val studentNumber: String,
    val room: String,
    val dormitoryId: String,
)
