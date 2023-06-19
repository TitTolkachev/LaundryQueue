package com.example.washingmachine.domain.repository

import com.example.washingmachine.domain.util.Resource

interface AuthLogoutRepository {

    suspend fun logout(refreshToken: String): Resource<Boolean>
}