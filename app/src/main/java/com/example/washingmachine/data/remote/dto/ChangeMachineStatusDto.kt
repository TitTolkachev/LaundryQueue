package com.example.washingmachine.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ChangeMachineStatusDto(
    val machineId: String,
    val status: String,
)
