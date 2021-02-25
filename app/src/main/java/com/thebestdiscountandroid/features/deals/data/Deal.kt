package com.thebestdiscountandroid.features.deals.data

import com.thebestdiscountandroid.features.deals.domain.DealEntity

data class Deal(
    private val name: String,
    private val productPrice: Double,
    private val productImage: Any,
    private val shopName: String
) {
    fun toDeal() = DealEntity(
        name,
        productPrice,
        productImage,
        shopName
    )
}