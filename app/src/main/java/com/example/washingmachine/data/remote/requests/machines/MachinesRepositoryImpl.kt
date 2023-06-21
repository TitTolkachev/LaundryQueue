package com.example.washingmachine.data.remote.requests.machines

import com.example.washingmachine.data.remote.AuthNetwork
import com.example.washingmachine.data.remote.dto.ChangeMachineStatusDto
import com.example.washingmachine.data.remote.dto.CreateNewMachineDto
import com.example.washingmachine.domain.model.ChangeMachineStatusRequest
import com.example.washingmachine.domain.model.CreateNewMachineRequest
import com.example.washingmachine.domain.model.Machine
import com.example.washingmachine.domain.model.QueueSlot
import com.example.washingmachine.domain.repository.MachinesRepository
import com.example.washingmachine.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MachinesRepositoryImpl(authNetwork: AuthNetwork) : MachinesRepository {

    private val api = authNetwork.getMachinesApi()

    override suspend fun changeMachineStatus(body: ChangeMachineStatusRequest): Resource<Machine> {
        var result: Resource<Machine>

        withContext(Dispatchers.IO) {
            result = try {
                val request = api.changeMachineStatus(
                    ChangeMachineStatusDto(
                        body.machineId,
                        body.status
                    )
                )

                if (request.isSuccessful) {
                    Resource.Success(
                        Machine(
                            request.body()?.id,
                            request.body()?.name,
                            request.body()?.startTime,
                            request.body()?.type,
                            request.body()?.status,
                            request.body()?.queueSlots?.map {
                                QueueSlot(
                                    it.id,
                                    it.number,
                                    it.personId,
                                    it.status
                                )
                            },
                            request.body()?.location
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

    override suspend fun createMachine(body: CreateNewMachineRequest): Resource<Machine> {
        var result: Resource<Machine>

        withContext(Dispatchers.IO) {
            result = try {
                val request = api.createMachine(
                    CreateNewMachineDto(
                        body.type,
                        body.name,
                        body.machineStatus,
                        body.ip,
                        body.location,
                    )
                )

                if (request.isSuccessful) {
                    Resource.Success(
                        Machine(
                            request.body()?.id,
                            request.body()?.name,
                            request.body()?.startTime,
                            request.body()?.type,
                            request.body()?.status,
                            request.body()?.queueSlots?.map {
                                QueueSlot(
                                    it.id,
                                    it.number,
                                    it.personId,
                                    it.status
                                )
                            },
                            request.body()?.location
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

    override suspend fun getMachines(dormitoryId: String): Resource<List<Machine>> {
        var result: Resource<List<Machine>>

        withContext(Dispatchers.IO) {
            result = try {
                val request = api.getMachines(dormitoryId)

                if (request.isSuccessful) {
                    Resource.Success(request.body()?.map {
                        Machine(
                            it.id,
                            it.name,
                            it.startTime,
                            it.type,
                            it.status,
                            it.queueSlots?.map { slot ->
                                QueueSlot(
                                    slot.id,
                                    slot.number,
                                    slot.personId,
                                    slot.status
                                )
                            },
                            it.location
                        )
                    }

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

    override suspend fun deleteMachine(machineId: String): Resource<Boolean> {
        var result: Resource<Boolean>

        withContext(Dispatchers.IO) {
            result = try {
                val request = api.deleteMachine(machineId)

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