package com.example.washingmachine.domain.repository

import com.example.washingmachine.domain.model.AdminEditRequestBody
import com.example.washingmachine.domain.model.AdminInfo
import com.example.washingmachine.domain.util.Resource

interface AdminProfileRepository {

    suspend fun getInfo(): Resource<AdminInfo>
    suspend fun edit(body: AdminEditRequestBody): Resource<AdminInfo>
}