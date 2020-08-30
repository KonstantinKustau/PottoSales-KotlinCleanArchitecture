package com.thebestdiscountandroid.features.chooseshop.domain

import com.thebestdiscountandroid.core.extension.empty

data class Shop(
    val name: String,
    val productPrice: Double,
    val discountPercentage: Int?,
    val numberOfStars: Float?
) {
    companion object {
        fun empty() = Shop(String.empty(), 0.0, 0, 0f)
    }
}