package com.example.washingmachine.presentation.screens.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.washingmachine.databinding.ActivityAuthBinding
import com.example.washingmachine.presentation.screens.admin.AdminActivity
import com.example.washingmachine.presentation.screens.editprofile.student.EditStudentProfileActivity
import com.example.washingmachine.presentation.screens.employee.EmployeeActivity
import com.example.washingmachine.presentation.screens.main.MainActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import org.koin.androidx.viewmodel.ext.android.getViewModel

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = getViewModel()

        viewModel.navigateToMain.observe(this) {
            if (it == true) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        viewModel.navigateEditStudentProfile.observe(this) {
            if (it == true) {
                val intent = Intent(this, EditStudentProfileActivity::class.java)
                intent.putExtra("must_fill", true)
                startActivity(intent)
                finish()
            }
        }

        viewModel.navigateToAdmin.observe(this) {
            if (it == true) {
                val intent = Intent(this, AdminActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        viewModel.navigateToEmployee.observe(this) {
            if (it == true) {
                val intent = Intent(this, EmployeeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        viewModel.shouldSendDeviceToken.observe(this) {
            if (it == true)
                sendDeviceToken()
        }

        viewModel.showSignInError.observe(this) {
            if (it == true)
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
        }

        viewModel.showDeviceToken.observe(this) {
            if (it == true)
                Toast.makeText(this, "Problems with device token", Toast.LENGTH_SHORT).show()
        }

        binding.button2.setOnClickListener {
            val email = binding.textInputEmail.text.toString()
            val password = binding.textInputPassword.text.toString()
            viewModel.signIn(email, password)
        }
    }

    private fun sendDeviceToken() {
        Firebase.messaging.token.addOnCompleteListener(
            OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(
                        "DEVICE_TOKEN",
                        "Fetching FCM registration token failed",
                        task.exception
                    )
                    return@OnCompleteListener
                }
                val token = task.result
                viewModel.sendDeviceToken(token)
                Log.d("DEVICE_TOKEN", token)
            },
        )
    }
}