package com.example.washingmachine.data.local.prefs.studentid

import android.content.Context
import android.content.SharedPreferences

private const val APP_PREFERENCES = "preferences_settings"
private const val STUDENT_ID = "student_id"

class StudentIdStorageImpl(context: Context): StudentIdStorage {

    private val preferences: SharedPreferences =
        context.getSharedPreferences(
            APP_PREFERENCES,
            Context.MODE_PRIVATE
        )

    override fun saveId(id: String) {
        preferences.edit().putString(STUDENT_ID, id).apply()
    }

    override fun getId(): String? {
        return preferences.getString(STUDENT_ID, null)
    }

}