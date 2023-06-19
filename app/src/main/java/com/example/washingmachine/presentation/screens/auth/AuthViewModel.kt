package com.example.washingmachine.presentation.screens.auth

import androidx.lifecycle.ViewModel
import com.example.washingmachine.domain.usecase.local.SetFirstEnterPassedUseCase

class AuthViewModel(
    setFirstEnterPassedUseCase: SetFirstEnterPassedUseCase
) : ViewModel() {

    init {
        setFirstEnterPassedUseCase.execute()
    }
}