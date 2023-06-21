package com.example.washingmachine.domain.usecase.remote

import com.example.washingmachine.domain.model.ChangeMachineStatusRequest
import com.example.washingmachine.domain.model.Machine
import com.example.washingmachine.domain.repository.MachinesRepository
import com.example.washingmachine.domain.util.Resource

class ChangeMachineStatusUseCase(private val repository: MachinesRepository) {

    suspend fun execute(body: ChangeMachineStatusRequest): Resource<Machine> {
        return repository.changeMachineStatus(body)
    }
}