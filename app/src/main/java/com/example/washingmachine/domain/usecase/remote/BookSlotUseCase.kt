package com.example.washingmachine.domain.usecase.remote

import com.example.washingmachine.domain.model.QueueSlot
import com.example.washingmachine.domain.repository.QueueRepository
import com.example.washingmachine.domain.util.Resource

class BookSlotUseCase(private val repository: QueueRepository) {

    suspend fun execute(slotId: String): Resource<List<QueueSlot>> {
        return repository.bookSlot(slotId = slotId)
    }
}