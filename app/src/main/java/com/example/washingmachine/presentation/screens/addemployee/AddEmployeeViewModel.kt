package com.example.washingmachine.presentation.screens.addemployee

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.washingmachine.domain.model.CreateEmployee
import com.example.washingmachine.domain.usecase.remote.CreateEmployeeUseCase
import com.example.washingmachine.domain.util.Resource
import kotlinx.coroutines.launch

class AddEmployeeViewModel(
    private val createEmployeeUseCase: CreateEmployeeUseCase
) : ViewModel() {

    private val _showProgressBar = MutableLiveData<Boolean>()
    val showProgressBar: LiveData<Boolean> = _showProgressBar

    private val _showSuccess = MutableLiveData<Boolean>()
    val showSuccess: LiveData<Boolean> = _showSuccess

    private val _showError = MutableLiveData<Boolean>()
    val showError: LiveData<Boolean> = _showError


    fun addEmployee(email: String, name: String, surname: String) {

        // TODO валидацию
        viewModelScope.launch {
            _showProgressBar.value = true
            when (createEmployeeUseCase.execute(
                CreateEmployee(
                    email,
                    name,
                    surname
                )
            )) {
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