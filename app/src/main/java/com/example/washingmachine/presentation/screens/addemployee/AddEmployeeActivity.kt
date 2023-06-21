package com.example.washingmachine.presentation.screens.addemployee

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.washingmachine.databinding.ActivityAddEmployeeBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.getViewModel

class AddEmployeeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEmployeeBinding
    private lateinit var viewModel: AddEmployeeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = getViewModel()

        viewModel.showProgressBar.observe(this) {
            if (it == true) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        binding.button3.setOnClickListener {
            val email = binding.textInputLayout4.editText?.text.toString()
            val name = binding.textInputLayout5.editText?.text.toString()
            val surname = binding.textInputLayout6.editText?.text.toString()
            viewModel.addEmployee(email, name, surname)
        }

        viewModel.showSuccess.observe(this) {
            if (it == true) {
                viewModel.snackBarShowed()
                Snackbar.make(binding.root, "User added", Snackbar.LENGTH_LONG)
                    .setAction("CLOSE") { }
                    .setActionTextColor(Color.GRAY)
                    .show()
            }
        }

        viewModel.showError.observe(this) {
            if (it == true) {
                viewModel.snackBarShowed()
                Snackbar.make(binding.root, "Failed!", Snackbar.LENGTH_LONG)
                    .setAction("CLOSE") { }
                    .setActionTextColor(Color.RED)
                    .show()
            }
        }
    }
}