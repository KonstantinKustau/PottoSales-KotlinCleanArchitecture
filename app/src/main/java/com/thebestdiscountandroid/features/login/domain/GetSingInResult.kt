package com.thebestdiscountandroid.features.login.domain

import com.thebestdiscountandroid.core.exception.Failure
import com.thebestdiscountandroid.core.functional.Either
import com.thebestdiscountandroid.core.interactor.UseCase
import javax.inject.Inject

class GetSingInResult
@Inject constructor(private val loginRepository: LoginRepository) :
    UseCase<UserLoginProperties, GetSingInResult.Params>() {

    data class Params(val email: String, val password: String)

    override suspend fun run(params: Params): Either<Failure, UserLoginProperties> =
        loginRepository.signIn(params.email, params.password)
}