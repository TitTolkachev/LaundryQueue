package com.example.washingmachine.data.remote.requests.auth

import com.example.washingmachine.data.remote.AuthNetwork
import com.example.washingmachine.data.remote.Network
import com.example.washingmachine.data.remote.dto.LoginRequestDto
import com.example.washingmachine.data.remote.dto.LogoutRequestDto
import com.example.washingmachine.data.remote.dto.RefreshRequestDto
import com.example.washingmachine.domain.model.Roles
import com.example.washingmachine.domain.model.Token
import com.example.washingmachine.domain.repository.AuthRepository
import com.example.washingmachine.domain.usecase.group.AuthNetworkUseCases
import com.example.washingmachine.domain.util.Resource

class AuthRepositoryImpl(useCases: AuthNetworkUseCases) : AuthRepository {

    private val api = Network.getAuthApi()
    private val authApi = AuthNetwork.getAuthApi(useCases = useCases)

    override suspend fun logout(refreshToken: String): Resource<Boolean> {
        return try {
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

    override suspend fun login(email: String, password: String): Resource<Token> {
        return try {
            val request = api.login(LoginRequestDto(email, password))

            if (request.isSuccessful) {
                Resource.Success(
                    Token(
                        request.body()?.accessToken ?: "",
                        request.body()?.refreshToken ?: "",
                        getRole(request.body()?.role ?: "")
                    )
                )
            } else {
                Resource.NetworkError(request.message())
            }
        } catch (e: Exception) {
            Resource.Exception(e)
        }
    }

    override suspend fun refresh(refreshToken: String): Resource<Token> {
        return try {
            val request = api.refresh(RefreshRequestDto(refreshToken))

            if (request.isSuccessful) {
                Resource.Success(
                    Token(
                        request.body()?.accessToken ?: "",
                        request.body()?.refreshToken ?: "",
                        getRole(request.body()?.role ?: "")
                    )
                )
            } else {
                Resource.NetworkError(request.message())
            }
        } catch (e: Exception) {
            Resource.Exception(e)
        }
    }

    private fun getRole(role: String): Roles {
        if (role == Roles.ROLE_EMPLOYEE.name)
            return Roles.ROLE_EMPLOYEE
        if (role == Roles.ROLE_ADMIN.name)
            return Roles.ROLE_ADMIN
        return Roles.ROLE_STUDENT
    }
}