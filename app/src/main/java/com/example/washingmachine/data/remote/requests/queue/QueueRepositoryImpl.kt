package com.example.washingmachine.data.remote.requests.queue

import com.example.washingmachine.data.remote.AuthNetwork
import com.example.washingmachine.domain.model.QueueSlot
import com.example.washingmachine.domain.repository.QueueRepository
import com.example.washingmachine.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QueueRepositoryImpl(authNetwork: AuthNetwork) : QueueRepository {

    private val api = authNetwork.getQueueApi()

    override suspend fun bookSlot(slotId: String): Resource<List<QueueSlot>> {
        var result: Resource<List<QueueSlot>>

        withContext(Dispatchers.IO) {
            result = try {
                val request = api.bookSlot(slotId)

                if (request.isSuccessful) {
                    Resource.Success(request.body()?.map {
                        QueueSlot(
                            it.id,
                            it.number,
                            it.personId,
                            it.status
                        )
                    })
                } else {
                    Resource.NetworkError(request.message())
                }

            } catch (e: Exception) {
                Resource.Exception(e)
            }
        }

        return result
    }

    override suspend fun startMachine(): Resource<Boolean> {
        var result: Resource<Boolean>

        withContext(Dispatchers.IO) {
            result = try {
                val request = api.startMachine()

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

    override suspend fun getMachineQueue(machineId: String): Resource<List<QueueSlot>> {
        var result: Resource<List<QueueSlot>>

        withContext(Dispatchers.IO) {
            result = try {
                val request = api.getMachineQueue(machineId)

                if (request.isSuccessful) {
                    Resource.Success(request.body()?.map {
                        QueueSlot(
                            it.id,
                            it.number,
                            it.personId,
                            it.status
                        )
                    })
                } else {
                    Resource.NetworkError(request.message())
                }

            } catch (e: Exception) {
                Resource.Exception(e)
            }
        }

        return result
    }

    override suspend fun checkOutQueue(): Resource<List<QueueSlot>> {
        var result: Resource<List<QueueSlot>>

        withContext(Dispatchers.IO) {
            result = try {
                val request = api.checkOutQueue()

                if (request.isSuccessful) {
                    Resource.Success(request.body()?.map {
                        QueueSlot(
                            it.id,
                            it.number,
                            it.personId,
                            it.status
                        )
                    })
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