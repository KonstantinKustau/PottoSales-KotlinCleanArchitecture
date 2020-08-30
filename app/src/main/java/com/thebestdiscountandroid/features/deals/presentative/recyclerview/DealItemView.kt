package com.thebestdiscountandroid.features.deals.presentative.recyclerview

data class DealItemView(
    val name: String,
    val productPrice: Double,
    val productImage: Any,
    val shopName: String,
    var dealSelected: Boolean
) : DealView()