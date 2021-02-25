package com.thebestdiscountandroid.features.login.data

import com.thebestdiscountandroid.core.extension.empty
import com.thebestdiscountandroid.features.login.domain.UserLoginPropertiesEntity

data class UserLoginProperties(
    private val userId: Int,
    private val email: String,
    private val password: String,
    private val mobilePhone: String,
    private val firstName: String,
    private val lastName: String
) {

    companion object {
        fun empty() =
            UserLoginProperties(
                -1,
                String.empty(),
                String.empty(),
                String.empty(),
                String.empty(),
                String.empty()
            )
    }

    fun toUserLoginProperties() =
        UserLoginPropertiesEntity(
            userId,
            email,
            password,
            mobilePhone,
            firstName,
            lastName
        )
}