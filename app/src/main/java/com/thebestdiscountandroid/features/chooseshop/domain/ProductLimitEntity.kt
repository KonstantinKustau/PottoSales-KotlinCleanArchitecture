package com.thebestdiscountandroid.features.chooseshop.domain

import com.thebestdiscountandroid.core.extension.empty

data class ProductLimitEntity(
    private val id: Int,
    private val productName: String,
    private val limit: Double
) {
    companion object {
        fun empty() = ProductLimitEntity(-1, String.empty(), 0.0)
    }

    fun toProductLimit() = ProductLimit(id, productName, limit)
}