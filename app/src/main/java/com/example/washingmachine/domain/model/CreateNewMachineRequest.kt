package com.example.washingmachine.domain.model

data class CreateNewMachineRequest(
    val type: String,
    val name: String,
    val machineStatus: String,
    val ip: String,
    val location: String,
)
