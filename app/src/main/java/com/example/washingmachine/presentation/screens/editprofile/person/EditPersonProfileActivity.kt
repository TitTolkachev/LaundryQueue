package com.example.washingmachine.presentation.screens.editprofile.person

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.washingmachine.databinding.ActivityPersonEditProfileBinding
import com.example.washingmachine.domain.model.Roles
import com.example.washingmachine.presentation.screens.admin.AdminActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class EditPersonProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersonEditProfileBinding
    private lateinit var viewModel: EditPersonProfileViewModel

    private var selectedDormitory: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
        viewModel.getCurrentData()

        viewModel.setWorkingType((intent.extras?.getBoolean("must_fill") ?: false))

        binding = ActivityPersonEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.personSaveChangesButton.setOnClickListener {
            viewModel.save(
                binding.personChangeEmail.text.toString(),
                binding.personChangeName.text.toString(),
                binding.personChangeSurname.text.toString(),
                viewModel.dormitories.firstOrNull { it.number.toString() == selectedDormitory }?.id
                    ?: ""
            )
        }

        viewModel.getProfileLiveData().observe(this) { data ->
            binding.personChangeEmail.setText(data.email.toString())
            binding.personChangeName.setText(data.name.toString())
            binding.personChangeSurname.setText(data.surname.toString())
            selectedDormitory = data.dormitory?.number.toString()
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

                val intent: Intent = when (viewModel.getProfileLiveData().value?.role) {
                    Roles.ROLE_ADMIN.name -> {
                        Intent(this, AdminActivity::class.java)
                    }

                    else -> {
                        // TODO(EMPLOYEE ACTIVITY)
                        Intent(this, AdminActivity::class.java)
                    }
                }

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