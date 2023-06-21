package com.example.washingmachine.domain.repository

import com.example.washingmachine.domain.model.Token

interface StudentIdRepository {
    fun getId(): String?

    fun saveId(id: String)
}