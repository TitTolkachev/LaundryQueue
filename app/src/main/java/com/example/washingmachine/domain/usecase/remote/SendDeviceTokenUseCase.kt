package com.example.washingmachine.domain.usecase.remote

import com.example.washingmachine.domain.model.DeviceToken
import com.example.washingmachine.domain.repository.DeviceTokenRepository
import com.example.washingmachine.domain.util.Resource

class SendDeviceTokenUseCase(private val repository: DeviceTokenRepository) {

    suspend fun execute(token: DeviceToken): Resource<Boolean> {
        return repository.sendToken(token)
    }
}