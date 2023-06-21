package com.example.washingmachine.domain.repository

import com.example.washingmachine.domain.model.CreateEmployee
import com.example.washingmachine.domain.util.Resource

interface UsersCreateRepository {

    suspend fun createStudent(email: String): Resource<Boolean>

    suspend fun createEmployee(body: CreateEmployee): Resource<Boolean>
}