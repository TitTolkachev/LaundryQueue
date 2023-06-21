package com.example.washingmachine.presentation.screens.admin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.washingmachine.databinding.ActivityAdminBinding
import com.example.washingmachine.presentation.screens.addstudent.AddStudentActivity
import com.example.washingmachine.presentation.screens.adminmachines.AdminMachinesActivity
import com.example.washingmachine.presentation.screens.adminprofile.AdminProfileActivity

class AdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.gotToAdminProfile.setOnClickListener {
            val intent = Intent(this, AdminProfileActivity::class.java)
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