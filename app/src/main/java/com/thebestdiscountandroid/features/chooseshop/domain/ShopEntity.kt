package com.thebestdiscountandroid.features.chooseshop.domain

import com.thebestdiscountandroid.core.extension.empty

data class ShopEntity(
    private val name: String,
    private val productPrice: Double,
    private val discountPercentage: Int?,
    private val numberOfStars: Float?
) {
    companion object {
        fun empty() = ShopEntity(String.empty(), 0.0, 0, 0f)
    }

    fun toShop() = Shop(name, productPrice, discountPercentage, numberOfStars)
}