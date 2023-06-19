package com.example.washingmachine.domain.usecase.local

import com.example.washingmachine.domain.model.Token
import com.example.washingmachine.domain.repository.TokenRepository

class GetTokenFromLocalStorageUseCase(private val repository: TokenRepository) {

    fun execute(): Token? {
        return repository.getToken()
    }
}