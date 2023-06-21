package com.example.washingmachine.data.remote.requests.queue

import com.example.washingmachine.data.remote.dto.QueueSlotDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface QueueApi {

    @POST("api/queue/slots/{slotId}")
    suspend fun bookSlot(@Path("slotId") slotId: String): Response<List<QueueSlotDto>>

    @POST("api/laundry/start")
    suspend fun startMachine(): Response<ResponseBody>

    @POST("api/queue/{machineId}")
    suspend fun getMachineQueue(@Path("machineId") machineId: String): Response<List<QueueSlotDto>>

    @DELETE("api/queue")
    suspend fun checkOutQueue(): Response<List<QueueSlotDto>>
}