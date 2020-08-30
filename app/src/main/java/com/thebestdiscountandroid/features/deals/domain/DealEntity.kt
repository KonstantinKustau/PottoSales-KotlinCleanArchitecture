package com.thebestdiscountandroid.features.deals.domain

import com.thebestdiscountandroid.core.extension.empty

data class DealEntity(
    private val name: String,
    private val productPrice: Double,
    private val productImage: Any,
    private val shopName: String
) {
    companion object {
        fun empty() = DealEntity(String.empty(), 0.0, String.empty(), String.empty())
    }

    fun toDeal() = Deal(name, productPrice, productImage, shopName)
}