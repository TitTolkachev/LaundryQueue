package com.example.washingmachine.data.remote.requests.profile

import com.example.washingmachine.data.remote.AuthNetwork
import com.example.washingmachine.data.remote.dto.StudentProfileRequestDto
import com.example.washingmachine.domain.model.StudentEditRequestBody
import com.example.washingmachine.domain.model.StudentInfo
import com.example.washingmachine.domain.repository.StudentProfileRepository
import com.example.washingmachine.domain.usecase.group.AuthNetworkUseCases
import com.example.washingmachine.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StudentProfileRepositoryImpl(useCases: AuthNetworkUseCases) : StudentProfileRepository {

    private val api = AuthNetwork.getStudentProfileApi(useCases)

    override suspend fun getInfo(): Resource<StudentInfo> {
        var result: Resource<StudentInfo>

        withContext(Dispatchers.IO) {
            result = try {
                val request = api.getInfo()
                if (request.isSuccessful) {
                    Resource.Success(
                        StudentInfo(
                            request.body()?.id,
                            request.body()?.dormitoryId,
                            request.body()?.email,
                            request.body()?.name,
                            request.body()?.surname,
                            request.body()?.room,
                            request.body()?.money,
                        )
                    )
                } else {
                    Resource.NetworkError(request.message())
                }
            } catch (e: Exception) {

                Resource.Exception(e)
            }
        }

        return result
    }

    override suspend fun edit(body: StudentEditRequestBody): Resource<StudentInfo> {
        var result: Resource<StudentInfo>

        withContext(Dispatchers.IO) {
            result = try {
                val request = api.edit(
                    StudentProfileRequestDto(
                        body.email,
                        body.name,
                        body.surname,
                        body.room,
                        body.dormitoryId,
                    )
                )
                if (request.isSuccessful) {
                    Resource.Success(
                        StudentInfo(
                            request.body()?.id,
                            request.body()?.dormitoryId,
                            request.body()?.email,
                            request.body()?.name,
                            request.body()?.surname,
                            request.body()?.room,
                            request.body()?.money,
                        )
                    )
                } else {
                    Resource.NetworkError(request.message())
                }
            } catch (e: Exception) {
                Resource.Exception(e)
            }
        }

        return result
    }
}