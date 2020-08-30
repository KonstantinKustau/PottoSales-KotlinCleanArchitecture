package com.thebestdiscountandroid.features.wish.presentation.expandablerecyclerview

data class WishProductView(
    val id: Int,
    val name: String,
    val image: Int,
    val currentPriceInUSD: Double,
    val maxPurchasePriceInUSD: Double,
    var productSelected: Boolean
) : WishView()