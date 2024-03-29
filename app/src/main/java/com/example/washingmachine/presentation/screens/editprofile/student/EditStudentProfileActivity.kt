package com.example.washingmachine.presentation.screens.editprofile.student

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.washingmachine.databinding.ActivityStudentEditProfileBinding
import com.example.washingmachine.presentation.screens.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel


class EditStudentProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentEditProfileBinding
    private lateinit var viewModel: EditStudentProfileViewModel

    private var selectedDormitory: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
        viewModel.getCurrentData()

        viewModel.setWorkingType((intent.extras?.getBoolean("must_fill") ?: false))

        binding = ActivityStudentEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.studentSaveChangesButton.setOnClickListener {

            val dormitoryId = selectedDormitory?.let {
                viewModel.dormitories.firstOrNull { it.number.toString() == selectedDormitory }?.id
                    ?: ""
            } ?: viewModel.dormitories.firstOrNull()?.id ?: ""

            viewModel.save(
                binding.studentChangeEmail.text.toString(),
                binding.studentChangeName.text.toString(),
                binding.studentChangeSurname.text.toString(),
                binding.studentChangeRoom.text.toString(),
                dormitoryId
            )
        }

        viewModel.getProfileLiveData().observe(this) { data ->
            binding.studentChangeEmail.setText(data.email.toString())
            binding.studentChangeName.setText(data.name.toString())
            binding.studentChangeSurname.setText(data.surname.toString())
            binding.studentChangeRoom.setText(data.room.toString())
        }

        viewModel.getDormitoriesLiveData().observe(this) {
            val numbers = mutableListOf<String>()

            for (dormitoryDto in it) {
                numbers.add(dormitoryDto.number.toString())
            }

            val adapter: ArrayAdapter<String> =
                ArrayAdapter<String>(this, R.layout.simple_spinner_item, numbers)
            adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            binding.dormitoryList.adapter = adapter

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
            binding.dormitoryList.onItemSelectedListener = itemSelectedListener
        }

        viewModel.getNavigateToMainScreenLiveData().observe(this) {
            if (it == true) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        viewModel.getNavigateToProfileScreenLiveData().observe(this) {
            if (it == true) {
                finish()
            }
        }
    }

}