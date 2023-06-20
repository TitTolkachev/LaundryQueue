package com.example.washingmachine.presentation.screens.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.washingmachine.domain.model.DeviceToken
import com.example.washingmachine.domain.usecase.remote.SendDeviceTokenUseCase
import com.example.washingmachine.domain.util.Resource
import kotlinx.coroutines.launch

class AdminViewModel(
    private val sendDeviceTokenUseCase: SendDeviceTokenUseCase
) : ViewModel() {

    fun sendDeviceToken(token: String) {
        viewModelScope.launch {
            when (sendDeviceTokenUseCase.execute(DeviceToken(token))) {
                is Resource.Success -> {}
                else -> {}
            }
        }
    }
}