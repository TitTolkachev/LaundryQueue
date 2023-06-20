package com.example.washingmachine.presentation.screens.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.washingmachine.data.local.prefs.enter.EnterRepositoryImpl
import com.example.washingmachine.data.local.prefs.enter.EnterStorageImpl
import com.example.washingmachine.data.local.prefs.token.TokenRepositoryImpl
import com.example.washingmachine.data.local.prefs.token.TokenStorageImpl
import com.example.washingmachine.data.remote.requests.auth.AuthRepositoryImpl
import com.example.washingmachine.domain.usecase.local.SaveTokenToLocalStorageUseCase
import com.example.washingmachine.domain.usecase.local.SetFirstEnterPassedUseCase
import com.example.washingmachine.domain.usecase.remote.SignInUseCase

//class AuthViewModelFactory(context: Context) : ViewModelProvider.Factory {
//
//    private val signInUseCase = SignInUseCase(AuthRepositoryImpl())
//    private val saveTokenToLocalStorageUseCase =
//        SaveTokenToLocalStorageUseCase(TokenRepositoryImpl(TokenStorageImpl(context)))
//
//    private val enterRepository = EnterRepositoryImpl(EnterStorageImpl(context))
//    private val setFirstEnterPassedUseCase = SetFirstEnterPassedUseCase(enterRepository)
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return AuthViewModel(
//            signInUseCase,
//            saveTokenToLocalStorageUseCase,
//            setFirstEnterPassedUseCase
//        ) as T
//    }
//}