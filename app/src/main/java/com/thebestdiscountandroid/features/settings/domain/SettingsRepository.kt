package com.thebestdiscountandroid.features.settings.domain

import com.thebestdiscountandroid.core.exception.Failure
import com.thebestdiscountandroid.core.functional.Either

interface SettingsRepository {

    fun logout(userId: Int): Either<Failure, Any>

    fun updateUserProperties(
        userId: Int,
        userLocalProperties: UserLocalProperties
    ): Either<Failure, SettingsUpdate>

}