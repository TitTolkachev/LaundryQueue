package com.example.washingmachine.presentation.screens.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.washingmachine.domain.model.DeviceToken
import com.example.washingmachine.domain.model.Roles
import com.example.washingmachine.domain.model.Token
import com.example.washingmachine.domain.usecase.local.SaveTokenToLocalStorageUseCase
import com.example.washingmachine.domain.usecase.local.SetFirstEnterPassedUseCase
import com.example.washingmachine.domain.usecase.remote.GetAdminProfileUseCase
import com.example.washingmachine.domain.usecase.remote.GetStudentProfileUseCase
import com.example.washingmachine.domain.usecase.remote.SendDeviceTokenUseCase
import com.example.washingmachine.domain.usecase.remote.SignInUseCase
import com.example.washingmachine.domain.util.Resource
import kotlinx.coroutines.launch

class AuthViewModel(
    private val signInUseCase: SignInUseCase,
    private val saveTokenToLocalStorageUseCase: SaveTokenToLocalStorageUseCase,
    private val sendDeviceTokenUseCase: SendDeviceTokenUseCase,
    setFirstEnterPassedUseCase: SetFirstEnterPassedUseCase,
    private val getStudentProfileUseCase: GetStudentProfileUseCase,
    private val getAdminProfileUseCase: GetAdminProfileUseCase
) : ViewModel() {

    private val _navigateToMain = MutableLiveData(false)
    val navigateToMain: LiveData<Boolean> = _navigateToMain

    private val _navigateEditStudentProfile = MutableLiveData(false)
    val navigateEditStudentProfile: LiveData<Boolean> = _navigateEditStudentProfile

    private val _navigateEditPersonProfile = MutableLiveData(false)
    val navigateEditPersonProfile: LiveData<Boolean> = _navigateEditPersonProfile

    private val _navigateToAdmin = MutableLiveData(false)
    val navigateToAdmin: LiveData<Boolean> = _navigateToAdmin

    private val _navigateToEmployee = MutableLiveData(false)
    val navigateToEmployee: LiveData<Boolean> = _navigateToEmployee

    private val _shouldSendDeviceToken = MutableLiveData(false)
    val shouldSendDeviceToken: LiveData<Boolean> = _shouldSendDeviceToken

    private val _showSignInError = MutableLiveData(false)
    val showSignInError: LiveData<Boolean> = _showSignInError

    private val _showDeviceToken = MutableLiveData(false)
    val showDeviceToken: LiveData<Boolean> = _showDeviceToken

    private lateinit var role: Roles

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
                        role = request.data.role
                        _shouldSendDeviceToken.postValue(true)
                    }
                }

                else -> {
                    _showSignInError.postValue(true)
                }
            }
        }
    }

    fun sendDeviceToken(token: String) {
        viewModelScope.launch {
            when (sendDeviceTokenUseCase.execute(DeviceToken(token))) {
                is Resource.Success -> {
                    when (role) {
                        Roles.ROLE_STUDENT -> {
                            when (val data = getStudentProfileUseCase.execute()) {
                                is Resource.Success -> {
                                    if (data.data?.name.isNullOrBlank()) {
                                        _navigateEditStudentProfile.postValue(true)
                                    } else {
                                        _navigateToMain.postValue(true)
                                    }
                                }

                                else -> {}
                            }
                        }

                        Roles.ROLE_EMPLOYEE -> {
                            when (val data = getAdminProfileUseCase.execute()) {
                                is Resource.Success -> {
                                    if (data.data?.name.isNullOrBlank()) {
                                        _navigateEditPersonProfile.postValue(true)
                                    } else {
                                        _navigateToEmployee.postValue(true)
                                    }
                                }

                                else -> {}
                            }

                        }

                        Roles.ROLE_ADMIN -> {
                            when (val data = getAdminProfileUseCase.execute()) {
                                is Resource.Success -> {
                                    if (data.data?.name.isNullOrBlank()) {
                                        _navigateEditPersonProfile.postValue(true)
                                    } else {
                                        _navigateToAdmin.postValue(true)
                                    }
                                }

                                else -> {}
                            }
                        }
                    }
                }

                else -> {
                    _showDeviceToken.postValue(true)
                }
            }
        }
    }
}