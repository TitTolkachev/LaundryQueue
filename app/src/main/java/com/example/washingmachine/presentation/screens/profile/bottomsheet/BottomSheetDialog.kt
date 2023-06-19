package com.example.washingmachine.presentation.screens.profile.bottomsheet

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.washingmachine.databinding.BottomSheetTopUpBalanceDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDialog(private val balance: Int = 0) : BottomSheetDialogFragment() {

    private lateinit var viewModel : BottomSheetViewModel
    private lateinit var bottomSheetBinding: BottomSheetTopUpBalanceDialogBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[BottomSheetViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        bottomSheetBinding = BottomSheetTopUpBalanceDialogBinding.inflate(layoutInflater)
        bottomSheetBinding.raise.setOnClickListener {
            val num = bottomSheetBinding.editTextNumberDecimal.text.toString().toIntOrNull()?.let {
                it + 1
            }
            bottomSheetBinding.editTextNumberDecimal.setText(num?.toString() ?: "0")
        }

        bottomSheetBinding.lower.setOnClickListener {
            val num = bottomSheetBinding.editTextNumberDecimal.text.toString().toIntOrNull()?.let {
                if (it > 0 ){
                    it - 1
                } else {
                    it
                }
            }
            bottomSheetBinding.editTextNumberDecimal.setText(num?.toString() ?: "0")
        }

        bottomSheetBinding.saveButton.setOnClickListener {
            viewModel.saveData(bottomSheetBinding.editTextNumberDecimal.text.toString().toInt())
        }

        bottomSheetBinding.editTextNumberDecimal.setText(balance.toString())

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable) {
                val str = p0.toString()
                if (!"[0-9]*".toRegex().matches(str)) {
                    val new = str.filter { "0123456789".indexOf(it) > -1 }
                    p0.clear()
                    p0.append(new)
                }
            }

        }

        bottomSheetBinding.editTextNumberDecimal.addTextChangedListener(textWatcher)

        return bottomSheetBinding.root
    }

}