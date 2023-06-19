package com.example.washingmachine.presentation.screens.launch

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.washingmachine.data.local.prefs.enter.EnterRepositoryImpl
import com.example.washingmachine.data.local.prefs.enter.EnterStorageImpl
import com.example.washingmachine.data.local.prefs.token.TokenRepositoryImpl
import com.example.washingmachine.data.local.prefs.token.TokenStorageImpl
import com.example.washingmachine.domain.usecase.local.GetFirstEnterStatusUseCase
import com.example.washingmachine.domain.usecase.local.GetTokenFromLocalStorageUseCase

class LaunchViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val tokenRepository = TokenRepositoryImpl(TokenStorageImpl(context))

    private val getTokenFromLocalStorageUseCase = GetTokenFromLocalStorageUseCase(tokenRepository)

    private val enterRepository = EnterRepositoryImpl(EnterStorageImpl(context))

    private val getFirstEnterStatusUseCase = GetFirstEnterStatusUseCase(enterRepository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LaunchViewModel(
            getTokenFromLocalStorageUseCase,
            getFirstEnterStatusUseCase
        ) as T
    }
}