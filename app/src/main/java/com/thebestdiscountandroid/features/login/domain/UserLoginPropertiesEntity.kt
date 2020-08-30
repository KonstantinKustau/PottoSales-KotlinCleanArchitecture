package com.thebestdiscountandroid.features.login.domain

import com.thebestdiscountandroid.core.extension.empty

class UserLoginPropertiesEntity(
    private val userId: Int,
    private val email: String,
    private val password: String,
    private val mobilePhone: String,
    private val firstName: String,
    private val lastName: String
) {

    companion object {
        fun empty() = UserLoginPropertiesEntity(
            -1,
            String.empty(),
            String.empty(),
            String.empty(),
            String.empty(),
            String.empty()
        )
    }

    fun toUserLoginProperties() = UserLoginProperties(userId, email, password, mobilePhone, firstName, lastName)
}