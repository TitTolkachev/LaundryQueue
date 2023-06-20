package com.example.washingmachine.presentation.screens.adminprofile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.washingmachine.databinding.ActivityAdminProfileBinding
import com.example.washingmachine.presentation.screens.auth.AuthActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class AdminProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminProfileBinding
    private lateinit var viewModel: AdminProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = getViewModel()
        viewModel.refreshData()


        binding.adminLogoutButton.setOnClickListener {
            viewModel.logout()
        }

        viewModel.getLiveDataForAuthNavigation().observe(this) {
            if (it){
                val intent = Intent(this, AuthActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}