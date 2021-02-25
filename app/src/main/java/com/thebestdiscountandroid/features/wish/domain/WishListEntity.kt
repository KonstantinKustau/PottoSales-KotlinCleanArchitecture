package com.thebestdiscountandroid.features.wish.domain

data class WishListCategory(
    val id: Int,
    val name: String,
    val items: List<WishListItem>
)

data class WishListItem(
    val id: Int,
    val name: String,
    val image: Int,
    val currentPriceInUSD: Double,
    val maxPurchasePriceInUSD: Double
)