package com.thebestdiscountandroid.features.login.presentation

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import com.thebestdiscountandroid.R
import com.thebestdiscountandroid.core.platform.BaseViewModel
import com.thebestdiscountandroid.features.login.domain.GetSingInResult
import com.thebestdiscountandroid.features.login.domain.GetSignUpResult
import com.thebestdiscountandroid.features.login.domain.UserLoginProperties
import java.util.regex.Pattern
import javax.inject.Inject

class LoginViewModel
@Inject constructor(
    private val getSignInResult: GetSingInResult,
    private val getSignUpResult: GetSignUpResult
) : BaseViewModel() {

    val loginForm: MutableLiveData<LoginFormState> = MutableLiveData()
    var userLoginPropertiesResult: MutableLiveData<UserLoginProperties> = MutableLiveData()

    fun signIn(email: String, password: String) {
        //TODO mock data
        handleUserProperties(
            UserLoginProperties(1, email, password, "", "", "")
        )

//        getSignInResult(UseCase.None()) { it.fold(::handleFailure, ::handleLogin) }
    }

    fun signUp(email: String, password: String) {
        //TODO mock data
        handleUserProperties(
            UserLoginProperties(1, email, password, "", "", "")
        )

//        getSignUpResult(UseCase.None()) { it.fold(::handleFailure, ::handleLogin) }
    }

    private fun handleUserProperties(userLoginPropertiesData: UserLoginProperties) {
        this.userLoginPropertiesResult.value = userLoginPropertiesData
    }

    fun loginDataChanged(username: String, password: String) {
        if (username.isNotEmpty() && !isUserEmailValid(username)) {
            loginForm.value = LoginFormState(emailError = R.string.invalid_email)
        }
        if (password.isNotEmpty() && !isPasswordValid(password)) {
            loginForm.value = LoginFormState(passwordError = getPasswordErrorMessage(password))
        }
        if (isUserEmailValid(username) && isPasswordValid(password)) {
            loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isUserEmailValid(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isPasswordValid(password: String): Boolean =
        Pattern.compile(regex_password).matcher(password).matches()

    private fun getPasswordErrorMessage(password: String): Int =
        if (!Pattern.compile("^$regex_password_characters.*\$").matcher(password).matches()) {
            R.string.invalid_password_characters
        } else if (!Pattern.compile("^$regex_password_spaces.*\$").matcher(password).matches()) {
            R.string.invalid_password_spaces
        } else if (!Pattern.compile("^$regex_password_number.*\$").matcher(password).matches()) {
            R.string.invalid_password_number
        } else if (!Pattern.compile("^$regex_password_latin_letter.*\$").matcher(password)
                .matches()
        ) {
            R.string.invalid_password_latin_letter
        } else {
            R.string.invalid_password
        }
}