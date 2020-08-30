package com.thebestdiscountandroid.features.settings.domain

import com.thebestdiscountandroid.core.extension.empty

data class UserLocalProperties(
    val userId: Int,
    val email: String,
    val password: String,
    val mobilePhone: String,
    val firstName: String,
    val lastName: String
) {
    companion object {
        fun empty() = UserLocalProperties(
            0,
            String.empty(),
            String.empty(),
            String.empty(),
            String.empty(),
            String.empty()
        )
    }
}