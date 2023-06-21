package com.example.washingmachine.data.remote.requests.machines

import com.example.washingmachine.data.remote.dto.ChangeMachineStatusDto
import com.example.washingmachine.data.remote.dto.CreateNewMachineDto
import com.example.washingmachine.data.remote.dto.MachineDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MachinesApi {

    @PUT("api/machines")
    suspend fun changeMachineStatus(@Body body: ChangeMachineStatusDto): Response<MachineDto>

    @POST("api/machines")
    suspend fun createMachine(@Body body: CreateNewMachineDto): Response<MachineDto>

    @GET("api/machines/{dormitoryId}")
    suspend fun getMachines(@Path("dormitoryId") dormitoryId: String): Response<List<MachineDto>>

    @DELETE("api/machines/{machineId}")
    suspend fun deleteMachine(@Path("machineId") machineId: String): Response<ResponseBody>
}