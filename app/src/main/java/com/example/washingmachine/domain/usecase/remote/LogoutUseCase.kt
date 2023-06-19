package com.example.washingmachine.domain.usecase.remote

import com.example.washingmachine.domain.repository.AuthLogoutRepository
import com.example.washingmachine.domain.util.Resource

class LogoutUseCase(private val repository: AuthLogoutRepository) {

    suspend fun execute(refreshToken: String): Resource<Boolean> {
        return repository.logout(refreshToken = refreshToken)
    }
}