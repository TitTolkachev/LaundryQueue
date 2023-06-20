package com.example.washingmachine.domain.usecase.local

import com.example.washingmachine.data.local.prefs.token.TokenStorage
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging

class ClearLocalStorageUseCase(private val tokenStorage: TokenStorage) {

    fun execute() {
        tokenStorage.clearToken()
        Firebase.messaging.deleteToken()
    }
}