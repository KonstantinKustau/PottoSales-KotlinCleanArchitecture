package com.thebestdiscountandroid.features.deals.data

import com.thebestdiscountandroid.features.deals.domain.DealEntity
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DealsService
@Inject constructor(retrofit: Retrofit) : DealsApi {
    private val dealsApi by lazy { retrofit.create(DealsApi::class.java) }

    override fun getTopDeals(userId: Int): Call<List<DealEntity>> = dealsApi.getTopDeals(userId)

    override fun filterProductByName(userId: Int, productName: String): Call<List<DealEntity>> =
        dealsApi.filterProductByName(userId, productName)
}
