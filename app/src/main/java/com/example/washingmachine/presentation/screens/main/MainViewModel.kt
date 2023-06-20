package com.example.washingmachine.presentation.screens.main

import androidx.lifecycle.ViewModel
import com.example.washingmachine.domain.model.Roles
import com.example.washingmachine.domain.usecase.local.GetTokenFromLocalStorageUseCase

class MainViewModel(
    private val getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase
) : ViewModel() {

    fun getUserRole(): Roles? {
        return getTokenFromLocalStorageUseCase.execute()?.role
    }
}