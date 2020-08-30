package com.thebestdiscountandroid.features.login.domain

import com.thebestdiscountandroid.core.extension.empty

data class UserLoginProperties(
    val userId: Int,
    val email: String,
    val password: String,
    val mobilePhone: String,
    val firstName: String,
    val lastName: String
) {
    companion object {
        fun empty() = UserLoginProperties(
            0,
            String.empty(),
            String.empty(),
            String.empty(),
            String.empty(),
            String.empty()
        )
    }
}