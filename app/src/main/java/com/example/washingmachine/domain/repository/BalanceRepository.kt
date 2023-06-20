package com.example.washingmachine.domain.repository

import com.example.washingmachine.domain.model.AdminInfo
import com.example.washingmachine.domain.model.StudentEditRequestBody
import com.example.washingmachine.domain.model.StudentInfo
import com.example.washingmachine.domain.util.Resource

interface BalanceRepository {

    suspend fun topUpBalance(delta: Int): Resource<StudentInfo>

    suspend fun takeOutBalance(delta: Int): Resource<AdminInfo>
}