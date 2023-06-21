package com.example.washingmachine.domain.usecase.remote

import com.example.washingmachine.domain.repository.MachinesRepository
import com.example.washingmachine.domain.util.Resource

class DeleteMachineUseCase(private val repository: MachinesRepository) {

    suspend fun execute(machineId: String): Resource<Boolean> {
        return repository.deleteMachine(machineId)
    }
}