package com.example.washingmachine.domain.repository

import com.example.washingmachine.data.remote.dto.PersonDto
import com.example.washingmachine.domain.model.AdminInfo
import com.example.washingmachine.domain.model.StudentEditRequestBody
import com.example.washingmachine.domain.model.StudentInfo
import com.example.washingmachine.domain.util.Resource

interface BalanceRepository {

    suspend fun topUpBalance(delta: Int): Resource<PersonDto>

    suspend fun takeOutBalance(delta: Int): Resource<PersonDto>
}