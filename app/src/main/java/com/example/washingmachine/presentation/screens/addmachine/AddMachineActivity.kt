package com.example.washingmachine.presentation.screens.addmachine

import android.R
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.washingmachine.databinding.ActivityAddMachineBinding
import com.example.washingmachine.presentation.screens.main.model.MachineStatus
import com.example.washingmachine.presentation.screens.main.model.MachineTypes
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.getViewModel

class AddMachineActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddMachineBinding
    private lateinit var viewModel: AddMachineViewModel

    private var selectedDormitory: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMachineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = getViewModel()

        binding.button8.setOnClickListener {
            val type =
                if (binding.spinner.selectedItemPosition == -1)
                    MachineTypes.WASHING_MACHINE
                else
                    MachineTypes.values()[binding.spinner.selectedItemPosition]
            val name = binding.textInputLayout8.editText?.text.toString()
            val status =
                if (binding.spinner2.selectedItemPosition == -1)
                    MachineStatus.UNAVAILABLE
                else
                    MachineStatus.values()[binding.spinner2.selectedItemPosition]
            val ip = binding.textInputLayout9.editText?.text.toString()
            val location = selectedDormitory ?: ""
            viewModel.createMachine(type, name, status, ip, location.toIntOrNull())
        }

        viewModel.showSuccess.observe(this) {
            if (it == true) {
                viewModel.snackBarShowed()
                Snackbar.make(binding.root, "New Machine created", Snackbar.LENGTH_LONG)
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

        viewModel.dormitories.observe(this) {
            val numbers = mutableListOf<String>()

            for (dormitoryDto in it) {
                numbers.add(dormitoryDto.number.toString())
            }

            val adapter: ArrayAdapter<String> =
                ArrayAdapter<String>(this, R.layout.simple_spinner_item, numbers)
            adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            binding.spinner3.adapter = adapter

            val itemSelectedListener: AdapterView.OnItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {

                        val item = parent.getItemAtPosition(position) as String
                        selectedDormitory = item
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
            binding.spinner3.onItemSelectedListener = itemSelectedListener
        }
    }
}