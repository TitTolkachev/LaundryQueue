package com.example.washingmachine.data.local.prefs.enter

import com.example.washingmachine.data.local.prefs.model.FirstEnterStatus
import com.example.washingmachine.domain.repository.EnterRepository

class EnterRepositoryImpl(private val enterStorage: EnterStorage) : EnterRepository {

    override fun getFirstEnterStatus(): com.example.washingmachine.domain.model.FirstEnterStatus {
        val data = enterStorage.getFirstEnterStatus()
        return if (data == FirstEnterStatus.NOT_FIRST_ENTER)
            com.example.washingmachine.domain.model.FirstEnterStatus.NOT_FIRST_ENTER
        else
            com.example.washingmachine.domain.model.FirstEnterStatus.THIS_IS_FIRST_ENTER
    }

    override fun setFirstEnterPassed() {
        enterStorage.setFirstEnterPassed()
    }
}