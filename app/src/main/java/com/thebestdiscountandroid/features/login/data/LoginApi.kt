package com.thebestdiscountandroid.features.login.data

import com.thebestdiscountandroid.features.login.domain.UserLoginPropertiesEntity
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

internal interface LoginApi {
    companion object {
        private const val PARAM_USER_EMAIL = "email"
        private const val PARAM_USER_PASSWORD = "password"
        private const val SIGN_IN = "signIn/"
        private const val SIGN_UP = "signUp/"
    }

    @POST(SIGN_IN)
    fun signIn(
        @Query(PARAM_USER_EMAIL) email: String,
        @Query(PARAM_USER_PASSWORD) password: String
    ): Call<UserLoginPropertiesEntity>

    @POST(SIGN_UP)
    fun signUp(
        @Query(PARAM_USER_EMAIL) email: String,
        @Query(PARAM_USER_PASSWORD) password: String
    ): Call<UserLoginPropertiesEntity>
}
