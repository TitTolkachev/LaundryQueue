package com.example.washingmachine.domain.model

import com.example.washingmachine.data.remote.dto.DormitoryDto

data class AdminInfo(
    val id: String? = null,
    val email: String? = null,
    val name: String? = null,
    val surname: String? = null,
    val money: Double? = null,
    val role: String? = null,
    val dormitory: DormitoryDto? = null
)
