package com.thebestdiscountandroid.features.wish.presentation.expandablerecyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_wish_list.view.*

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(
        wishProductView: WishProductView,
        shortClickListener: (WishProductView) -> Unit,
        longClickListener: (WishProductView) -> Unit
    ) {
        val maxPurchasePriceInUSD = "${wishProductView.maxPurchasePriceInUSD}$"
        val currentPriceInUSD = "${wishProductView.currentPriceInUSD}$"
        itemView.productImage.setImageLevel(wishProductView.image)
        itemView.title.text = wishProductView.name
        itemView.currentPrice.text = currentPriceInUSD
        itemView.maxPurchasePrice.text = maxPurchasePriceInUSD
        if (wishProductView.productSelected) {
            itemView.choiceCheckBox.visibility = View.VISIBLE
        } else {
            itemView.choiceCheckBox.visibility = View.GONE
        }

        itemView.setOnClickListener {
            shortClickListener(wishProductView)
        }

        itemView.setOnLongClickListener {
            longClickListener(wishProductView)
            true
        }
    }
}