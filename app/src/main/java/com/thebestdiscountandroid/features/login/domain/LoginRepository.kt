package com.thebestdiscountandroid.features.login.domain

import com.thebestdiscountandroid.core.exception.Failure
import com.thebestdiscountandroid.core.functional.Either

interface LoginRepository {

    fun signIn(email: String, password: String): Either<Failure, UserLoginPropertiesEntity>

    fun signUp(email: String, password: String): Either<Failure, UserLoginPropertiesEntity>

}