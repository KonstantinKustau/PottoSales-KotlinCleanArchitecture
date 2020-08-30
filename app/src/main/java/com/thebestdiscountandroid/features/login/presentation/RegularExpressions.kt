package com.thebestdiscountandroid.features.login.presentation

const val regex_password_characters = "(?=.{8,})"
const val regex_password_spaces = "(?=\\S+\$)"
const val regex_password_number = "(?=.*[0-9])"
const val regex_password_latin_letter = "(?=.*[a-zA-Z])"
const val regex_password =
    "^$regex_password_number$regex_password_latin_letter$regex_password_spaces$regex_password_characters.*\$"