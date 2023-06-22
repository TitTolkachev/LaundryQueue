package com.example.washingmachine.presentation.screens.studentqueue

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.washingmachine.domain.usecase.remote.CheckOutQueueUseCase
import com.example.washingmachine.domain.usecase.remote.GetMachinesUseCase
import com.example.washingmachine.domain.usecase.remote.GetStudentProfileUseCase
import com.example.washingmachine.domain.usecase.remote.StartMachineUseCase
import com.example.washingmachine.domain.util.Resource
import com.example.washingmachine.presentation.screens.studentqueue.model.MachineSlot
import kotlinx.coroutines.launch

class StudentQueueTicketsViewModel(
    private val getStudentProfileUseCase: GetStudentProfileUseCase,
    private val getMachinesUseCase: GetMachinesUseCase,
    private val checkOutQueueUseCase: CheckOutQueueUseCase,
    private val startMachineUseCase: StartMachineUseCase
) :
    ViewModel() {
    private val myTicketsUpdatedLiveData = MutableLiveData(false)

    private val messageLiveData = MutableLiveData<String>()

    private val myTickets = mutableListOf<MachineSlot>()

    fun getMyTicketsLiveData(): LiveData<Boolean> = myTicketsUpdatedLiveData

    fun getMessageLiveData(): LiveData<String> = messageLiveData

    fun getMyTickets() = myTickets

    fun startMachine() {
        viewModelScope.launch {
            when (val data = startMachineUseCase.execute()) {
                is Resource.Success -> {
                    messageLiveData.postValue("Started")
                }

                else -> {
                    messageLiveData.postValue("Start error")
                }
            }
        }
    }

    fun checkOutQueue() {
        viewModelScope.launch {
            when (val data = checkOutQueueUseCase.execute()) {
                is Resource.Success -> {
                    messageLiveData.postValue("Successfully checked out")
                }

                else -> {
                    messageLiveData.postValue("Error in checked out")
                }
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            when (val data = getStudentProfileUseCase.execute()) {
                is Resource.Success -> {

                    when (val queue = getMachinesUseCase.execute(data.data?.dormitory?.id!!)) {

                        is Resource.Success -> {
                            myTickets.clear()

                            queue.data?.filter { it.location == data.data.dormitory.id }
                                ?.forEach {
                                    it.queueSlots?.forEach { slot ->

                                        if (slot.personId == data.data.id) myTickets.add(
                                            MachineSlot(
                                                slot.id,
                                                slot.number,
                                                slot.personId,
                                                slot.status,
                                                it.status,
                                                it.name,
                                                it.startTime
                                            )
                                        )
                                    }
                                }

                            myTicketsUpdatedLiveData.postValue(true)
                        }

                        else -> {
                            messageLiveData.postValue("Error for get machines data")
                        }
                    }
                }

                else -> {
                    messageLiveData.postValue("Error for get user data")
                }
            }
        }
    }
}