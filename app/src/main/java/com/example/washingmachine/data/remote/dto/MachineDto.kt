package com.example.washingmachine.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class MachineDto(
    val id: String?,
    val name: String?,
    val startTime: String?,
    val type: String?,
    val status: String?,
    val queueSlots: List<QueueSlotDto>?,
    val location: String?,
)
