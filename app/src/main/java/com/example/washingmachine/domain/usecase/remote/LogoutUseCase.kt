package com.example.washingmachine.domain.usecase.remote

import com.example.washingmachine.domain.repository.AuthRepository
import com.example.washingmachine.domain.util.Resource

class LogoutUseCase(private val repository: AuthRepository) {

    suspend fun execute(refreshToken: String): Resource<Boolean> {
        return repository.logout(refreshToken = refreshToken)
    }
}