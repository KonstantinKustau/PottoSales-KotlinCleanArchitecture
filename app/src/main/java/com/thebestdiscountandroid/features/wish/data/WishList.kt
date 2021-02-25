package com.thebestdiscountandroid.features.wish.data

import com.thebestdiscountandroid.features.wish.domain.WishListCategory
import com.thebestdiscountandroid.features.wish.domain.WishListItem

data class WishListCategoryEntity(
    private val id: Int,
    private val name: String,
    private val items: List<WishListItem>
) {
    fun toWishListCategory() =
        WishListCategory(
            id,
            name,
            items
        )
}