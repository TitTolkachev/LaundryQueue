package com.example.washingmachine.presentation.screens.editprofile.student

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.washingmachine.data.remote.dto.DormitoryDto
import com.example.washingmachine.domain.model.StudentEditRequestBody
import com.example.washingmachine.domain.model.StudentInfo
import com.example.washingmachine.domain.usecase.remote.EditStudentProfileUseCase
import com.example.washingmachine.domain.usecase.remote.GetDormitoriesUseCase
import com.example.washingmachine.domain.usecase.remote.GetStudentProfileUseCase
import com.example.washingmachine.domain.util.Resource
import kotlinx.coroutines.launch

class EditStudentProfileViewModel(
    private val getStudentProfileUseCase: GetStudentProfileUseCase,
    private val editStudentProfileUseCase: EditStudentProfileUseCase,
    private val getDormitoriesUseCase: GetDormitoriesUseCase
) :
    ViewModel() {

    private val liveData = MutableLiveData(StudentInfo())
    private val dormitoriesLiveData = MutableLiveData(listOf(DormitoryDto()))

    private var mustFillAllFields = false

    var dormitories = listOf<DormitoryDto>()

    fun setWorkingType(mustFillAllFields: Boolean) {
        this.mustFillAllFields = mustFillAllFields
    }

    fun getProfileLiveData(): LiveData<StudentInfo> = liveData

    fun getDormitoriesLiveData(): LiveData<List<DormitoryDto>> = dormitoriesLiveData

    fun getCurrentData() {
        viewModelScope.launch {
            when (val data = getDormitoriesUseCase.execute()) {
                is Resource.Success -> {
                    dormitoriesLiveData.postValue(data.data)
                    dormitories = data.data!!
                }

                else -> {

                }
            }

            when (val data = getStudentProfileUseCase.execute()) {
                is Resource.Success -> {
                    liveData.postValue(data.data)
                }

                else -> {

                }
            }
        }
    }


    fun save(
        email: String? = null,
        name: String? = null,
        surname: String? = null,
        room: String? = null,
        dormitoryId: String? = null
    ) {
        if (mustFillAllFields) {
            if (email.isNullOrBlank() || name.isNullOrBlank() || surname.isNullOrBlank() || room.isNullOrBlank() || dormitoryId.isNullOrBlank()) {
                // error
            } else {
                viewModelScope.launch {
                    when (editStudentProfileUseCase.execute(
                        StudentEditRequestBody(
                            email,
                            name,
                            surname,
                            room,
                            dormitoryId
                        )
                    )) {
                        is Resource.Success -> {
                        }

                        else -> {

                        }
                    }
                }
            }
        } else {
            viewModelScope.launch {
                when (editStudentProfileUseCase.execute(
                    StudentEditRequestBody(
                        email ?: liveData.value?.email,
                        name ?: liveData.value?.name ?: "",
                        surname ?: liveData.value?.surname ?: "",
                        room ?: liveData.value?.room ?: "",
                        dormitoryId ?: liveData.value?.dormitoryId ?: ""
                    )
                )) {
                    is Resource.Success -> {
                    }

                    else -> {

                    }
                }
            }
        }
    }
}