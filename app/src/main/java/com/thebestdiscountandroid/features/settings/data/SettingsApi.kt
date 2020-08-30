package com.thebestdiscountandroid.features.settings.data

import com.thebestdiscountandroid.features.settings.domain.SettingsUpdateEntity
import com.thebestdiscountandroid.features.settings.domain.UserLocalProperties
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

internal interface SettingsApi {
    companion object {
        private const val PARAM_USER_ID = "userId"
        private const val PARAM_USER_NEW_PROPERTIES = "newProperties"
        private const val LOGOUT = "logout/"
        private const val UPDATE_USER_PROPERTIES = "updateUserProperties/"
    }

    @POST(LOGOUT)
    fun logout(
        @Query(PARAM_USER_ID) userId: Int
    ): Call<Any>

    @PUT(UPDATE_USER_PROPERTIES)
    fun updateUserProperties(
        @Query(PARAM_USER_ID) userId: Int,
        @Query(PARAM_USER_NEW_PROPERTIES) userLocalProperties: UserLocalProperties
    ): Call<SettingsUpdateEntity>
}
