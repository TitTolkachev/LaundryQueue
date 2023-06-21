package com.example.washingmachine.presentation.screens.addstudent

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.washingmachine.databinding.ActivityAddStudentBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.getViewModel

class AddStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddStudentBinding
    private lateinit var viewModel: AddStudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStudentBinding.inflate(layoutInflater)
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
            viewModel.addStudent(email)
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