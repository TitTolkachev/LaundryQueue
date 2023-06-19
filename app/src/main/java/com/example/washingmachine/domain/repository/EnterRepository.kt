package com.example.washingmachine.domain.repository

import com.example.washingmachine.domain.model.FirstEnterStatus

interface EnterRepository {

    fun getFirstEnterStatus(): FirstEnterStatus

    fun setFirstEnterPassed()
}