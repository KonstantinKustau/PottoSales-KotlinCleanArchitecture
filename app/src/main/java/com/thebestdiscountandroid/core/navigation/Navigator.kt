package com.thebestdiscountandroid.core.navigation

import android.content.Context
import android.view.View
import com.thebestdiscountandroid.core.storage.PreferencesStorage
import com.thebestdiscountandroid.features.chooseshop.presentative.ChooseShopActivity
import com.thebestdiscountandroid.features.deals.presentative.DealsActivity
import com.thebestdiscountandroid.features.login.presentation.LoginActivity
import com.thebestdiscountandroid.features.settings.presentative.SettingsActivity
import com.thebestdiscountandroid.features.wish.presentation.WishListsActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator
@Inject constructor() {

    @Inject
    lateinit var preferencesStorage: PreferencesStorage

    fun showFirstScreen(context: Context) {
//        when (preferencesStorage.getUserId() == 0) {
//            true -> showLogin(context)
//            false -> showMain(context)
//        }
        showMain(context)
    }

    private fun showLogin(context: Context) =
        context.startActivity(LoginActivity.callingIntent(context))

    fun showMain(context: Context) =
        context.startActivity(WishListsActivity.callingIntent(context))

    fun showDeals(context: Context) =
        context.startActivity(DealsActivity.callingIntent(context))

    fun showChooseShop(context: Context, productId: Int, productName: String) =
        context.startActivity(ChooseShopActivity.callingIntent(context, productId, productName))

    fun showSettings(context: Context) =
        context.startActivity(SettingsActivity.callingIntent(context))

    //TODO create urls & intents to particular shop

    class Extras(val transitionSharedElement: View)
}


