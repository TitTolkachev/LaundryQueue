package com.example.washingmachine.data.remote.requests.balance

import com.example.washingmachine.data.remote.AuthNetwork
import com.example.washingmachine.data.remote.Network
import com.example.washingmachine.domain.model.AdminInfo
import com.example.washingmachine.domain.model.StudentInfo
import com.example.washingmachine.domain.repository.BalanceRepository
import com.example.washingmachine.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BalanceRepositoryIml(authNetwork: AuthNetwork) : BalanceRepository {

    private val api = authNetwork.getBalanceApi()

    override suspend fun topUpBalance(delta: Int): Resource<StudentInfo> {
        var result: Resource<StudentInfo>

        withContext(Dispatchers.IO) {
            result = try {
                val request = api.topUpBalance(delta)

                if (request.isSuccessful) {
                    val data = request.body()

                    Resource.Success(
                        StudentInfo(
                            data?.id,
                            data?.dormitoryId,
                            data?.email,
                            data?.name,
                            data?.surname,
                            data?.room,
                            data?.money
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

    override suspend fun takeOutBalance(delta: Int): Resource<AdminInfo> {
        var result: Resource<AdminInfo>

        withContext(Dispatchers.IO) {
            result = try {
                val request = api.takeOutBalance(delta)

                if (request.isSuccessful) {
                    val data = request.body()

                    Resource.Success(
                        AdminInfo(
                            data?.id,
                            data?.email,
                            data?.name,
                            data?.surname,
                            data?.money,
                            data?.role,
                            data?.dormitoryId
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