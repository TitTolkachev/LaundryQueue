package com.example.washingmachine.domain.usecase.local

import com.example.washingmachine.domain.repository.StudentIdRepository

class SaveStudentIdToLocalStorageUseCase(private val studentIdRepository: StudentIdRepository) {

    fun execute(id: String) {
        studentIdRepository.saveId(id)
    }

}