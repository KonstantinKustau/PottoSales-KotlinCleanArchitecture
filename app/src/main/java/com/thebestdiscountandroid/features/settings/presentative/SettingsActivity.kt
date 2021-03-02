package com.thebestdiscountandroid.features.settings.presentative

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.thebestdiscountandroid.R
import com.thebestdiscountandroid.core.platform.BaseFragment
import com.thebestdiscountandroid.features.chooseshop.presentative.ChooseShopActivity
import kotlinx.android.synthetic.main.settings_activity.*
import kotlinx.android.synthetic.main.settings_content.*

class SettingsActivity : AppCompatActivity() {

    companion object {

        fun callingIntent(context: Context): Intent {
            val intent = Intent(context, SettingsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        initializeView()
    }

    private fun initializeView() {
        toolbar_layout.title = "Konstantin Kustov"
        phoneNumber.text = "+375333584478"
        email.text = "test@mail.com"
        pottoSalesVersion.text = "PottoSales for Android v.1.0.0"

        fab.setOnClickListener {}
        mobileCard.setOnClickListener {}
        mailCard.setOnClickListener {}
        notificationPeriodCard.setOnClickListener {}
        favoriteShopsCard.setOnClickListener {}
        privacyPolicyCard.setOnClickListener {}
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.actionEditName -> true
            R.id.actionLogout -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}