package com.example.washingmachine.domain.usecase.remote

import com.example.washingmachine.domain.repository.QueueRepository
import com.example.washingmachine.domain.util.Resource

class StartMachineUseCase(private val repository: QueueRepository) {

    suspend fun execute(): Resource<Boolean> {
        return repository.startMachine()
    }
}
