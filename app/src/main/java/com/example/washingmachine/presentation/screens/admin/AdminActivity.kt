package com.example.washingmachine.presentation.screens.admin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.washingmachine.databinding.ActivityAdminBinding
import com.example.washingmachine.notification.MyFirebaseMessagingService
import com.example.washingmachine.presentation.screens.adminmachines.AdminMachinesActivity
import com.example.washingmachine.presentation.screens.adminprofile.AdminProfileActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class AdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminBinding
    private lateinit var viewModel: AdminViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = getViewModel()

        binding.button3.setOnClickListener {
            val intent = Intent(this, AdminProfileActivity::class.java)
            startActivity(intent)
        }

        binding.button4.setOnClickListener {
            val intent = Intent(this, AdminMachinesActivity::class.java)
            startActivity(intent)
        }

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