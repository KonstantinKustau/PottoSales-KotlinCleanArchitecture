package com.thebestdiscountandroid.features.wish.data

import com.thebestdiscountandroid.features.wish.domain.WishListCategory
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

internal interface WishListsApi {
    companion object {
        private const val PARAM_USER_ID = "userId"
        private const val PARAM_UPDATE_TYPE = "updateType"
        private const val PARAM_UPDATE_DATA = "updateData"
        private const val GET_WISH_LISTS = "getWishLists/"
        private const val UPDATE_WISH_LISTS = "updateWishLists/"
        private const val ADD = "add"
        private const val UPDATE = "update"
        private const val DELETE = "delete"
    }

    @GET(GET_WISH_LISTS)
    fun getWishLists(@Query(PARAM_USER_ID) userId: Int): Call<List<WishListCategoryEntity>>

    @PUT(UPDATE_WISH_LISTS)
    fun updateWishLists(
        @Query(PARAM_USER_ID) userId: Int,
        @Query(PARAM_UPDATE_TYPE) updateType: String,
        @Query(PARAM_UPDATE_DATA) updateData: List<WishListCategory>
    ): Call<WishListUpdate>
}