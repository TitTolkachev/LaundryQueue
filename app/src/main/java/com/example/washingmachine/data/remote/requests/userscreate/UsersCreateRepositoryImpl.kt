package com.example.washingmachine.data.remote.requests.userscreate

import com.example.washingmachine.data.remote.AuthNetwork
import com.example.washingmachine.data.remote.dto.CreateEmployeeDto
import com.example.washingmachine.domain.model.CreateEmployee
import com.example.washingmachine.domain.repository.UsersCreateRepository
import com.example.washingmachine.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsersCreateRepositoryImpl(authNetwork: AuthNetwork) : UsersCreateRepository {

    private val api = authNetwork.getUsersCreateApi()

    override suspend fun createStudent(email: String): Resource<Boolean> {
        var result: Resource<Boolean>

        withContext(Dispatchers.IO) {
            result = try {
                val request = api.createStudent(email)

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

    override suspend fun createEmployee(body: CreateEmployee): Resource<Boolean> {
        var result: Resource<Boolean>

        withContext(Dispatchers.IO) {
            result = try {
                val request = api.createEmployee(
                    CreateEmployeeDto(
                        body.email,
                        body.name,
                        body.surname
                    )
                )

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