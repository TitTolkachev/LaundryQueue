package com.example.washingmachine.domain.model

data class Machine(
    val id: String?,
    val name: String?,
    val startTime: String?,
    val type: String?,
    val status: String?,
    val queueSlots: List<QueueSlot>?,
    val location: String?,
)