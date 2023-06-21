package com.example.washingmachine.presentation.screens.addstudent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.washingmachine.domain.usecase.remote.CreateStudentUseCase
import com.example.washingmachine.domain.util.Resource
import kotlinx.coroutines.launch

class AddStudentViewModel(
    private val createStudentUseCase: CreateStudentUseCase
) : ViewModel() {

    private val _showProgressBar = MutableLiveData<Boolean>()
    val showProgressBar: LiveData<Boolean> = _showProgressBar

    private val _showSuccess = MutableLiveData<Boolean>()
    val showSuccess: LiveData<Boolean> = _showSuccess

    private val _showError = MutableLiveData<Boolean>()
    val showError: LiveData<Boolean> = _showError


    fun addStudent(email: String) {

        // TODO валидацию
        viewModelScope.launch {
            _showProgressBar.value = true
            when (createStudentUseCase.execute(email)) {
                is Resource.Success -> {
                    _showSuccess.postValue(true)
                }

                else -> {
                    _showError.postValue(true)
                }
            }
            _showProgressBar.value = false
        }
    }

    fun snackBarShowed() {
        _showSuccess.value = false
        _showError.value = false
    }
}