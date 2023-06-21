package com.example.washingmachine.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateNewMachineDto(
    val type: String,
    val name: String,
    val machineStatus: String,
    val ip: String,
    val location: String,
)
