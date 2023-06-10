package com.example.washingmachine.presentation.screens.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.washingmachine.R
import com.google.android.material.textfield.TextInputLayout

class AuthActivity : AppCompatActivity() {

    //TODO
    private var state = AuthScreenState.SIGN_UP

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        findViewById<Button>(R.id.button3).setOnClickListener {
            if (state == AuthScreenState.SIGN_IN) {
                //TODO
            } else {
                state = AuthScreenState.SIGN_IN
                findViewById<TextInputLayout>(R.id.textInputLayout).visibility = View.GONE
                it.setBackgroundColor(getColor(R.color.transparentA6_black))
                findViewById<Button>(R.id.button2).setBackgroundColor(getColor(R.color.transparent80_black33))
            }
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            if (state == AuthScreenState.SIGN_UP) {
                //TODO
            } else {
                state = AuthScreenState.SIGN_UP
                findViewById<TextInputLayout>(R.id.textInputLayout).visibility = View.VISIBLE
                it.setBackgroundColor(getColor(R.color.transparentA6_black))
                findViewById<Button>(R.id.button3).setBackgroundColor(getColor(R.color.transparent80_black33))
            }
        }
    }

    private enum class AuthScreenState {
        SIGN_IN,
        SIGN_UP
    }
}