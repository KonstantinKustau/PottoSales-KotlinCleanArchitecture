package com.thebestdiscountandroid.features.settings.domain

import com.thebestdiscountandroid.core.exception.Failure
import com.thebestdiscountandroid.core.functional.Either
import com.thebestdiscountandroid.core.interactor.UseCase
import javax.inject.Inject

class UpdateUserProperties
@Inject constructor(private val settingsRepository: SettingsRepository) :
    UseCase<SettingsUpdateEntity, UpdateUserProperties.Params>() {

    data class Params(val userId: Int, val userLocalProperties: UserLocalProperties)

    override suspend fun run(params: Params): Either<Failure, SettingsUpdateEntity> =
        settingsRepository.updateUserProperties(params.userId, params.userLocalProperties)
}