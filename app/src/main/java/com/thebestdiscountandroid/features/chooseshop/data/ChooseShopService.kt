package com.thebestdiscountandroid.features.chooseshop.data

import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChooseShopService
@Inject constructor(retrofit: Retrofit) : ChooseShopApi {
    private val chooseShopApi by lazy { retrofit.create(ChooseShopApi::class.java) }

    override fun getShopsByProduct(productId: Int): Call<List<Shop>> =
        chooseShopApi.getShopsByProduct(productId)

    override fun setLimitOnUserProduct(userId: Int, productName: String, limit: Double): Call<Any> =
        chooseShopApi.setLimitOnUserProduct(userId, productName, limit)

    override fun getLimitsOnUser(userId: Int): Call<List<ProductLimit>> =
        chooseShopApi.getLimitsOnUser(userId)
}
