package com.example.washingmachine.domain.usecase.remote

import com.example.washingmachine.domain.model.CreateNewMachineRequest
import com.example.washingmachine.domain.model.Machine
import com.example.washingmachine.domain.repository.MachinesRepository
import com.example.washingmachine.domain.util.Resource

class CreateMachineUseCase(private val repository: MachinesRepository) {

    suspend fun execute(body: CreateNewMachineRequest): Resource<Machine> {
        return repository.createMachine(body)
    }
}