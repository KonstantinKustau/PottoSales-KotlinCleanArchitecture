package com.thebestdiscountandroid.features.login.domain

data class UserLoginPropertiesEntity(
    val userId: Int,
    val email: String,
    val password: String,
    val mobilePhone: String,
    val firstName: String,
    val lastName: String
)