package com.example.washingmachine.domain.usecase.remote

import com.example.washingmachine.domain.model.CreateEmployee
import com.example.washingmachine.domain.repository.UsersCreateRepository
import com.example.washingmachine.domain.util.Resource

class CreateEmployeeUseCase(private val repository: UsersCreateRepository) {

    suspend fun execute(body: CreateEmployee): Resource<Boolean> {
        return repository.createEmployee(body)
    }
}