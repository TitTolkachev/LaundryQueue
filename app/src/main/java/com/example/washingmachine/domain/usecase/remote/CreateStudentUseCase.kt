package com.example.washingmachine.domain.usecase.remote

import com.example.washingmachine.domain.repository.UsersCreateRepository
import com.example.washingmachine.domain.util.Resource

class CreateStudentUseCase(private val repository: UsersCreateRepository) {

    suspend fun execute(email: String): Resource<Boolean> {
        return repository.createStudent(email)
    }
}