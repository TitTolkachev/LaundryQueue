package com.example.washingmachine.data.local.prefs.enter

import android.content.Context
import android.content.SharedPreferences
import com.example.washingmachine.data.local.prefs.model.FirstEnterStatus

private const val APP_PREFERENCES = "preferences_settings"
private const val ENTER_STATUS = "enter_status"

class EnterStorageImpl(context: Context) : EnterStorage {

    private val preferences: SharedPreferences =
        context.getSharedPreferences(
            APP_PREFERENCES,
            Context.MODE_PRIVATE
        )

    override fun getFirstEnterStatus(): FirstEnterStatus {

        val status = preferences.getBoolean(ENTER_STATUS, false)
        return if (!status)
            FirstEnterStatus.THIS_IS_FIRST_ENTER
        else FirstEnterStatus.NOT_FIRST_ENTER
    }

    override fun setFirstEnterPassed() {
        preferences.edit().putBoolean(ENTER_STATUS, true).apply()
    }
}