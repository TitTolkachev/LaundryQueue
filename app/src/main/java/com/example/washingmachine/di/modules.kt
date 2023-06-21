package com.example.washingmachine.di

import com.example.washingmachine.data.local.prefs.enter.EnterRepositoryImpl
import com.example.washingmachine.data.local.prefs.enter.EnterStorage
import com.example.washingmachine.data.local.prefs.enter.EnterStorageImpl
import com.example.washingmachine.data.local.prefs.token.TokenRepositoryImpl
import com.example.washingmachine.data.local.prefs.token.TokenStorage
import com.example.washingmachine.data.local.prefs.token.TokenStorageImpl
import com.example.washingmachine.data.remote.AuthInterceptor
import com.example.washingmachine.data.remote.AuthNetwork
import com.example.washingmachine.data.remote.Network
import com.example.washingmachine.data.remote.TokenAuthenticator
import com.example.washingmachine.data.remote.requests.auth.AuthLogoutRepositoryImpl
import com.example.washingmachine.data.remote.requests.auth.AuthRepositoryImpl
import com.example.washingmachine.data.remote.requests.balance.BalanceRepositoryIml
import com.example.washingmachine.data.remote.requests.devicetoken.DeviceTokenRepositoryImpl
import com.example.washingmachine.data.remote.requests.profile.AdminProfileRepositoryImpl
import com.example.washingmachine.data.remote.requests.profile.StudentProfileRepositoryImpl
import com.example.washingmachine.domain.repository.AdminProfileRepository
import com.example.washingmachine.domain.repository.AuthLogoutRepository
import com.example.washingmachine.domain.repository.AuthRepository
import com.example.washingmachine.domain.repository.BalanceRepository
import com.example.washingmachine.domain.repository.DeviceTokenRepository
import com.example.washingmachine.domain.repository.EnterRepository
import com.example.washingmachine.domain.repository.MachinesRepository
import com.example.washingmachine.domain.repository.QueueRepository
import com.example.washingmachine.domain.repository.StudentProfileRepository
import com.example.washingmachine.domain.repository.TokenRepository
import com.example.washingmachine.domain.usecase.local.ClearLocalStorageUseCase
import com.example.washingmachine.domain.usecase.local.GetFirstEnterStatusUseCase
import com.example.washingmachine.domain.usecase.local.GetTokenFromLocalStorageUseCase
import com.example.washingmachine.domain.usecase.local.SaveTokenToLocalStorageUseCase
import com.example.washingmachine.domain.usecase.local.SetFirstEnterPassedUseCase
import com.example.washingmachine.domain.usecase.remote.BookSlotUseCase
import com.example.washingmachine.domain.usecase.remote.ChangeMachineStatusUseCase
import com.example.washingmachine.domain.usecase.remote.CheckOutQueueUseCase
import com.example.washingmachine.domain.usecase.remote.CreateMachineUseCase
import com.example.washingmachine.domain.usecase.remote.DeleteMachineUseCase
import com.example.washingmachine.domain.usecase.remote.EditAdminProfileUseCase
import com.example.washingmachine.domain.usecase.remote.EditStudentProfileUseCase
import com.example.washingmachine.domain.usecase.remote.GetAdminProfileUseCase
import com.example.washingmachine.domain.usecase.remote.GetDormitoriesUseCase
import com.example.washingmachine.domain.usecase.remote.GetMachineQueueUseCase
import com.example.washingmachine.domain.usecase.remote.GetMachinesUseCase
import com.example.washingmachine.domain.usecase.remote.GetStudentProfileUseCase
import com.example.washingmachine.domain.usecase.remote.LogoutUseCase
import com.example.washingmachine.domain.usecase.remote.RefreshTokenUseCase
import com.example.washingmachine.domain.usecase.remote.SendDeviceTokenUseCase
import com.example.washingmachine.domain.usecase.remote.SignInUseCase
import com.example.washingmachine.domain.usecase.remote.StartMachineUseCase
import com.example.washingmachine.domain.usecase.remote.TakeOutBalanceUseCase
import com.example.washingmachine.domain.usecase.remote.TopUpBalanceUseCase
import com.example.washingmachine.presentation.screens.admin.AdminViewModel
import com.example.washingmachine.presentation.screens.adminprofile.AdminProfileViewModel
import com.example.washingmachine.presentation.screens.auth.AuthViewModel
import com.example.washingmachine.presentation.screens.employee.EmployeeViewModel
import com.example.washingmachine.presentation.screens.launch.LaunchViewModel
import com.example.washingmachine.presentation.screens.main.MainViewModel
import com.example.washingmachine.presentation.screens.studentprofile.StudentProfileViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val remote = module {
    single { Network() }
    single { AuthNetwork(get(), get()) }
}

