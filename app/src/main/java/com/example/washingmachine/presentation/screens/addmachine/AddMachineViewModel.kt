package com.example.washingmachine.presentation.screens.addmachine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.washingmachine.data.remote.dto.DormitoryDto
import com.example.washingmachine.domain.model.CreateNewMachineRequest
import com.example.washingmachine.domain.usecase.remote.CreateMachineUseCase
import com.example.washingmachine.domain.usecase.remote.GetDormitoriesUseCase
import com.example.washingmachine.domain.util.Resource
import com.example.washingmachine.presentation.screens.main.model.MachineStatus
import com.example.washingmachine.presentation.screens.main.model.MachineTypes
import kotlinx.coroutines.launch

class AddMachineViewModel(
    private val createMachineUseCase: CreateMachineUseCase,
    getDormitoriesUseCase: GetDormitoriesUseCase
) : ViewModel() {

    private val _showSuccess = MutableLiveData<Boolean>()
    val showSuccess: LiveData<Boolean> = _showSuccess

    private val _showError = MutableLiveData<Boolean>()
    val showError: LiveData<Boolean> = _showError

    private val _dormitories = MutableLiveData<List<DormitoryDto>>()
    val dormitories: LiveData<List<DormitoryDto>> = _dormitories


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

    fun createMachine(
        type: MachineTypes,
        name: String,
        machineStatus: MachineStatus,
        ip: String,
        location: Int?
    ) {
        val dormitoryId = _dormitories.value?.first { it.number == location }?.id
        if (dormitoryId != null) {
            viewModelScope.launch {
                when (createMachineUseCase.execute(
                    CreateNewMachineRequest(
                        type = type.name,
                        name = name,
                        machineStatus = machineStatus.name,
                        ip = ip,
                        location = dormitoryId,
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
    }

    fun snackBarShowed() {
        _showSuccess.value = false
        _showError.value = false
    }
}