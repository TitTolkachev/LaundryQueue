package com.example.washingmachine.domain.usecase.remote

import com.example.washingmachine.domain.model.AdminInfo
import com.example.washingmachine.domain.repository.AdminProfileRepository
import com.example.washingmachine.domain.util.Resource

class GetAdminProfileUseCase(private val repository: AdminProfileRepository) {

    suspend fun execute(): Resource<AdminInfo> {
        return repository.getInfo()
    }
}