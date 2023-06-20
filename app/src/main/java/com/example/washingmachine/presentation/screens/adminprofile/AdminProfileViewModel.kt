package com.example.washingmachine.presentation.screens.adminprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.washingmachine.domain.model.AdminInfo
import com.example.washingmachine.domain.usecase.local.ClearLocalStorageUseCase
import com.example.washingmachine.domain.usecase.local.GetTokenFromLocalStorageUseCase
import com.example.washingmachine.domain.usecase.remote.GetAdminProfileUseCase
import com.example.washingmachine.domain.usecase.remote.LogoutUseCase
import com.example.washingmachine.domain.util.Resource
import kotlinx.coroutines.launch

class AdminProfileViewModel(
    private val getAdminProfileUseCase: GetAdminProfileUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase,
    private val clearLocalStorageUseCase: ClearLocalStorageUseCase
) : ViewModel() {

    private val navigateToAuthLiveData = MutableLiveData(false)
    private val profileLiveData = MutableLiveData(AdminInfo())

    fun getLiveDataForAuthNavigation(): LiveData<Boolean> = navigateToAuthLiveData

    fun getLiveDataForProfile(): LiveData<AdminInfo> = profileLiveData


    fun logout() {
        viewModelScope.launch {
            when (logoutUseCase.execute(getTokenFromLocalStorageUseCase.execute()?.refresh ?: "")) {
                is Resource.Success -> {
                    clearLocalStorageUseCase.execute()
                    navigateToAuthLiveData.postValue(true)
                }

                else -> {

                }
            }
        }
    }

    fun refreshData() {
        viewModelScope.launch {
            when (val data = getAdminProfileUseCase.execute()) {
                is Resource.Success -> {
                    profileLiveData.postValue(data.data)
                }

                else -> {

                }
            }
        }
    }
}