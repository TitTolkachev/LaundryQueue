package com.example.washingmachine.data.local.prefs.enter

import com.example.washingmachine.data.local.prefs.model.FirstEnterStatus

interface EnterStorage {

    fun getFirstEnterStatus(): FirstEnterStatus

    fun setFirstEnterPassed()
}