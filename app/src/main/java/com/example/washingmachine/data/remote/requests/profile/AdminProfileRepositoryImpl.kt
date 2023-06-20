package com.example.washingmachine.data.remote.requests.profile

import com.example.washingmachine.data.remote.AuthNetwork
import com.example.washingmachine.data.remote.dto.AdminProfileRequestDto
import com.example.washingmachine.domain.model.AdminEditRequestBody
import com.example.washingmachine.domain.model.AdminInfo
import com.example.washingmachine.domain.repository.AdminProfileRepository
import com.example.washingmachine.domain.usecase.group.AuthNetworkUseCases
import com.example.washingmachine.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AdminProfileRepositoryImpl(useCases: AuthNetworkUseCases) : AdminProfileRepository {

    private val api = AuthNetwork.getAdminProfileApi(useCases)

    override suspend fun getInfo(): Resource<AdminInfo> {
        var result: Resource<AdminInfo>

        withContext(Dispatchers.IO) {
            result = try {
                val request = api.getInfo()
                if (request.isSuccessful) {
                    Resource.Success(
                        AdminInfo(
                            request.body()?.id,
                            request.body()?.email,
                            request.body()?.name,
                            request.body()?.surname,
                            request.body()?.money,
                            request.body()?.role,
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

    override suspend fun edit(body: AdminEditRequestBody): Resource<AdminInfo> {
        var result: Resource<AdminInfo>

        withContext(Dispatchers.IO) {
            result = try {
                val request = api.edit(
                    AdminProfileRequestDto(
                        body.email,
                        body.name,
                        body.surname
                    )
                )
                if (request.isSuccessful) {
                    Resource.Success(
                        AdminInfo(
                            request.body()?.id,
                            request.body()?.email,
                            request.body()?.name,
                            request.body()?.surname,
                            request.body()?.money,
                            request.body()?.role,
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
}