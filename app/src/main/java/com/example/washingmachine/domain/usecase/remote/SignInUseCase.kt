package com.example.washingmachine.domain.usecase.remote

import com.example.washingmachine.domain.model.Token
import com.example.washingmachine.domain.repository.AuthRepository
import com.example.washingmachine.domain.util.Resource

class SignInUseCase(private val repository: AuthRepository) {

    suspend fun execute(email: String, password: String): Resource<Token> {
        return repository.login(email = email, password = password)
    }
}