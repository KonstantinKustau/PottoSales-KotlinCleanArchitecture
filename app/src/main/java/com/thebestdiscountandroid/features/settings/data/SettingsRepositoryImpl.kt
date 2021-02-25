package com.thebestdiscountandroid.features.settings.data

import com.thebestdiscountandroid.core.exception.Failure
import com.thebestdiscountandroid.core.functional.Either
import com.thebestdiscountandroid.core.platform.BaseNetwork
import com.thebestdiscountandroid.core.platform.NetworkHandler
import com.thebestdiscountandroid.features.settings.domain.SettingsRepository
import com.thebestdiscountandroid.features.settings.domain.SettingsUpdate
import com.thebestdiscountandroid.features.settings.domain.SettingsUpdateEntity
import com.thebestdiscountandroid.features.settings.domain.UserLocalProperties
import javax.inject.Inject

class SettingsRepositoryImpl
@Inject constructor(
    private val networkHandler: NetworkHandler,
    private val service: SettingsService
) : SettingsRepository, BaseNetwork() {

    override fun logout(userId: Int): Either<Failure, Any> {
        return when (networkHandler.isConnected) {
            true -> request(
                service.logout(userId),
                {},
                Any()
            )
            false, null -> Either.Left(
                Failure.NetworkConnection
            )
        }
    }

    override fun updateUserProperties(
        userId: Int,
        userLocalProperties: UserLocalProperties
    ): Either<Failure, SettingsUpdate> {
        return when (networkHandler.isConnected) {
            true -> request(
                service.updateUserProperties(userId, userLocalProperties),
                { settingsUpdateEntity -> settingsUpdateEntity.toSettingsUpdate() },
                SettingsUpdateEntity.empty()
            )
            false, null -> Either.Left(
                Failure.NetworkConnection
            )
        }
    }

}