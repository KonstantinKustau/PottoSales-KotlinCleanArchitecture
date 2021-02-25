package com.thebestdiscountandroid.features.wish.presentation

import androidx.lifecycle.MutableLiveData
import com.thebestdiscountandroid.core.platform.BaseViewModel
import com.thebestdiscountandroid.features.wish.domain.*
import com.thebestdiscountandroid.features.wish.presentation.expandablerecyclerview.WishCategoryView
import com.thebestdiscountandroid.features.wish.presentation.expandablerecyclerview.WishProductView
import com.thebestdiscountandroid.features.wish.presentation.expandablerecyclerview.WishView
import javax.inject.Inject

class WishListsViewModel
@Inject constructor(
    private val getWishLists: GetWishLists,
    private val updateWishLists: UpdateWishLists
) : BaseViewModel() {

    var wishLists: MutableLiveData<List<WishView>> = MutableLiveData()

    var wishListsUpdate: MutableLiveData<Boolean> = MutableLiveData()

    fun updateWishLists(
        userId: Int,
        updateType: String,
        wishListsCategory: List<WishListCategory>
    ) {
//        updateWishLists(UpdateWishLists.Params(userId, updateType, wishListsCategory)) {
//            it.fold(::handleFailure, ::wishListUpdated)
//        }
    }

    fun loadWishLists(userId: Int) {
        getWishLists(GetWishLists.Params(userId)) { it ->
            it.fold(
                { handleFailure(it) },
                { handleWishList(it) })
        }
    }

    private fun handleWishList(wishListsCategory: List<WishListCategory>) {
        val wishViewAdapterList: ArrayList<WishView> = arrayListOf()

        wishListsCategory.map { it ->
            wishViewAdapterList.add(
                WishCategoryView(
                    it.id,
                    it.name,
                    categoryExpanded = false,
                    categorySelected = false,
                    products = it.items.map {
                        val wishProductView = WishProductView(
                            it.id,
                            it.name,
                            it.image,
                            it.currentPriceInUSD,
                            it.maxPurchasePriceInUSD,
                            productSelected = false
                        )
                        wishProductView
                    }
                )
            )
            WishCategoryView(
                it.id,
                it.name,
                categoryExpanded = false,
                categorySelected = false,
                products = it.items.map {
                    val wishProductView = WishProductView(
                        it.id,
                        it.name,
                        it.image,
                        it.currentPriceInUSD,
                        it.maxPurchasePriceInUSD,
                        productSelected = false
                    )
                    wishViewAdapterList.add(wishProductView)
                    wishProductView
                }
            )
        }
        this.wishLists.value = wishViewAdapterList
    }

    private fun wishListUpdated(wishListUpdate: WishListUpdateEntity) {
        this.wishListsUpdate.value = wishListUpdate.isUpdated
    }
}