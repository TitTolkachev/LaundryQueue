package com.example.washingmachine.domain.usecase.remote

import com.example.washingmachine.domain.model.AdminEditRequestBody
import com.example.washingmachine.domain.model.AdminInfo
import com.example.washingmachine.domain.repository.AdminProfileRepository
import com.example.washingmachine.domain.util.Resource

class EditAdminProfileUseCase(private val repository: AdminProfileRepository) {

    suspend fun execute(body: AdminEditRequestBody): Resource<AdminInfo> {
        return repository.edit(body)
    }
}