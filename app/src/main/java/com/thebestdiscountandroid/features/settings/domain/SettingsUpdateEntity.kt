package com.thebestdiscountandroid.features.settings.domain

data class SettingsUpdateEntity(
    private val isUpdated: Boolean?
) {
    companion object {
        fun empty() = SettingsUpdateEntity(null)
    }

    fun toSettingsUpdate() = SettingsUpdate(isUpdated)
}