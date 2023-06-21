package com.example.washingmachine.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class QueueSlotDto(
    val id: String?,
    val number: Int?,
    val personId: String?,
    val status: String?,
)
