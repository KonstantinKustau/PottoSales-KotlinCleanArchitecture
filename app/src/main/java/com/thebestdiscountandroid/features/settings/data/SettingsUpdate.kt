package com.thebestdiscountandroid.features.settings.data

import com.thebestdiscountandroid.features.settings.domain.SettingsUpdateEntity

data class SettingsUpdate(
    private val isUpdated: Boolean?
) {
    companion object {
        fun empty() =
            SettingsUpdate(
                null
            )
    }

    fun toSettingsUpdate() =
        SettingsUpdateEntity(
            isUpdated
        )
}