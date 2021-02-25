package com.thebestdiscountandroid.features.settings.data

import com.thebestdiscountandroid.features.settings.domain.UserLocalProperties
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsService
@Inject constructor(retrofit: Retrofit) : SettingsApi {
    private val settingsApi by lazy { retrofit.create(SettingsApi::class.java) }

    override fun logout(userId: Int): Call<Any> = settingsApi.logout(userId)

    override fun updateUserProperties(
        userId: Int,
        userLocalProperties: UserLocalProperties
    ): Call<SettingsUpdate> = settingsApi.updateUserProperties(userId, userLocalProperties)
}
