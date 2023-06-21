package com.example.washingmachine.data.local.prefs.studentid

interface StudentIdStorage {
    fun saveId(id: String)

    fun getId(): String?
}