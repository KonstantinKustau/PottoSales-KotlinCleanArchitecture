package com.thebestdiscountandroid.features.deals.domain

import com.thebestdiscountandroid.core.extension.empty

data class Deal(
    val name: String,
    val productPrice: Double,
    val productImage: Any,
    val shopName: String
) {
    companion object {
        fun empty() = Deal(String.empty(), 0.0, String.empty(), String.empty())
    }
}