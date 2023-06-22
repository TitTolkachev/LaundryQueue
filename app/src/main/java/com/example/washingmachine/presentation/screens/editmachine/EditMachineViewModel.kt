package com.example.washingmachine.presentation.screens.editmachine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.washingmachine.domain.model.ChangeMachineStatusRequest
import com.example.washingmachine.domain.usecase.remote.ChangeMachineStatusUseCase
import com.example.washingmachine.domain.usecase.remote.DeleteMachineUseCase
import com.example.washingmachine.domain.util.Resource
import com.example.washingmachine.presentation.screens.main.model.MachineStatus
import kotlinx.coroutines.launch

class EditMachineViewModel(
    private val changeMachineStatusUseCase: ChangeMachineStatusUseCase,
    private val deleteMachineUseCase: DeleteMachineUseCase
) : ViewModel() {

    private val _showSuccess = MutableLiveData<Boolean>()
    val showSuccess: LiveData<Boolean> = _showSuccess

    private val _showError = MutableLiveData<Boolean>()
    val showError: LiveData<Boolean> = _showError

    private val _exit = MutableLiveData<Boolean>()
    val exit: LiveData<Boolean> = _exit

    fun change(machineId: String, status: MachineStatus) {
        viewModelScope.launch {
            when (changeMachineStatusUseCase.execute(
                ChangeMachineStatusRequest(
                    machineId = machineId,
                    status = status.name
                )
            )) {
                is Resource.Success -> {
                    _showSuccess.postValue(true)
                }

                else -> {
                    _showError.postValue(true)
                }
            }
        }
    }

    fun delete(machineId: String) {
        viewModelScope.launch {
            when (deleteMachineUseCase.execute(machineId)) {
                is Resource.Success -> {
                    _exit.postValue(true)
                }

                else -> {
                    _showError.postValue(true)
                }
            }
        }
    }

    fun snackBarShowed() {
        _showSuccess.value = false
        _showError.value = false
    }
}