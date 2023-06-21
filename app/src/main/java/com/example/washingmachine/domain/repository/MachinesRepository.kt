package com.example.washingmachine.domain.repository

import com.example.washingmachine.domain.model.ChangeMachineStatusRequest
import com.example.washingmachine.domain.model.CreateNewMachineRequest
import com.example.washingmachine.domain.model.Machine
import com.example.washingmachine.domain.util.Resource

interface MachinesRepository {
    suspend fun changeMachineStatus(body: ChangeMachineStatusRequest): Resource<Machine>
    suspend fun createMachine(body: CreateNewMachineRequest): Resource<Machine>
    suspend fun getMachines(dormitoryId: String): Resource<List<Machine>>
    suspend fun deleteMachine(machineId: String): Resource<Boolean>
}