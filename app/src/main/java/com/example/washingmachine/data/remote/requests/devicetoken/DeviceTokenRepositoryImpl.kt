package com.example.washingmachine.data.remote.requests.devicetoken

import com.example.washingmachine.data.remote.AuthNetwork
import com.example.washingmachine.data.remote.dto.DeviceTokenRequestDto
import com.example.washingmachine.domain.model.DeviceToken
import com.example.washingmachine.domain.repository.DeviceTokenRepository
import com.example.washingmachine.domain.usecase.group.AuthNetworkUseCases
import com.example.washingmachine.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeviceTokenRepositoryImpl(useCases: AuthNetworkUseCases) : DeviceTokenRepository {

    private val api = AuthNetwork.getDeviceTokenApi(useCases)

    override suspend fun sendToken(token: DeviceToken): Resource<Boolean> {
        var result: Resource<Boolean>

        withContext(Dispatchers.IO) {
            result = try {
                val request = api.sendToken(DeviceTokenRequestDto(token.token))

                if (request.isSuccessful) {
                    Resource.Success(true)
                } else {
                    Resource.NetworkError(request.message())
                }

            } catch (e: Exception) {
                Resource.Exception(e)
            }
        }

        return result
    }
}