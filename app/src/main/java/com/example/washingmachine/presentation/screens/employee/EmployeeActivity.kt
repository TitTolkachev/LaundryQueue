package com.example.washingmachine.presentation.screens.employee

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.washingmachine.databinding.ActivityEmployeeBinding
import com.example.washingmachine.notification.MyFirebaseMessagingService
import org.koin.androidx.viewmodel.ext.android.getViewModel

class EmployeeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeeBinding
    private lateinit var viewModel: EmployeeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = getViewModel()

        checkDeviceToken()
    }

    private fun checkDeviceToken() {
        val preferences = baseContext.getSharedPreferences(
            MyFirebaseMessagingService.APP_PREFERENCES,
            Context.MODE_PRIVATE
        )
        val token = preferences.getString(MyFirebaseMessagingService.DEVICE_TOKEN, "")
        if (!token.isNullOrBlank()) {
            viewModel.sendDeviceToken(token)
            preferences.edit().remove(MyFirebaseMessagingService.DEVICE_TOKEN).apply()
        }
    }
}