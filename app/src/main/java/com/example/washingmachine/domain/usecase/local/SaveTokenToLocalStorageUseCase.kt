package com.example.washingmachine.domain.usecase.local

import com.example.washingmachine.domain.model.Token
import com.example.washingmachine.domain.repository.TokenRepository

class SaveTokenToLocalStorageUseCase(private val repository: TokenRepository) {

    fun execute(token: Token) {
        return repository.saveToken(token = token)
    }
}