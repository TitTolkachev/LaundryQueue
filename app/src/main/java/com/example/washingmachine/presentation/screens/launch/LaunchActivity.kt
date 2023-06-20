package com.example.washingmachine.presentation.screens.launch

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.washingmachine.databinding.ActivityLaunchBinding
import com.example.washingmachine.presentation.screens.admin.AdminActivity
import com.example.washingmachine.presentation.screens.auth.AuthActivity
import com.example.washingmachine.presentation.screens.employee.EmployeeActivity
import com.example.washingmachine.presentation.screens.main.MainActivity
import com.example.washingmachine.presentation.screens.onboarding.OnBoardingActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

@SuppressLint("CustomSplashScreen")
class LaunchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLaunchBinding
    private lateinit var viewModel: LaunchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = getViewModel()

//        viewModel =
//            ViewModelProvider(this, LaunchViewModelFactory(this))[LaunchViewModel::class.java]

        viewModel.navigateToOnBoarding.observe(this) {
            if (it == true) {
                val intent = Intent(this, OnBoardingActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        viewModel.navigateToSignIn.observe(this) {
            if (it == true) {
                val intent = Intent(this, AuthActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        viewModel.navigateToMain.observe(this) {
            if (it == true) {
                val intent = Intent(this, MainActivity::class.java)
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
    }
}