package com.thebestdiscountandroid.features.wish.domain

data class WishListUpdateEntity(
    private val isUpdated: Boolean?
) {
    companion object {
        fun empty() = WishListUpdateEntity(null)
    }

    fun toWishListUpdate() = WishListUpdate(isUpdated)
}