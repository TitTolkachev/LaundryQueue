package com.example.washingmachine.presentation.screens.employee

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.washingmachine.databinding.ActivityEmployeeBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class EmployeeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeeBinding
    private lateinit var viewModel: EmployeeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = getViewModel()
    }
}