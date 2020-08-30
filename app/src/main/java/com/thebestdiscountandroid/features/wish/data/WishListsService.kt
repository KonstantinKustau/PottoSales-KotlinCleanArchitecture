package com.thebestdiscountandroid.features.wish.data

import com.thebestdiscountandroid.features.wish.domain.WishListCategory
import com.thebestdiscountandroid.features.wish.domain.WishListCategoryEntity
import com.thebestdiscountandroid.features.wish.domain.WishListUpdateEntity
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WishListsService
@Inject constructor(retrofit: Retrofit) : WishListsApi {
    private val wishListsApi by lazy { retrofit.create(WishListsApi::class.java) }

    override fun getWishLists(userId: Int): Call<List<WishListCategoryEntity>> =
        wishListsApi.getWishLists(userId)

    override fun updateWishLists(
        userId: Int,
        updateType: String,
        updateData: List<WishListCategory>
    ): Call<WishListUpdateEntity> = wishListsApi.updateWishLists(userId, updateType, updateData)
}
