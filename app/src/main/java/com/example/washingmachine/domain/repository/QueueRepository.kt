package com.example.washingmachine.domain.repository

import com.example.washingmachine.domain.model.QueueSlot
import com.example.washingmachine.domain.util.Resource

interface QueueRepository {
    suspend fun bookSlot(slotId: String): Resource<List<QueueSlot>>
    suspend fun startMachine(): Resource<Boolean>
    suspend fun getMachineQueue(machineId: String): Resource<List<QueueSlot>>
    suspend fun checkOutQueue(): Resource<List<QueueSlot>>
}