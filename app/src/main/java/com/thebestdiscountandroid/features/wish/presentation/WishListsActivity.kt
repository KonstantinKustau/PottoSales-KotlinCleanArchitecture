package com.thebestdiscountandroid.features.wish.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.thebestdiscountandroid.R
import com.thebestdiscountandroid.core.extension.inTransaction
import com.thebestdiscountandroid.core.platform.BaseFragment
import kotlinx.android.synthetic.main.activity_layout.*
import kotlinx.android.synthetic.main.toolbar.*


class WishListsActivity : AppCompatActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, WishListsActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_activity)
        setSupportActionBar(toolbar as Toolbar?)
        addFragment(savedInstanceState)
    }

    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(
            R.id.fragmentContainer
        ) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }

    internal fun getFabAdd(): FloatingActionButton = fab_add

    private fun addFragment(savedInstanceState: Bundle?) =
        savedInstanceState ?: supportFragmentManager.inTransaction {
            add(
                R.id.fragmentContainer, WishListsFragment()
            )
        }
}