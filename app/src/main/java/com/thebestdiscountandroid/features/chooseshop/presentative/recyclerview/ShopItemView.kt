package com.thebestdiscountandroid.features.chooseshop.presentative.recyclerview

data class ShopItemView(
    val name: String,
    val productPrice: Double,
    val discountPercentage: Int?,
    val numberOfStars: Float?,
    var shopSelected: Boolean
) : ShopView()