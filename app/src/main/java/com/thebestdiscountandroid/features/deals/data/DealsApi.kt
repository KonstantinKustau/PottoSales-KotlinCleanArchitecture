package com.thebestdiscountandroid.features.deals.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

internal interface DealsApi {
    companion object {
        private const val PARAM_USER_ID = "userId"
        private const val PARAM_PRODUCT_NAME = "productName"
        private const val GET_TOP_DEALS = "getTopDeals/"
        private const val FILTER_PRODUCTS_BY_NAME = "filterProductsByName/"
    }

    @GET(GET_TOP_DEALS)
    fun getTopDeals(
        @Query(PARAM_USER_ID) userId: Int
    ): Call<List<Deal>>

    @GET(FILTER_PRODUCTS_BY_NAME)
    fun filterProductByName(
        @Query(PARAM_USER_ID) userId: Int,
        @Query(PARAM_PRODUCT_NAME) productName: String
    ): Call<List<Deal>>
}
