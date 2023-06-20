package com.example.washingmachine.data.local.prefs.token

import android.content.Context
import android.content.SharedPreferences
import com.example.washingmachine.data.local.prefs.model.TokenModel

private const val APP_PREFERENCES = "preferences_settings"
private const val ACCESS_TOKEN_NAME = "access_token"
private const val REFRESH_TOKEN_NAME = "refresh_token"
private const val ROLE_NAME = "role_name"

class TokenStorageImpl(context: Context) : TokenStorage {

    private val preferences: SharedPreferences =
        context.getSharedPreferences(
            APP_PREFERENCES,
            Context.MODE_PRIVATE
        )

    override fun getToken(): TokenModel? {
        val accessToken = preferences.getString(ACCESS_TOKEN_NAME, null)
        val refreshToken = preferences.getString(REFRESH_TOKEN_NAME, null)
        val roleName = preferences.getString(ROLE_NAME, null)
        return if (accessToken != null && refreshToken != null)
            TokenModel(accessToken, refreshToken, roleName ?: "")
        else null
    }

    override fun saveToken(token: TokenModel) {
        preferences.edit().putString(ACCESS_TOKEN_NAME, token.access).apply()
        preferences.edit().putString(REFRESH_TOKEN_NAME, token.refresh).apply()
        preferences.edit().putString(ROLE_NAME, token.role).apply()
    }

    override fun clearToken(){
        preferences.edit().putString(ACCESS_TOKEN_NAME, "").apply()
        preferences.edit().putString(ACCESS_TOKEN_NAME, "").apply()
        preferences.edit().putString(ACCESS_TOKEN_NAME, "").apply()
    }

}