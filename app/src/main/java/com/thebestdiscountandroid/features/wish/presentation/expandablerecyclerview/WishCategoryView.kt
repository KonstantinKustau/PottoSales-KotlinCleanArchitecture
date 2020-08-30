package com.thebestdiscountandroid.features.wish.presentation.expandablerecyclerview

data class WishCategoryView(
    val id: Int,
    val name: String,
    var categoryExpanded: Boolean,
    var categorySelected: Boolean,
    val products: List<WishProductView>
) : WishView()