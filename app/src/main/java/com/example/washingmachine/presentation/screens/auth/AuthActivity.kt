package com.example.washingmachine.presentation.screens.auth

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.washingmachine.R
import com.example.washingmachine.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    //TODO
    private var state = AuthScreenState.SIGN_UP

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button3.setOnClickListener {
            if (state == AuthScreenState.SIGN_IN) {
                //TODO
            } else {
                state = AuthScreenState.SIGN_IN
                binding.textInputLayout.visibility = View.GONE
                it.setBackgroundColor(getColor(R.color.transparentA6_black))
                binding.button2.setBackgroundColor(getColor(R.color.transparent80_black33))
            }
        }

        binding.button2.setOnClickListener {
            if (state == AuthScreenState.SIGN_UP) {
                //TODO
            } else {
                state = AuthScreenState.SIGN_UP
                binding.textInputLayout.visibility = View.VISIBLE
                it.setBackgroundColor(getColor(R.color.transparentA6_black))
                binding.button3.setBackgroundColor(getColor(R.color.transparent80_black33))
            }
        }
    }

    private enum class AuthScreenState {
        SIGN_IN,
        SIGN_UP
    }
}