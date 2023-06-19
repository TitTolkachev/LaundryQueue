package com.example.washingmachine.domain.repository

import com.example.washingmachine.domain.model.DeviceToken
import com.example.washingmachine.domain.util.Resource

interface DeviceTokenRepository {

    suspend fun sendToken(token: DeviceToken) : Resource<Boolean>
}