package com.example.washingmachine.domain.repository

import com.example.washingmachine.domain.model.Token

interface TokenRepository {

    fun getToken(): Token?

    fun saveToken(token: Token)
}