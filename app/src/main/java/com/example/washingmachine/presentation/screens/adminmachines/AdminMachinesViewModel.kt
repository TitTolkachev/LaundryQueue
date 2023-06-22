package com.example.washingmachine.presentation.screens.adminmachines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.washingmachine.domain.model.Machine
import com.example.washingmachine.domain.usecase.remote.GetAdminProfileUseCase
import com.example.washingmachine.domain.usecase.remote.GetMachinesUseCase
import com.example.washingmachine.domain.util.Resource
import kotlinx.coroutines.launch

class AdminMachinesViewModel(
    private val getAdminProfileUseCase: GetAdminProfileUseCase,
    private val getMachinesUseCase: GetMachinesUseCase
) :
    ViewModel() {
    private val machinesLiveData = MutableLiveData<MutableList<Machine>>()

    private lateinit var dormitoryName: String

    fun getMachinesLiveData(): LiveData<MutableList<Machine>> = machinesLiveData

    fun getDormitoryName() = dormitoryName

    fun refresh() {
        viewModelScope.launch {
            var dormitoryId: String? = null
            when (val data = getAdminProfileUseCase.execute()) {
                is Resource.Success -> {
                    dormitoryId = data.data?.dormitory?.id
                    dormitoryName = data.data?.dormitory?.number.toString()
                }

                else -> {

                }
            }


            when (val data = getMachinesUseCase.execute(dormitoryId ?: "")) {
                is Resource.Success -> {
                    if (!data.data.isNullOrEmpty()) {
                        machinesLiveData.postValue(data.data.toMutableList())
                    }
                }

                else -> {

                }
            }
        }
    }

}