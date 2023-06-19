package com.example.washingmachine.domain.usecase.remote

import com.example.washingmachine.domain.model.StudentEditRequestBody
import com.example.washingmachine.domain.model.StudentInfo
import com.example.washingmachine.domain.repository.StudentProfileRepository
import com.example.washingmachine.domain.util.Resource

class EditStudentProfileUseCase(private val repository: StudentProfileRepository) {

    suspend fun execute(body: StudentEditRequestBody): Resource<StudentInfo> {
        return repository.edit(body)
    }
}