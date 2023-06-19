package com.example.washingmachine.domain.usecase

import com.example.washingmachine.domain.model.FirstEnterStatus
import com.example.washingmachine.domain.repository.EnterRepository

class GetFirstEnterStatusUseCase(private val repository: EnterRepository) {

    fun execute(): FirstEnterStatus {
        return repository.getFirstEnterStatus()
    }
}