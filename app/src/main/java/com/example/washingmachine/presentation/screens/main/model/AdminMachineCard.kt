package com.example.washingmachine.presentation.screens.main.model

data class MachineCard(
    val id: String,
    val name: String,
    val status: String,
    val availableSlots: Int
)
