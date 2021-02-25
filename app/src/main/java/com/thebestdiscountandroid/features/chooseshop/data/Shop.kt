package com.thebestdiscountandroid.features.chooseshop.data

import com.thebestdiscountandroid.features.chooseshop.domain.ShopEntity

data class Shop(
    private val name: String,
    private val productPrice: Double,
    private val discountPercentage: Int?,
    private val numberOfStars: Float?
) {
    fun toShop() = ShopEntity(
        name,
        productPrice,
        discountPercentage,
        numberOfStars
    )
}