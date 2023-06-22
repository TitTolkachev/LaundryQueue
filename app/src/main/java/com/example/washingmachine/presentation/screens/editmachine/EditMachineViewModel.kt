package com.example.washingmachine.presentation.screens.editmachine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.washingmachine.data.remote.dto.DormitoryDto
import com.example.washingmachine.domain.model.ChangeMachineStatusRequest
import com.example.washingmachine.domain.usecase.remote.ChangeMachineStatusUseCase
import com.example.washingmachine.domain.usecase.remote.DeleteMachineUseCase
import com.example.washingmachine.domain.usecase.remote.GetDormitoriesUseCase
import com.example.washingmachine.domain.util.Resource
import com.example.washingmachine.presentation.screens.main.model.MachineStatus
import kotlinx.coroutines.launch

class EditMachineViewModel(
    private val changeMachineStatusUseCase: ChangeMachineStatusUseCase,
    private val deleteMachineUseCase: DeleteMachineUseCase,
    getDormitoriesUseCase: GetDormitoriesUseCase
) : ViewModel() {

    private val _showSuccess = MutableLiveData<Boolean>()
    val showSuccess: LiveData<Boolean> = _showSuccess

    private val _showError = MutableLiveData<Boolean>()
    val showError: LiveData<Boolean> = _showError

    private val _dormitories = MutableLiveData<List<DormitoryDto>>()
    val dormitories: LiveData<List<DormitoryDto>> = _dormitories

    private val _exit = MutableLiveData<Boolean>()
    val exit: LiveData<Boolean> = _exit

    init {
        viewModelScope.launch {
            when (val data = getDormitoriesUseCase.execute()) {
                is Resource.Success -> {
                    if (data.data != null) {
                        val putData: List<DormitoryDto> = data.data
                        _dormitories.postValue(putData)
                    }
                }

                else -> {

                }
            }
        }
    }

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