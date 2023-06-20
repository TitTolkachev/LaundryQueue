package com.example.washingmachine.domain.usecase.group

import com.example.washingmachine.domain.usecase.local.GetTokenFromLocalStorageUseCase
import com.example.washingmachine.domain.usecase.local.SaveTokenToLocalStorageUseCase
import com.example.washingmachine.domain.usecase.remote.RefreshTokenUseCase

data class AuthNetworkUseCases(
    val getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase,
    val saveTokenToLocalStorageUseCase: SaveTokenToLocalStorageUseCase,
    val refreshTokenUseCase: RefreshTokenUseCase
)