val retrofit = module {
    single { AuthInterceptor(get()) }
    single { TokenAuthenticator(get(), get(), get()) }
}


val remoteUseCases = module {
    factory { EditAdminProfileUseCase(get()) }
    factory { EditStudentProfileUseCase(get()) }
    factory { GetAdminProfileUseCase(get()) }
    factory { GetStudentProfileUseCase(get()) }
    factory { LogoutUseCase(get()) }
    factory { RefreshTokenUseCase(get()) }
    factory { SendDeviceTokenUseCase(get()) }
    factory { SignInUseCase(get()) }
    factory { TakeOutBalanceUseCase(get()) }
    factory { TopUpBalanceUseCase(get()) }
    factory { GetDormitoriesUseCase(get()) }

    // Machines
    factory { ChangeMachineStatusUseCase(get()) }
    factory { CreateMachineUseCase(get()) }
    factory { DeleteMachineUseCase(get()) }
    factory { GetMachinesUseCase(get()) }

    // Queue
    factory { BookSlotUseCase(get()) }
    factory { CheckOutQueueUseCase(get()) }
    factory { GetMachineQueueUseCase(get()) }
    factory { StartMachineUseCase(get()) }
}

val localUseCases = module {
    factory { GetFirstEnterStatusUseCase(get()) }
    factory { GetTokenFromLocalStorageUseCase(get()) }
    factory { SaveTokenToLocalStorageUseCase(get()) }
    factory { SetFirstEnterPassedUseCase(get()) }
    factory { ClearLocalStorageUseCase(get()) }
}


val repositories = module {
    factory<EnterRepository> { EnterRepositoryImpl(get()) }
    factory<TokenRepository> { TokenRepositoryImpl(get()) }

    factory<AuthRepository> { AuthRepositoryImpl(get()) }
    factory<AuthLogoutRepository> { AuthLogoutRepositoryImpl(get()) }
    factory<BalanceRepository> { BalanceRepositoryIml(get()) }
    factory<DeviceTokenRepository> { DeviceTokenRepositoryImpl(get()) }
    factory<AdminProfileRepository> { AdminProfileRepositoryImpl(get()) }
    factory<StudentProfileRepository> { StudentProfileRepositoryImpl(get()) }
    factory<MachinesRepository> { MachinesRepositoryImpl(get()) }
    factory<QueueRepository> { QueueRepositoryImpl(get()) }

    factory<DormitoryRepository> { DormitoryRepositoryImpl(get()) }
}

val storage = module {
    single<TokenStorage> { TokenStorageImpl(androidContext()) }
    single<EnterStorage> { EnterStorageImpl(androidContext()) }
}


val viewModels = module {
    viewModel { AuthViewModel(get(), get(), get(), get(), get()) }
    viewModel { LaunchViewModel(get(), get(), get(), get()) }
    viewModel { MainViewModel(get()) }
    viewModel { AdminViewModel() }

    viewModel { StudentProfileViewModel(get(), get(), get(), get(), get(), get()) }
    viewModel { AdminProfileViewModel(get(), get(), get(), get(), get()) }
    viewModel { EmployeeViewModel() }

    viewModel { EditStudentProfileViewModel(get(), get(), get()) }

}