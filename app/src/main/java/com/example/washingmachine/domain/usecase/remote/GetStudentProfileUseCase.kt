package com.example.washingmachine.domain.usecase.remote

import com.example.washingmachine.domain.model.StudentInfo
import com.example.washingmachine.domain.repository.StudentProfileRepository
import com.example.washingmachine.domain.util.Resource

class GetStudentProfileUseCase(private val repository: StudentProfileRepository) {

    suspend fun execute(): Resource<StudentInfo> {
        return repository.getInfo()
    }
}