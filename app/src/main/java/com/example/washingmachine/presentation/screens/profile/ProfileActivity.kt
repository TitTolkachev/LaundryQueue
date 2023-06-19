package com.example.washingmachine.presentation.screens.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.washingmachine.databinding.ActivityProfileBinding
import com.example.washingmachine.presentation.screens.profile.bottomsheet.BottomSheetDialog


class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topUpBalanceButton.setOnClickListener {
            showBottomSheet()
        }
    }

    private fun showBottomSheet(){
        val bottomSheetFragment = BottomSheetDialog()
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }

}