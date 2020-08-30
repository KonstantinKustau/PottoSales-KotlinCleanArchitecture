package com.thebestdiscountandroid.features.chooseshop.data

import com.thebestdiscountandroid.features.chooseshop.domain.ProductLimitEntity
import com.thebestdiscountandroid.features.chooseshop.domain.ShopEntity
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChooseShopService
@Inject constructor(retrofit: Retrofit) : ChooseShopApi {
    private val chooseShopApi by lazy { retrofit.create(ChooseShopApi::class.java) }

    override fun getShopsByProduct(productId: Int): Call<List<ShopEntity>> =
        chooseShopApi.getShopsByProduct(productId)

    override fun setLimitOnUserProduct(userId: Int, productName: String, limit: Double): Call<Any> =
        chooseShopApi.setLimitOnUserProduct(userId, productName, limit)

    override fun getLimitsOnUser(userId: Int): Call<List<ProductLimitEntity>> =
        chooseShopApi.getLimitsOnUser(userId)
}
