package com.thebestdiscountandroid.features.chooseshop.data

import com.thebestdiscountandroid.features.chooseshop.domain.ProductLimitEntity

data class ProductLimit(
    private val id: Int,
    private val productName: String,
    private val limit: Double
) {
    fun toProductLimit() =
        ProductLimitEntity(
            id,
            productName,
            limit
        )
}