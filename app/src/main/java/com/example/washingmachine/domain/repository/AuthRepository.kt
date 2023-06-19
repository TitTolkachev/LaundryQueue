package com.example.washingmachine.domain.repository

import com.example.washingmachine.domain.model.Token
import com.example.washingmachine.domain.util.Resource

interface AuthRepository {
    suspend fun login(email: String, password: String): Resource<Token>
    suspend fun refresh(refreshToken: String): Resource<Token>
}