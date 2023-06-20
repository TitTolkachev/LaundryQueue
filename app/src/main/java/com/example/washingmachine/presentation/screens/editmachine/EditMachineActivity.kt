package com.example.washingmachine.presentation.screens.editmachine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.washingmachine.databinding.ActivityEditMachineBinding

class EditMachineActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditMachineBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditMachineBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}