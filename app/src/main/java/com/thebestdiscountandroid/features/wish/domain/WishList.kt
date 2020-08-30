package com.thebestdiscountandroid.features.wish.domain

import com.thebestdiscountandroid.core.extension.empty

data class WishListCategory(
    val id: Int,
    val name: String,
    val items: List<WishListItem>
) {
    companion object {
        fun empty() = WishListCategory(-1, String.empty(), emptyList())
    }
}

data class WishListItem(
    val id: Int,
    val name: String,
    val image: Int,
    val currentPriceInUSD: Double,
    val maxPurchasePriceInUSD: Double
)