package com.example.washingmachine.data.remote.requests.dormitory

import com.example.washingmachine.data.remote.Network
import com.example.washingmachine.data.remote.dto.DormitoryDto
import com.example.washingmachine.domain.repository.DormitoryRepository
import com.example.washingmachine.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DormitoryRepositoryImpl(private val network: Network) : DormitoryRepository {

    private val api = network.getDormitoryApi()

    override suspend fun getDormitories(): Resource<List<DormitoryDto>> {
        var result: Resource<List<DormitoryDto>>

        withContext(Dispatchers.IO) {
            result = try {
                val request = api.getDormitories()

                if (request.isSuccessful) {
                    Resource.Success(request.body())
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