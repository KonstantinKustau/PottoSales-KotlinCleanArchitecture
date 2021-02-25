package com.thebestdiscountandroid.features.login.presentation

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.annotation.StringRes
import com.thebestdiscountandroid.R
import com.thebestdiscountandroid.core.exception.Failure
import com.thebestdiscountandroid.core.extension.*
import com.thebestdiscountandroid.core.navigation.Navigator
import com.thebestdiscountandroid.core.platform.BaseFragment
import com.thebestdiscountandroid.core.storage.PreferencesStorage
import com.thebestdiscountandroid.features.login.domain.UserLoginPropertiesEntity
import kotlinx.android.synthetic.main.login_fragment.email
import kotlinx.android.synthetic.main.login_fragment.password
import kotlinx.android.synthetic.main.login_fragment.signIn
import kotlinx.android.synthetic.main.login_fragment.signUp
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var preferencesStorage: PreferencesStorage

    private lateinit var loginViewModel: LoginViewModel

    override fun layoutId() = R.layout.login_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        loginViewModel = viewModel(viewModelFactory) {
            observe(userLoginPropertiesResult, ::loginSuccess)
            observe(loginForm, ::loginFormState)
            failure(failure, ::handleFailure)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val email = view.findViewById<EditText>(R.id.email)
        val password = view.findViewById<EditText>(R.id.password)

        email.afterTextChanged {
            loginViewModel.loginDataChanged(
                email.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    email.text.toString(),
                    password.text.toString()
                )
            }
            signIn.setOnClickListener {
                showProgress()
                loginViewModel.signIn(email.text.toString(), password.text.toString())
            }
            signUp.setOnClickListener {
                showProgress()
                loginViewModel.signUp(email.text.toString(), password.text.toString())
            }
        }
    }

    private fun loginSuccess(userLoginProperties: UserLoginPropertiesEntity?) {
        //TODO preference setUserId
        //TODO cache userProperties
//        if (loginData != null) {
//            preferencesStorage.setUserId(userProperties.userId)
        hideProgress()
        navigator.showMain(appContext)
//        }
    }

    private fun loginFormState(loginState: LoginFormState?) {
        if (loginState != null) {
            signIn.isEnabled = loginState.isDataValid
            signUp.isEnabled = loginState.isDataValid

            if (loginState.emailError != null) {
                email.error = getString(loginState.emailError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        }
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is Failure.ServerError -> renderFailure(R.string.failure_server_error)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        hideProgress()
        notify(message)
    }
}