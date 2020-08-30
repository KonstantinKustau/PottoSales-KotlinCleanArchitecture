package com.thebestdiscountandroid.features.wish.domain

import com.thebestdiscountandroid.core.extension.empty

data class WishListCategoryEntity(
    private val id: Int,
    private val name: String,
    private val items: List<WishListItem>
) {
    companion object {
        fun empty() = WishListCategoryEntity(-1, String.empty(), emptyList())
    }

    fun toWishListCategory() = WishListCategory(id, name, items)
}