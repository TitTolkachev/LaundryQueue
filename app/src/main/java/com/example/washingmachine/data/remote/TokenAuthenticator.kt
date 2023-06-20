package com.example.washingmachine.data.remote

import android.util.Log
import com.example.washingmachine.domain.model.Roles
import com.example.washingmachine.domain.model.Token
import com.example.washingmachine.domain.usecase.local.GetTokenFromLocalStorageUseCase
import com.example.washingmachine.domain.usecase.local.SaveTokenToLocalStorageUseCase
import com.example.washingmachine.domain.usecase.remote.RefreshTokenUseCase
import com.example.washingmachine.domain.util.Resource
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    private val getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase,
    private val saveTokenToLocalStorageUseCase: SaveTokenToLocalStorageUseCase,
    private val refreshTokenUseCase: RefreshTokenUseCase
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {

        val localToken = getTokenFromLocalStorageUseCase.execute() ?: return null

        var remoteToken: Token? = null
        runBlocking {
            when (val refreshRequest = refreshTokenUseCase.execute(localToken.refresh)) {
                is Resource.Success -> {
                    remoteToken = refreshRequest.data
                }

                else -> {
                    Log.e("REFRESH TOKEN ERROR", refreshRequest.message.toString())
                }
            }
        }

        saveTokenToLocalStorageUseCase.execute(
            Token(
                remoteToken?.access ?: "",
                remoteToken?.refresh ?: "",
                remoteToken?.role ?: Roles.ROLE_STUDENT
            )
        )

        return if (response.responseCount > 1) {
            null
        } else {
            response.request.newBuilder()
                .header("Authorization", "Bearer ${remoteToken?.access ?: ""}")
                .build()
        }
    }

    private val Response.responseCount: Int
        get() = generateSequence(this) { it.priorResponse }.count()
}