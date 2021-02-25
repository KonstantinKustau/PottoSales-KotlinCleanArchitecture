package com.thebestdiscountandroid.features.login.data

import com.thebestdiscountandroid.core.exception.Failure
import com.thebestdiscountandroid.core.functional.Either
import com.thebestdiscountandroid.core.platform.BaseNetwork
import com.thebestdiscountandroid.core.platform.NetworkHandler
import com.thebestdiscountandroid.features.login.domain.LoginRepository
import com.thebestdiscountandroid.features.login.domain.UserLoginProperties
import com.thebestdiscountandroid.features.login.domain.UserLoginPropertiesEntity
import javax.inject.Inject

class LoginRepositoryImpl
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
            false, null -> Either.Left(
                Failure.NetworkConnection
            )
        }
}