package com.example.washingmachine.domain.model

import com.example.washingmachine.data.remote.dto.DormitoryDto

data class StudentInfo(
    val id: String? = null,
    val dormitory: DormitoryDto? = null,
    val email: String? = null,
    val name: String? = null,
    val surname: String? = null,
    val room: String? = null,
    val money: Int? = null
)
