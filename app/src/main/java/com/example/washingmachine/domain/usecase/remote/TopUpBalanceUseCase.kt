package com.example.washingmachine.domain.usecase.remote

import com.example.washingmachine.domain.model.StudentInfo
import com.example.washingmachine.domain.repository.BalanceRepository
import com.example.washingmachine.domain.util.Resource

class TopUpBalanceUseCase(private val repository: BalanceRepository) {
    suspend fun execute(delta: Int): Resource<StudentInfo> {
        return repository.topUpBalance(delta)
    }
}