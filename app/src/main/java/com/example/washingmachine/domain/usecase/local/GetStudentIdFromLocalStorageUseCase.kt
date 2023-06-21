package com.example.washingmachine.domain.usecase.local

import com.example.washingmachine.domain.repository.StudentIdRepository

class GetStudentIdFromLocalStorageUseCase(private val studentIdRepository: StudentIdRepository) {

    fun execute(): String? {
        return studentIdRepository.getId()
    }
}