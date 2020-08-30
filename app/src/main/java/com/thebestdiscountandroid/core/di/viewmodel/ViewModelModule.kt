package com.thebestdiscountandroid.core.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thebestdiscountandroid.features.chooseshop.presentative.ChooseShopViewModel
import com.thebestdiscountandroid.features.deals.presentative.DealsViewModel
import com.thebestdiscountandroid.features.login.presentation.LoginViewModel
import com.thebestdiscountandroid.features.wish.presentation.WishListsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindsLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WishListsViewModel::class)
    abstract fun bindsWishListsViewModel(wishListsViewModel: WishListsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DealsViewModel::class)
    abstract fun bindsDealsViewModel(dealsViewModel: DealsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChooseShopViewModel::class)
    abstract fun bindsChooseShopViewModel(chooseShopViewModel: ChooseShopViewModel): ViewModel
}