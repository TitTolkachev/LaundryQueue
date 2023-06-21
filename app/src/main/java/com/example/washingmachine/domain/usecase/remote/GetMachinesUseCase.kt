package com.example.washingmachine.domain.usecase.remote

import com.example.washingmachine.domain.model.Machine
import com.example.washingmachine.domain.repository.MachinesRepository
import com.example.washingmachine.domain.util.Resource

class GetMachinesUseCase(private val repository: MachinesRepository) {

    suspend fun execute(dormitoryId: String): Resource<List<Machine>> {
        return repository.getMachines(dormitoryId)
    }
}