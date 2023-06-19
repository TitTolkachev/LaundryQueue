package com.example.washingmachine.presentation.screens.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.washingmachine.domain.model.Roles
import com.example.washingmachine.domain.model.Token
import com.example.washingmachine.domain.usecase.local.SaveTokenToLocalStorageUseCase
import com.example.washingmachine.domain.usecase.local.SetFirstEnterPassedUseCase
import com.example.washingmachine.domain.usecase.remote.SignInUseCase
import com.example.washingmachine.domain.util.Resource
import kotlinx.coroutines.launch

class AuthViewModel(
    private val signInUseCase: SignInUseCase,
    private val saveTokenToLocalStorageUseCase: SaveTokenToLocalStorageUseCase,
    setFirstEnterPassedUseCase: SetFirstEnterPassedUseCase
) : ViewModel() {

    private val _navigateToMain = MutableLiveData(false)
    val navigateToMain: LiveData<Boolean> = _navigateToMain

    private val _navigateToAdmin = MutableLiveData(false)
    val navigateToAdmin: LiveData<Boolean> = _navigateToAdmin

    private val _navigateToEmployee = MutableLiveData(false)
    val navigateToEmployee: LiveData<Boolean> = _navigateToEmployee

    init {
        setFirstEnterPassedUseCase.execute()
    }

    fun signIn(email: String, password: String) {

        //TODO(валидация)

        viewModelScope.launch {
            when (val request = signInUseCase.execute(email = email, password = password)) {
                is Resource.Success -> {
                    if (request.data != null) {
                        saveTokenToLocalStorageUseCase.execute(
                            Token(
                                request.data.access,
                                request.data.refresh,
                                request.data.role
                            )
                        )

                        when (request.data.role) {
                            Roles.ROLE_STUDENT -> {
                                _navigateToMain.postValue(true)
                            }

                            Roles.ROLE_EMPLOYEE -> {
                                _navigateToEmployee.postValue(true)
                            }

                            Roles.ROLE_ADMIN -> {
                                _navigateToAdmin.postValue(true)
                            }
                        }
                    }
                }

                else -> {}
            }
        }
    }
}