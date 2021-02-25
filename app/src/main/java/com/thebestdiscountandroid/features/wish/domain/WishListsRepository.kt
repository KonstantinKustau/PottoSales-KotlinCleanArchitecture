package com.thebestdiscountandroid.features.wish.domain

import com.thebestdiscountandroid.core.exception.Failure
import com.thebestdiscountandroid.core.functional.Either

interface WishListsRepository {

    fun getWishLists(userId: Int): Either<Failure, List<WishListCategory>>

    fun updateWishLists(
        userId: Int,
        updateType: String,
        updateData: List<WishListCategory>
    ): Either<Failure, WishListUpdate>

}