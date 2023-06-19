package com.example.washingmachine.domain.usecase.remote

import com.example.washingmachine.domain.model.Token
import com.example.washingmachine.domain.repository.AuthRepository
import com.example.washingmachine.domain.util.Resource

class RefreshTokenUseCase(private val repository: AuthRepository) {

    suspend fun execute(refreshToken: String): Resource<Token> {
        return repository.refresh(refreshToken)
    }
}