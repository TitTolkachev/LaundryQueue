package com.example.washingmachine.domain.usecase.remote

import com.example.washingmachine.domain.model.AdminInfo
import com.example.washingmachine.domain.repository.BalanceRepository
import com.example.washingmachine.domain.util.Resource

class TakeOutBalanceUseCase(private val repository: BalanceRepository) {
    suspend fun execute(delta: Int): Resource<AdminInfo> {
        return repository.takeOutBalance(delta)
    }
}