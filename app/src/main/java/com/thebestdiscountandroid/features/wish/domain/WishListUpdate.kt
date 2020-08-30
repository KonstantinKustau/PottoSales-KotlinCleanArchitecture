package com.thebestdiscountandroid.features.wish.domain

data class WishListUpdate(
    val isUpdated: Boolean?
) {
    companion object {
        fun empty() = WishListUpdate(null)
    }
}