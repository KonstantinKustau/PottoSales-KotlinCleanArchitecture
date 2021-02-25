package com.thebestdiscountandroid.features.chooseshop.domain

data class ShopEntity(
    val name: String,
    val productPrice: Double,
    val discountPercentage: Int?,
    val numberOfStars: Float?
)