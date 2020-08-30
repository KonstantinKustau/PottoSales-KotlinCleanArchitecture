package com.thebestdiscountandroid.features.login.domain

import com.thebestdiscountandroid.core.exception.Failure
import com.thebestdiscountandroid.core.exception.Failure.NetworkConnection
import com.thebestdiscountandroid.core.exception.Failure.ServerError
import com.thebestdiscountandroid.core.functional.Either
import com.thebestdiscountandroid.core.functional.Either.Left
import com.thebestdiscountandroid.core.functional.Either.Right
import com.thebestdiscountandroid.core.platform.BaseNetwork
import com.thebestdiscountandroid.core.platform.NetworkHandler
import com.thebestdiscountandroid.features.login.data.LoginService
import retrofit2.Call
import javax.inject.Inject

interface LoginRepository {

    fun signIn(email: String, password: String): Either<Failure, UserLoginProperties>

    fun signUp(email: String, password: String): Either<Failure, UserLoginProperties>

    class Network
    @Inject constructor(
        private val networkHandler: NetworkHandler,
        private val service: LoginService
    ) : LoginRepository, BaseNetwork() {

        override fun signIn(email: String, password: String): Either<Failure, UserLoginProperties> =
            loginRequest(email, password)

        override fun signUp(email: String, password: String): Either<Failure, UserLoginProperties> =
            loginRequest(email, password)

        private fun loginRequest(
            email: String,
            password: String
        ): Either<Failure, UserLoginProperties> =
            when (networkHandler.isConnected) {
                true -> request(
                    service.signUp(email, password),
                    { userPropertiesEntity -> userPropertiesEntity.toUserLoginProperties() },
                    UserLoginPropertiesEntity.empty()
                )
                false, null -> Left(NetworkConnection)
            }
    }
}