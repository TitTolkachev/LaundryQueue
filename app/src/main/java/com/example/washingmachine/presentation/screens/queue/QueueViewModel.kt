package com.example.washingmachine.presentation.screens.queue

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.washingmachine.domain.usecase.remote.BookSlotUseCase
import com.example.washingmachine.domain.usecase.remote.CheckOutQueueUseCase
import com.example.washingmachine.domain.usecase.remote.GetMachineQueueUseCase
import com.example.washingmachine.domain.usecase.remote.GetStudentProfileUseCase
import com.example.washingmachine.domain.util.Resource
import com.example.washingmachine.presentation.screens.queue.model.QueueSlot
import com.example.washingmachine.presentation.screens.queue.model.QueueSlotTypes
import kotlinx.coroutines.launch

class QueueViewModel(
    private val getStudentProfileUseCase: GetStudentProfileUseCase,
    private val getMachineQueueUseCase: GetMachineQueueUseCase,
    private val checkOutQueueUseCase: CheckOutQueueUseCase,
    private val bookSlotUseCase: BookSlotUseCase
) : ViewModel() {

    private val _queueSlotsData = MutableLiveData<MutableList<QueueSlot>>()
    val queueSlotsData: LiveData<MutableList<QueueSlot>> = _queueSlotsData

    private val _showTakeQueueSucceeded = MutableLiveData<Boolean>()
    val showTakeQueueSucceeded: LiveData<Boolean> = _showTakeQueueSucceeded

    private val _showCheckoutSucceeded = MutableLiveData<Boolean>()
    val showCheckoutSucceeded: LiveData<Boolean> = _showCheckoutSucceeded

    private lateinit var userId: String

    fun takeQueue(slotId: String) {
        viewModelScope.launch {
            when (bookSlotUseCase.execute(slotId)) {
                is Resource.Success -> {
                    _showTakeQueueSucceeded.postValue(true)
                }

                else -> {}
            }
        }
    }

    fun checkout() {
        viewModelScope.launch {
            when (val request = checkOutQueueUseCase.execute()) {
                is Resource.Success -> {
                    _showCheckoutSucceeded.postValue(true)
                    val slots = request.data?.map {
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