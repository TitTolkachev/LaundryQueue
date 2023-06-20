package com.example.washingmachine.domain.repository

import com.example.washingmachine.data.remote.dto.DormitoryDto
import com.example.washingmachine.domain.util.Resource

interface DormitoryRepository {

    suspend fun getDormitories(): Resource<List<DormitoryDto>>
}