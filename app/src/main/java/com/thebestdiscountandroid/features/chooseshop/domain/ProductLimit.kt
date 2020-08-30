package com.thebestdiscountandroid.features.chooseshop.domain

import com.thebestdiscountandroid.core.extension.empty

data class ProductLimit(
    val id: Int,
    val productName: String,
    val limit: Double
) {
    companion object {
        fun empty() = ProductLimit(-1, String.empty(), 0.0)
    }
}