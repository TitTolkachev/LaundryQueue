package com.example.washingmachine.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.washingmachine.domain.model.DeviceToken
import com.example.washingmachine.domain.model.Roles
import com.example.washingmachine.domain.usecase.local.GetTokenFromLocalStorageUseCase
import com.example.washingmachine.domain.usecase.remote.SendDeviceTokenUseCase
import com.example.washingmachine.domain.util.Resource
import kotlinx.coroutines.launch

class MainViewModel(
    private val sendDeviceTokenUseCase: SendDeviceTokenUseCase,
    private val getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase
) : ViewModel() {

    fun getUserRole(): Roles? {
        return getTokenFromLocalStorageUseCase.execute()?.role
    }

    fun sendDeviceToken(token: String) {
        viewModelScope.launch {
            when (sendDeviceTokenUseCase.execute(DeviceToken(token))) {
                is Resource.Success -> {}
                else -> {}
            }
        }
    }
}