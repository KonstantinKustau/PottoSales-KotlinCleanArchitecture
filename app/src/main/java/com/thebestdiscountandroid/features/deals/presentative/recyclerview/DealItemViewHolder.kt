package com.thebestdiscountandroid.features.deals.presentative.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_deals.view.*

class DealItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(
        dealItemView: DealItemView,
        shortClickListener: (DealItemView) -> Unit,
        longClickListener: (DealItemView) -> Unit
    ) {
        val currentPriceInUSD = "${dealItemView.productPrice}$"
//        itemView.dealImage.setImageLevel(dealView.productImage as Int)
        itemView.title.text = dealItemView.name
        itemView.currentPrice.text = currentPriceInUSD
        itemView.shopName.text = dealItemView.shopName
        if (dealItemView.dealSelected) {
            itemView.choiceCheckBox.visibility = View.VISIBLE
        } else {
            itemView.choiceCheckBox.visibility = View.GONE
        }

        itemView.setOnClickListener {
            shortClickListener(dealItemView)
        }

        itemView.setOnLongClickListener {
            longClickListener(dealItemView)
            true
        }
    }
}