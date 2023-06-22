package com.example.washingmachine.presentation.screens.editmachine

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.washingmachine.R
import com.example.washingmachine.databinding.ActivityEditMachineBinding
import com.example.washingmachine.presentation.screens.main.model.MachineStatus
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.getViewModel

class EditMachineActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditMachineBinding
    private lateinit var viewModel: EditMachineViewModel

    private var machineId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditMachineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = getViewModel()

        machineId = intent.getStringExtra(getString(R.string.machine_id))

        binding.button8.setOnClickListener {
            if (machineId != null) {
                val status =
                    if (binding.spinner2.selectedItemPosition == -1)
                        MachineStatus.UNAVAILABLE
                    else
                        MachineStatus.values()[binding.spinner2.selectedItemPosition]
                viewModel.change(machineId = machineId ?: "", status = status)
            }
        }

        binding.button9.setOnClickListener {
            if (machineId != null) {
                viewModel.delete(machineId = machineId ?: "")
            }
        }

        viewModel.exit.observe(this) {
            if (it == true) {
                finish()
            }
        }

        viewModel.showSuccess.observe(this) {
            if (it == true) {
                viewModel.snackBarShowed()
                Snackbar.make(binding.root, "Machine status changed", Snackbar.LENGTH_LONG)
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