package com.thebestdiscountandroid.features.deals.presentative

import android.content.Context
import android.content.Intent
import com.thebestdiscountandroid.core.platform.BaseActivity
import com.thebestdiscountandroid.features.chooseshop.presentative.ChooseShopActivity

class DealsActivity : BaseActivity() {

    companion object {

        fun callingIntent(context: Context): Intent {
            val intent = Intent(context, DealsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
            return intent
        }
    }

    override fun fragment() = DealsFragment()
}