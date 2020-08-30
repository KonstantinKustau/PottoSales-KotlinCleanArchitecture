package com.thebestdiscountandroid.features.login.presentation

import android.content.Context
import android.content.Intent
import com.thebestdiscountandroid.core.platform.BaseActivity

class LoginActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }

    override fun fragment() = LoginFragment()
}