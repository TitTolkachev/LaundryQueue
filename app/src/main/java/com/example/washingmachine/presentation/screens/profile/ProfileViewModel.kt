package com.example.washingmachine.presentation.screens.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.washingmachine.domain.model.AdminInfo
import com.example.washingmachine.domain.model.StudentInfo
import com.example.washingmachine.domain.usecase.remote.GetAdminProfileUseCase
import com.example.washingmachine.domain.usecase.remote.GetStudentProfileUseCase
import com.example.washingmachine.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(
    private val getStudentProfileUseCase: GetStudentProfileUseCase,
    private val getAdminProfileUseCase: GetAdminProfileUseCase
) : ViewModel() {
    private val studentLiveData = MutableLiveData(StudentInfo())
    private val adminLiveData = MutableLiveData(AdminInfo())

    fun getStudentLiveData(): LiveData<StudentInfo> = studentLiveData

    fun getAdminLiveData(): LiveData<AdminInfo> = adminLiveData

    fun refreshData() {
        viewModelScope.launch {
            // if role == student
            when (val response = getStudentProfileUseCase.execute()) {
                is Resource.Success -> {
                    studentLiveData.postValue(response.data)
                }

                is Resource.Exception -> {

                }

                is Resource.NetworkError -> {

                }

                is Resource.ValidationError -> {

                }
            }
        }
    }
}