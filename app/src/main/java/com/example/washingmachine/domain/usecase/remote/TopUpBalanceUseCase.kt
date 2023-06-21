package com.example.washingmachine.domain.usecase.remote

import com.example.washingmachine.data.remote.dto.PersonDto
import com.example.washingmachine.domain.model.StudentInfo
import com.example.washingmachine.domain.repository.BalanceRepository
import com.example.washingmachine.domain.util.Resource

class TopUpBalanceUseCase(private val repository: BalanceRepository) {
    suspend fun execute(delta: Int): Resource<PersonDto> {
        return repository.topUpBalance(delta)
    }
}