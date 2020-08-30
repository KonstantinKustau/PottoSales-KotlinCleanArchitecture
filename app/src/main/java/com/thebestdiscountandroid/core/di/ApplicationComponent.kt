package com.thebestdiscountandroid.core.di

import com.thebestdiscountandroid.AndroidApplication
import com.thebestdiscountandroid.core.di.viewmodel.ViewModelModule
import com.thebestdiscountandroid.core.navigation.RouteActivity
import com.thebestdiscountandroid.features.chooseshop.presentative.ChooseShopFragment
import com.thebestdiscountandroid.features.deals.presentative.DealsFragment
import com.thebestdiscountandroid.features.login.presentation.LoginFragment
import com.thebestdiscountandroid.features.wish.presentation.WishListsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(routeActivity: RouteActivity)

    fun inject(loginFragment: LoginFragment)
    fun inject(wishListsFragment: WishListsFragment)
    fun inject(dealsFragment: DealsFragment)
    fun inject(chooseShopFragment: ChooseShopFragment)
}
