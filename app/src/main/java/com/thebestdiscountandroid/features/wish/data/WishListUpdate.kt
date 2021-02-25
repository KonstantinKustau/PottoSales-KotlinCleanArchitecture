package com.thebestdiscountandroid.features.wish.data

import com.thebestdiscountandroid.features.wish.domain.WishListUpdateEntity

data class WishListUpdate(
    private val isUpdated: Boolean?
) {
    companion object {
        fun empty() =
            WishListUpdate(
                null
            )
    }

    fun toWishListUpdate() =
        WishListUpdateEntity(isUpdated)
}