package com.example.washingmachine.domain.usecase.remote

import com.example.washingmachine.domain.repository.DormitoryRepository

class GetDormitoriesUseCase(private val dormitoryRepository: DormitoryRepository) {
    suspend fun execute() = dormitoryRepository.getDormitories()
}