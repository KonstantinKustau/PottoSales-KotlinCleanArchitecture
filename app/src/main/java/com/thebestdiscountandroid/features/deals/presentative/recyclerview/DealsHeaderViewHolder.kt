package com.thebestdiscountandroid.features.deals.presentative.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_deals_header.view.*

class DealsHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(topDealsHeader: String) {
        itemView.topDealsHeader.text = topDealsHeader
    }
}