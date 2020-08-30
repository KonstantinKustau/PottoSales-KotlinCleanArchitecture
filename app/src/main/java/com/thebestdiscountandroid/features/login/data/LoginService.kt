package com.thebestdiscountandroid.features.login.data

import com.thebestdiscountandroid.features.login.domain.UserLoginPropertiesEntity
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginService
@Inject constructor(retrofit: Retrofit) : LoginApi {
    private val loginApi by lazy { retrofit.create(LoginApi::class.java) }

    override fun signIn(email: String, password: String): Call<UserLoginPropertiesEntity> =
        loginApi.signIn(email, password)

    override fun signUp(email: String, password: String): Call<UserLoginPropertiesEntity> =
        loginApi.signUp(email, password)
}
