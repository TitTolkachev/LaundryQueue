package com.example.washingmachine.presentation.screens.launch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.washingmachine.domain.model.FirstEnterStatus
import com.example.washingmachine.domain.model.Roles
import com.example.washingmachine.domain.usecase.local.GetFirstEnterStatusUseCase
import com.example.washingmachine.domain.usecase.local.GetTokenFromLocalStorageUseCase
import com.example.washingmachine.domain.usecase.remote.GetAdminProfileUseCase
import com.example.washingmachine.domain.usecase.remote.GetStudentProfileUseCase
import com.example.washingmachine.domain.util.Resource
import kotlinx.coroutines.launch

class LaunchViewModel(
    private val getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase,
    private val getStudentProfileUseCase: GetStudentProfileUseCase,
    private val getAdminProfileUseCase: GetAdminProfileUseCase,
    getFirstEnterStatusUseCase: GetFirstEnterStatusUseCase
) : ViewModel() {

    private val _navigateToOnBoarding = MutableLiveData(false)
    val navigateToOnBoarding: LiveData<Boolean> = _navigateToOnBoarding

    private val _navigateToSignIn = MutableLiveData(false)
    val navigateToSignIn: LiveData<Boolean> = _navigateToSignIn

    private val _navigateToMain = MutableLiveData(false)
    val navigateToMain: LiveData<Boolean> = _navigateToMain

    init {
        val status = getFirstEnterStatusUseCase.execute()

        if (status == FirstEnterStatus.THIS_IS_FIRST_ENTER) {
            _navigateToOnBoarding.value = true
        } else {
            doNotFirstEnterLogic()
        }
    }

    private fun doNotFirstEnterLogic() {

        viewModelScope.launch {
            when (getTokenFromLocalStorageUseCase.execute()?.role) {
                Roles.ROLE_STUDENT -> {
                    when (getStudentProfileUseCase.execute()) {
                        is Resource.Success -> {
                            _navigateToMain.postValue(true)
                        }

                        else -> {
                            _navigateToSignIn.postValue(true)
                        }
                    }
                }

                Roles.ROLE_EMPLOYEE -> {
                    when (val profileRequest = getAdminProfileUseCase.execute()) {
                        is Resource.Success -> {
                            if (profileRequest.data?.role == Roles.ROLE_EMPLOYEE.name)
                                _navigateToMain.postValue(true)
                            else
                                _navigateToSignIn.postValue(true)
                        }

                        else -> {
                            _navigateToSignIn.postValue(true)
                        }
                    }
                }

                Roles.ROLE_ADMIN -> {
                    when (val profileRequest = getAdminProfileUseCase.execute()) {
                        is Resource.Success -> {
                            if (profileRequest.data?.role == Roles.ROLE_ADMIN.name)
                                _navigateToMain.postValue(true)
                            else
                                _navigateToSignIn.postValue(true)
                        }

                        else -> {
                            _navigateToSignIn.postValue(true)
                        }
                    }
                }

                else -> {
                    _navigateToSignIn.postValue(true)
                }
            }
        }
    }
}