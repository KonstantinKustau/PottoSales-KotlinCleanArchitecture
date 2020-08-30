package com.thebestdiscountandroid.features.settings.domain

import com.thebestdiscountandroid.core.exception.Failure
import com.thebestdiscountandroid.core.functional.Either
import com.thebestdiscountandroid.core.interactor.UseCase
import javax.inject.Inject

class GetLogoutResult
@Inject constructor(private val settingsRepository: SettingsRepository) :
    UseCase<Any, GetLogoutResult.Params>() {

    data class Params(val userId: Int)

    override suspend fun run(params: Params): Either<Failure, Any> =
        settingsRepository.logout(params.userId)
}