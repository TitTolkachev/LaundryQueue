package com.example.washingmachine.data.local.prefs.token

import com.example.washingmachine.data.local.prefs.model.TokenModel

interface TokenStorage {

    fun getToken(): TokenModel?

    fun saveToken(token: TokenModel)
}