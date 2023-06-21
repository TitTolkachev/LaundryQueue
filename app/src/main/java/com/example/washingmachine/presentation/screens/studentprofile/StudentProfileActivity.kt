package com.example.washingmachine.presentation.screens.studentprofile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.washingmachine.databinding.ActivityStudentProfileBinding
import com.example.washingmachine.presentation.screens.auth.AuthActivity
import com.example.washingmachine.presentation.screens.editprofile.student.EditStudentProfileActivity
import com.example.washingmachine.presentation.screens.studentprofile.bottomsheet.BottomSheetDialog
import org.koin.androidx.viewmodel.ext.android.getViewModel


class StudentProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentProfileBinding
    private lateinit var viewModel: StudentProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStudentProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = getViewModel()
        viewModel.refreshData()

        binding.topUpBalanceButton.setOnClickListener {
            showBottomSheet()
        }

        binding.studentLogoutButton.setOnClickListener {
            viewModel.logout()
        }

        binding.editStudentButton.setOnClickListener {
            openEditStudentProfile()
        }

        viewModel.getNavigationToAuthLiveData().observe(this) {
            if (it == true) {
                val intent = Intent(this, AuthActivity::class.java)
                intent.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP
                            or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            or Intent.FLAG_ACTIVITY_NEW_TASK
                )
                startActivity(intent)
                finish()
            }
        }

        viewModel.getStudentProfileLiveData().observe(this) { data ->
            binding.studentBalance.text = data.money.toString()
            binding.studentEmail.text = data.email
            binding.studentName.text = data.name
            binding.studentSurname.text = data.surname
            binding.studentRoom.text = data.room

            binding.studentDormitory.text = data.dormitory?.number.toString() + " dormitory"
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshData()
    }

    private fun openEditStudentProfile() {
        val intent = Intent(this, EditStudentProfileActivity::class.java)
        startActivity(intent)
    }

    private fun showBottomSheet() {
        val bottomSheetFragment = BottomSheetDialog() {
            viewModel.topUpBalance(it)
        }
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }

}