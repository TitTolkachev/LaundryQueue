package com.example.washingmachine.presentation.screens.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.washingmachine.data.local.prefs.enter.EnterRepositoryImpl
import com.example.washingmachine.data.local.prefs.enter.EnterStorageImpl
import com.example.washingmachine.domain.usecase.SetFirstEnterPassedUseCase

class AuthViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val enterRepository = EnterRepositoryImpl(EnterStorageImpl(context))

    private val setFirstEnterPassedUseCase = SetFirstEnterPassedUseCase(enterRepository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(
            setFirstEnterPassedUseCase
        ) as T
    }
}