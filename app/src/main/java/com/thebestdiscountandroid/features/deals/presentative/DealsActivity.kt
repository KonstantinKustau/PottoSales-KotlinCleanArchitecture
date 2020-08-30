package com.thebestdiscountandroid.features.deals.presentative

import android.content.Context
import android.content.Intent
import com.thebestdiscountandroid.core.platform.BaseActivity

class DealsActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, DealsActivity::class.java)
    }

    override fun fragment() = DealsFragment()
}