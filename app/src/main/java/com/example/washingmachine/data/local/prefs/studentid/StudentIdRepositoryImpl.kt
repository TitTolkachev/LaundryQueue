package com.example.washingmachine.data.local.prefs.studentid

import com.example.washingmachine.domain.repository.StudentIdRepository

class StudentIdRepositoryImpl(private val studentIdStorage: StudentIdStorage) : StudentIdRepository {
    override fun getId(): String? {
        return studentIdStorage.getId()
    }

    override fun saveId(id: String) {
        return studentIdStorage.saveId(id)
    }

}