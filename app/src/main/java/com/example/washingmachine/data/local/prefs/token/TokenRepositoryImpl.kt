package com.example.washingmachine.data.local.prefs.token

import com.example.washingmachine.data.local.prefs.model.TokenModel
import com.example.washingmachine.domain.model.Token
import com.example.washingmachine.domain.repository.TokenRepository

class TokenRepositoryImpl(private val tokenStorage: TokenStorage) : TokenRepository {

    override fun getToken(): Token? {
        val data = tokenStorage.getToken()
        return if (data != null)
            Token(data.access, data.refresh)
        else null
    }

    override fun saveToken(token: Token) {
        tokenStorage.saveToken(TokenModel(access = token.access, refresh = token.refresh))
    }
}