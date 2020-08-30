package com.thebestdiscountandroid.features.settings.domain

data class SettingsUpdate(
    val isUpdated: Boolean?
) {
    companion object {
        fun empty() = SettingsUpdate(null)
    }
}