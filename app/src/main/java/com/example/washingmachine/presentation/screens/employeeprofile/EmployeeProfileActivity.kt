package com.example.washingmachine.presentation.screens.employeeprofile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.washingmachine.databinding.ActivityAdminProfileBinding
import com.example.washingmachine.databinding.ActivityEmployeeProfileBinding
import com.example.washingmachine.presentation.screens.adminprofile.AdminProfileViewModel
import com.example.washingmachine.presentation.screens.auth.AuthActivity
import com.example.washingmachine.presentation.screens.editprofile.person.EditPersonProfileActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class EmployeeProfileActivity: AppCompatActivity() {

    private lateinit var binding: ActivityEmployeeProfileBinding
    private lateinit var viewModel: EmployeeProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = getViewModel()
        viewModel.refreshData()


        binding.employeeLogoutButton.setOnClickListener {
            viewModel.logout()
        }

        viewModel.getLiveDataForProfile().observe(this) {
            binding.employeeName.text = it.name
            binding.employeeSurname.text = it.surname
            binding.employeeEmail.text = it.email
            binding.employeeDormitory.text = it.dormitory?.number.toString() + " dormitory"
        }

        viewModel.getLiveDataForAuthNavigation().observe(this) {
            if (it) {
                val intent = Intent(this, AuthActivity::class.java)
                intent.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP
                            or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            or Intent.FLAG_ACTIVITY_NEW_TASK
                )
                startActivity(intent)
                finish()
            }
        }

        binding.editEmployeeButton.setOnClickListener {
            val intent = Intent(this, EditPersonProfileActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshData()
    }
}