package com.example.washingmachine.domain.repository

import com.example.washingmachine.domain.model.StudentEditRequestBody
import com.example.washingmachine.domain.model.StudentInfo
import com.example.washingmachine.domain.util.Resource

interface StudentProfileRepository {

    suspend fun getInfo(): Resource<StudentInfo>
    suspend fun edit(body: StudentEditRequestBody): Resource<StudentInfo>
}