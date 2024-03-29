package com.example.washingmachine.domain.usecase.local

import com.example.washingmachine.domain.repository.EnterRepository

class SetFirstEnterPassedUseCase(private val repository: EnterRepository) {

    fun execute() {
        repository.setFirstEnterPassed()
    }
}