package com.example.washingmachine.presentation.screens.queue

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.washingmachine.domain.usecase.remote.GetMachineQueueUseCase
import com.example.washingmachine.domain.usecase.remote.GetStudentProfileUseCase
import com.example.washingmachine.domain.util.Resource
import com.example.washingmachine.presentation.screens.queue.model.QueueSlot
import com.example.washingmachine.presentation.screens.queue.model.QueueSlotTypes
import kotlinx.coroutines.launch

class QueueViewModel(
    private val getStudentProfileUseCase: GetStudentProfileUseCase,
    private val getMachineQueueUseCase: GetMachineQueueUseCase
) : ViewModel() {

    private val _queueSlotsData = MutableLiveData<MutableList<QueueSlot>>()
    val queueSlotsData: LiveData<MutableList<QueueSlot>> = _queueSlotsData

    private lateinit var userId: String

    fun update(machineId: String) {
        viewModelScope.launch {
            when (val profileRequest = getStudentProfileUseCase.execute()) {
                is Resource.Success -> {
                    val id = profileRequest.data?.id
                    if (!id.isNullOrEmpty())
                        userId = id
                }

                else -> {}
            }

            when (val queueRequest = getMachineQueueUseCase.execute(machineId)) {
                is Resource.Success -> {
                    val slots =
                        queueRequest.data?.map {
                            // TODO
                            QueueSlot(
                                id = it.id ?: "1",
                                type = when (it.personId) {
                                    null -> QueueSlotTypes.AVAILABLE
                                    userId -> QueueSlotTypes.SELF
                                    else -> QueueSlotTypes.NOT_AVAILABLE
                                },
                                surname = it.personId ?: "1"
                            )
                        }
                    slots?.let {
                        val putData: MutableList<QueueSlot> = slots.toMutableList()
                        _queueSlotsData.postValue(putData)
                    }
                }

                else -> {}
            }
        }
    }
}