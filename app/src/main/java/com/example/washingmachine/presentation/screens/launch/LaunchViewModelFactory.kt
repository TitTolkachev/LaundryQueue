package com.example.washingmachine.presentation.screens.launch

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.washingmachine.data.local.prefs.enter.EnterRepositoryImpl
import com.example.washingmachine.data.local.prefs.enter.EnterStorageImpl
import com.example.washingmachine.data.local.prefs.token.TokenRepositoryImpl
import com.example.washingmachine.data.local.prefs.token.TokenStorageImpl
import com.example.washingmachine.data.remote.requests.auth.AuthRepositoryImpl
import com.example.washingmachine.data.remote.requests.profile.AdminProfileRepositoryImpl
import com.example.washingmachine.data.remote.requests.profile.StudentProfileRepositoryImpl
import com.example.washingmachine.domain.usecase.group.AuthNetworkUseCases
import com.example.washingmachine.domain.usecase.local.GetFirstEnterStatusUseCase
import com.example.washingmachine.domain.usecase.local.GetTokenFromLocalStorageUseCase
import com.example.washingmachine.domain.usecase.local.SaveTokenToLocalStorageUseCase
import com.example.washingmachine.domain.usecase.remote.GetAdminProfileUseCase
import com.example.washingmachine.domain.usecase.remote.GetStudentProfileUseCase
import com.example.washingmachine.domain.usecase.remote.RefreshTokenUseCase

class LaunchViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val tokenRepository = TokenRepositoryImpl(TokenStorageImpl(context))

    private val getTokenFromLocalStorageUseCase = GetTokenFromLocalStorageUseCase(tokenRepository)
    private val saveTokenToLocalStorageUseCase = SaveTokenToLocalStorageUseCase(tokenRepository)
    private val refreshTokenUseCase = RefreshTokenUseCase(AuthRepositoryImpl())

    private val useCases = AuthNetworkUseCases(
        getTokenFromLocalStorageUseCase,
        saveTokenToLocalStorageUseCase,
        refreshTokenUseCase
    )

    private val studentProfileRepository = StudentProfileRepositoryImpl(useCases)
    private val adminProfileRepository = AdminProfileRepositoryImpl(useCases)

    private val getStudentProfileUseCase = GetStudentProfileUseCase(studentProfileRepository)
    private val getAdminProfileUseCase = GetAdminProfileUseCase(adminProfileRepository)

    private val enterRepository = EnterRepositoryImpl(EnterStorageImpl(context))

    private val getFirstEnterStatusUseCase = GetFirstEnterStatusUseCase(enterRepository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LaunchViewModel(
            getTokenFromLocalStorageUseCase,
            getStudentProfileUseCase,
            getAdminProfileUseCase,
            getFirstEnterStatusUseCase
        ) as T
    }
}