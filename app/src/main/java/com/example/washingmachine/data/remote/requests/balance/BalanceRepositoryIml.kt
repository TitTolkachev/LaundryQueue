package com.example.washingmachine.data.remote.requests.balance

import android.util.Log
import com.example.washingmachine.data.remote.AuthNetwork
import com.example.washingmachine.data.remote.Network
import com.example.washingmachine.data.remote.dto.PersonDto
import com.example.washingmachine.domain.model.AdminInfo
import com.example.washingmachine.domain.model.StudentInfo
import com.example.washingmachine.domain.repository.BalanceRepository
import com.example.washingmachine.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BalanceRepositoryIml(authNetwork: AuthNetwork) : BalanceRepository {

    private val api = authNetwork.getBalanceApi()

    override suspend fun topUpBalance(delta: Int): Resource<PersonDto> {
        var result: Resource<PersonDto>

        withContext(Dispatchers.IO) {
            result = try {
                val request = api.topUpBalance(delta)


                if (request.isSuccessful) {
                    val data = request.body()
                    Log.d("!!!!", data.toString())

                    Resource.Success(
                        PersonDto(
                            data?.id,
                            data?.email,
                            data?.name,
                            data?.surname,
                            data?.money,
                            data?.role,
                            data?.dormitoryId,
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

    override suspend fun takeOutBalance(delta: Int): Resource<PersonDto> {
        var result: Resource<PersonDto>

        withContext(Dispatchers.IO) {
            result = try {
                val request = api.takeOutBalance(delta)

                if (request.isSuccessful) {
                    val data = request.body()

                    Resource.Success(
                        PersonDto(
                            data?.id,
                            data?.email,
                            data?.name,
                            data?.surname,
                            data?.money,
                            data?.role,
                            data?.dormitoryId,
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