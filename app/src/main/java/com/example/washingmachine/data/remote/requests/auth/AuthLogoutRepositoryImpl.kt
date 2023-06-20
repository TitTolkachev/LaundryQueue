package com.example.washingmachine.data.remote.requests.auth

import com.example.washingmachine.data.remote.AuthNetwork
import com.example.washingmachine.data.remote.dto.LogoutRequestDto
import com.example.washingmachine.domain.repository.AuthLogoutRepository
import com.example.washingmachine.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthLogoutRepositoryImpl(authNetwork: AuthNetwork) : AuthLogoutRepository {

    private val authApi = authNetwork.getAuthApi()

    override suspend fun logout(refreshToken: String): Resource<Boolean> {
        var result: Resource<Boolean>

        withContext(Dispatchers.IO) {
            result = try {
                val request = authApi.logout(LogoutRequestDto(refreshToken))

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