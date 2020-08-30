package com.thebestdiscountandroid.features.chooseshop.data

import com.thebestdiscountandroid.features.chooseshop.domain.ProductLimitEntity
import com.thebestdiscountandroid.features.chooseshop.domain.ShopEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

internal interface ChooseShopApi {
    companion object {
        private const val PARAM_USER_ID = "userId"
        private const val PARAM_PRODUCT_NAME = "productName"
        private const val PARAM_LIMIT = "limit"
        private const val GET_SHOPS_BY_PRODUCT = "getShopsByProduct/"
        private const val SET_LIMIT_ON_USER_PRODUCT = "setLimitOnUserProduct/"
        private const val GET_LIMITS_ON_USER = "getLimitsOnUser/"
    }

    @GET(GET_SHOPS_BY_PRODUCT)
    fun getShopsByProduct(
        @Query(PARAM_PRODUCT_NAME) productId: Int
    ): Call<List<ShopEntity>>

    @POST(SET_LIMIT_ON_USER_PRODUCT)
    fun setLimitOnUserProduct(
        @Query(PARAM_USER_ID) userId: Int,
        @Query(PARAM_PRODUCT_NAME) productName: String,
        @Query(PARAM_LIMIT) limit: Double
    ): Call<Any>

    @GET(GET_LIMITS_ON_USER)
    fun getLimitsOnUser(
        @Query(PARAM_USER_ID) userId: Int
    ): Call<List<ProductLimitEntity>>
}