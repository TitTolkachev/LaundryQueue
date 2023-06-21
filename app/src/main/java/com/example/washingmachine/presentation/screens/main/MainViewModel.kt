package com.example.washingmachine.presentation.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.washingmachine.domain.model.Machine
import com.example.washingmachine.domain.usecase.remote.GetMachinesUseCase
import com.example.washingmachine.domain.usecase.remote.GetStudentProfileUseCase
import com.example.washingmachine.domain.util.Resource
import com.example.washingmachine.presentation.screens.main.model.MachineCard
import com.example.washingmachine.presentation.screens.main.model.MachineStatus
import com.example.washingmachine.presentation.screens.main.model.MachineTypes
import kotlinx.coroutines.launch

class MainViewModel(
    private val getStudentProfileUseCase: GetStudentProfileUseCase,
    private val getMachinesUseCase: GetMachinesUseCase
) : ViewModel() {

    private val _washingMachinesData = MutableLiveData<MutableList<MachineCard>>()
    val washingMachinesData: LiveData<MutableList<MachineCard>> = _washingMachinesData

    private val _dryingMachinesData = MutableLiveData<MutableList<MachineCard>>()
    val dryingMachinesData: LiveData<MutableList<MachineCard>> = _dryingMachinesData

    private lateinit var dormitoryId: String

    fun update() {
        viewModelScope.launch {
            when (val profileRequest = getStudentProfileUseCase.execute()) {
                is Resource.Success -> {
                    val id = profileRequest.data?.dormitory?.id
                    if (!id.isNullOrEmpty())
                        dormitoryId = id
                    else {
                        // TODO заставить указать общагу
                    }
                }

                else -> {}
            }

            when (val machinesRequest = getMachinesUseCase.execute(dormitoryId)) {
                is Resource.Success -> {
                    val washingMachines =
                        machinesRequest.data
                            ?.filter { it.type == MachineTypes.WASHING_MACHINE.name }
                            ?.map { mapMachine(it) }
                    washingMachines?.let {
                        val putData: MutableList<MachineCard> = washingMachines.toMutableList()
                        _washingMachinesData.postValue(putData)
                    }

                    val dryingMachines =
                        machinesRequest.data?.filter { it.type == MachineTypes.DRYING_MACHINE.name }
                            ?.map { mapMachine(it) }
                    dryingMachines?.let {
                        val putData: MutableList<MachineCard> = dryingMachines.toMutableList()
                        _dryingMachinesData.postValue(putData)
                    }
                }

                else -> {}
            }
        }
    }

    private fun mapMachine(m: Machine): MachineCard {
        return MachineCard(
            id = m.id ?: "1",
            name = m.name ?: "Washing",
            status = when (m.status) {
                MachineStatus.READY_TO_WORK.name -> "Ready"
                MachineStatus.UNAVAILABLE.name -> "Closed"
                MachineStatus.WORKING.name -> "Working"
                else -> "Broken"
            },
            availableSlots = m.queueSlots?.count { slot -> slot.personId.isNullOrEmpty() }
                ?: 0
        )
    }
}