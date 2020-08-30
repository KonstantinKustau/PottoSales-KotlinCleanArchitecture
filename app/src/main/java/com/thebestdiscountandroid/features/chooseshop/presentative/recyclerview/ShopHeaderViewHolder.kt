package com.thebestdiscountandroid.features.chooseshop.presentative.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.thebestdiscountandroid.features.chooseshop.presentative.ChooseShopFragment
import kotlinx.android.synthetic.main.row_choose_shop_header.view.*

class ShopHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(
        limit: Double?,
        shortClickListener: (ShopHeaderView) -> Unit
    ) {
        if (limit == null) {
            itemView.limitHeader.text = ChooseShopFragment.NO_LIMIT_HEADER
        } else {
            val header = "${ChooseShopFragment.LIMIT_HEADER} $limit $"
            itemView.limitHeader.text = header
        }

        itemView.setOnClickListener {
            shortClickListener(ShopHeaderView(limit))
        }
    }
}