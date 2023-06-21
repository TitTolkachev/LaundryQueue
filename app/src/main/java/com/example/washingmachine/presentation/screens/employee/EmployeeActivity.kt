package com.example.washingmachine.presentation.screens.employee

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.washingmachine.databinding.ActivityEmployeeBinding
import com.example.washingmachine.presentation.screens.addstudent.AddStudentActivity
import com.example.washingmachine.presentation.screens.adminmachines.AdminMachinesActivity
import com.example.washingmachine.presentation.screens.employeeprofile.EmployeeProfileActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class EmployeeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeeBinding
    private lateinit var viewModel: EmployeeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = getViewModel()

        binding.gotToAdminProfile.setOnClickListener {
            val intent = Intent(this, EmployeeProfileActivity::class.java)
            startActivity(intent)
        }

        binding.button4.setOnClickListener {
            val intent = Intent(this, AdminMachinesActivity::class.java)
            startActivity(intent)
        }

        binding.button5.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }
    }
}