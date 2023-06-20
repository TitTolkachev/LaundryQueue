package com.example.washingmachine.presentation.screens.studentprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.washingmachine.domain.model.StudentInfo
import com.example.washingmachine.domain.usecase.local.ClearLocalStorageUseCase
import com.example.washingmachine.domain.usecase.local.GetTokenFromLocalStorageUseCase
import com.example.washingmachine.domain.usecase.remote.GetStudentProfileUseCase
import com.example.washingmachine.domain.usecase.remote.LogoutUseCase
import com.example.washingmachine.domain.util.Resource
import kotlinx.coroutines.launch

class StudentProfileViewModel(
    private val getStudentProfileUseCase: GetStudentProfileUseCase,
    private val getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val clearLocalStorageUseCase: ClearLocalStorageUseCase
) : ViewModel() {

    private val navigateToAuth = MutableLiveData(false)

    private val studentLiveData = MutableLiveData(StudentInfo())

    fun getStudentProfileLiveData(): LiveData<StudentInfo> = studentLiveData

    fun getNavigationToAuthLiveData(): LiveData<Boolean> = navigateToAuth

    fun refreshData() {
        viewModelScope.launch {
            when (val data = getStudentProfileUseCase.execute()) {
                is Resource.Success -> {
                    clearLocalStorageUseCase.execute()
                    studentLiveData.postValue(data.data)
                }

                else -> {

                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            when (val data =
                logoutUseCase.execute(getTokenFromLocalStorageUseCase.execute()?.refresh ?: "")) {
                is Resource.Success -> {
                    navigateToAuth.postValue(true)
                }

                else -> {

                }
            }
        }
    }
}