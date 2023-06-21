package com.example.washingmachine.presentation.screens.editprofile.person

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.washingmachine.data.remote.dto.DormitoryDto
import com.example.washingmachine.domain.model.AdminEditRequestBody
import com.example.washingmachine.domain.model.AdminInfo
import com.example.washingmachine.domain.usecase.remote.EditAdminProfileUseCase
import com.example.washingmachine.domain.usecase.remote.GetAdminProfileUseCase
import com.example.washingmachine.domain.usecase.remote.GetDormitoriesUseCase
import com.example.washingmachine.domain.util.Resource
import kotlinx.coroutines.launch

class EditPersonProfileViewModel(
    private val getAdminProfileUseCase: GetAdminProfileUseCase,
    private val editAdminProfileUseCase: EditAdminProfileUseCase,
    private val getDormitoriesUseCase: GetDormitoriesUseCase
) : ViewModel() {

    private val liveData = MutableLiveData(AdminInfo())
    private val dormitoriesLiveData = MutableLiveData(listOf(DormitoryDto()))

    private val navigateToMainScreen = MutableLiveData(false)

    private val navigateToProfileScreen = MutableLiveData(false)

    private var mustFillAllFields = false

    var dormitories = listOf<DormitoryDto>()

    fun setWorkingType(mustFillAllFields: Boolean) {
        this.mustFillAllFields = mustFillAllFields
    }

    fun getProfileLiveData(): LiveData<AdminInfo> = liveData

    fun getDormitoriesLiveData(): LiveData<List<DormitoryDto>> = dormitoriesLiveData

    fun getNavigateToMainScreenLiveData(): LiveData<Boolean> = navigateToMainScreen

    fun getNavigateToProfileScreenLiveData(): LiveData<Boolean> = navigateToProfileScreen

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

            when (val data = getAdminProfileUseCase.execute()) {
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
        dormitoryId: String? = null
    ) {
        Log.d("!!!!", dormitoryId.toString())
        if (mustFillAllFields) {
            if (email.isNullOrBlank() || name.isNullOrBlank() || surname.isNullOrBlank() || dormitoryId.isNullOrBlank()) {
                // error
            } else {
                viewModelScope.launch {
                    when (editAdminProfileUseCase.execute(
                        AdminEditRequestBody(
                            email,
                            name,
                            surname,
                            dormitoryId
                        )
                    )) {
                        is Resource.Success -> {
                            navigateToMainScreen.postValue(true)
                        }

                        else -> {

                        }
                    }
                }
            }
        } else {
            Log.d("!!!!1", dormitoryId.toString())

            val a = AdminEditRequestBody(
                if (!email.isNullOrBlank()) email else liveData.value?.email,
                if (!name.isNullOrBlank()) name else "null",
                if (!surname.isNullOrBlank()) surname else "null",
                dormitoryId ?: "null"
            )

            Log.d("!!!!2", a.toString())

            viewModelScope.launch {
                when (editAdminProfileUseCase.execute(
                    a
                )) {
                    is Resource.Success -> {
                        navigateToProfileScreen.postValue(true)
                    }

                    else -> {

                    }
                }
            }
        }
    }
}