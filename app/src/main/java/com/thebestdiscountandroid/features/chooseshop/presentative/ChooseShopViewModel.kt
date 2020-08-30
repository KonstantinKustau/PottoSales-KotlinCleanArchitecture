package com.thebestdiscountandroid.features.chooseshop.presentative

import androidx.lifecycle.MutableLiveData
import com.thebestdiscountandroid.core.platform.BaseViewModel
import com.thebestdiscountandroid.features.chooseshop.domain.GetLimitsOnUser
import com.thebestdiscountandroid.features.chooseshop.domain.GetShopsByProduct
import com.thebestdiscountandroid.features.chooseshop.domain.ProductLimit
import com.thebestdiscountandroid.features.chooseshop.domain.Shop
import com.thebestdiscountandroid.features.chooseshop.presentative.recyclerview.ShopHeaderView
import com.thebestdiscountandroid.features.chooseshop.presentative.recyclerview.ShopItemView
import com.thebestdiscountandroid.features.chooseshop.presentative.recyclerview.ShopView
import javax.inject.Inject

class ChooseShopViewModel
@Inject constructor(
    private val getLimitsOnUser: GetLimitsOnUser,
    private val getShopsByProduct: GetShopsByProduct
) : BaseViewModel() {

    var shops: MutableLiveData<List<ShopView>> = MutableLiveData()

    var limit: MutableLiveData<ProductLimit> = MutableLiveData()

    fun loadShops(productId: Int) {
        getShopsByProduct(GetShopsByProduct.Params(productId)) { it ->
            it.fold(
                { handleFailure(it) },
                { handleShops(it) })
        }
    }

    fun loadLimits(userId: Int, productId: Int) {
        getLimitsOnUser(GetLimitsOnUser.Params(userId)) { it ->
            it.fold(
                { handleFailure(it) },
                { handleLimits(it, productId) })
        }
    }

    private fun handleShops(shops: List<Shop>) {
        val shopsAdapterList: ArrayList<ShopView> = arrayListOf()
        shopsAdapterList.add(ShopHeaderView(null))
        shops.map {
            shopsAdapterList.add(
                ShopItemView(
                    it.name,
                    it.productPrice,
                    it.discountPercentage,
                    it.numberOfStars,
                    false
                )
            )
        }
        this.shops.value = shopsAdapterList
    }

    private fun handleLimits(limits: List<ProductLimit>, productId: Int) {
        limits.map {
            if (it.id == productId) {
                this.limit.value = it
            }
        }
    }
}