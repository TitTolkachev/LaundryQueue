package com.example.washingmachine.presentation.screens.studentprofile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.washingmachine.databinding.ActivityStudentProfileBinding
import com.example.washingmachine.presentation.screens.auth.AuthActivity
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

        viewModel.getNavigationToAuthLiveData().observe(this) {
            if (it) {
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

        viewModel.getStudentProfileLiveData().observe(this) {
            binding.studentBalance.text = it.money.toString()
            binding.studentEmail.text = it.email
            binding.studentName.text = it.name
            binding.studentSurname.text = it.surname
            binding.studentRoom.text = it.room

        }
    }

    private fun showBottomSheet() {
        val bottomSheetFragment = BottomSheetDialog()
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }

}