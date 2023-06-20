package com.example.washingmachine.data.remote.requests.auth

import com.example.washingmachine.data.remote.Network
import com.example.washingmachine.data.remote.dto.LoginRequestDto
import com.example.washingmachine.data.remote.dto.RefreshRequestDto
import com.example.washingmachine.domain.model.Roles
import com.example.washingmachine.domain.model.Token
import com.example.washingmachine.domain.repository.AuthRepository
import com.example.washingmachine.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepositoryImpl : AuthRepository {

    private val api = Network.getAuthApi()

    override suspend fun login(email: String, password: String): Resource<Token> {
        var result: Resource<Token>

        withContext(Dispatchers.IO) {
            result = try {
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

        return result
    }

    override suspend fun refresh(refreshToken: String): Resource<Token> {
        var result: Resource<Token>

        withContext(Dispatchers.IO) {
            result = try {
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

        return result
    }

    private fun getRole(role: String): Roles {
        if (role == Roles.ROLE_EMPLOYEE.name)
            return Roles.ROLE_EMPLOYEE
        if (role == Roles.ROLE_ADMIN.name)
            return Roles.ROLE_ADMIN
        return Roles.ROLE_STUDENT
    }
}