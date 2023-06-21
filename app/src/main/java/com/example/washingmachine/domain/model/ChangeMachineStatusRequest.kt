package com.example.washingmachine.domain.model

data class ChangeMachineStatusRequest(
    val machineId: String,
    val status: String,
)
