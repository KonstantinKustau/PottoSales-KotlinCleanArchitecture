package com.thebestdiscountandroid.features.chooseshop.presentative.recyclerview

import android.view.View
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import com.thebestdiscountandroid.R
import kotlinx.android.synthetic.main.row_choose_shop.view.*

class ShopItemViewHolder(itemView: View, @ColorInt val selectedColor: Int) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(
        shopItemView: ShopItemView,
        shortClickListener: (ShopItemView) -> Unit,
        longClickListener: (ShopItemView) -> Unit
    ) {
        val currentPriceInUSD = "${shopItemView.productPrice}$"
        val discountPercentage = "${shopItemView.discountPercentage}%"
        itemView.title.text = shopItemView.name
        itemView.currentPrice.text = currentPriceInUSD
        itemView.discountPercentage.text = discountPercentage
        if (shopItemView.shopSelected) {
            itemView.dealCard.setBackgroundColor(selectedColor)
        }
        bindStars(shopItemView.numberOfStars)

        //TODO shopSelected
//        if (shopView.shopSelected) {
//            itemView.choiceCheckBox.visibility = View.VISIBLE
//        } else {
//            itemView.choiceCheckBox.visibility = View.GONE
//        }

        itemView.setOnClickListener {
            shortClickListener(shopItemView)
        }

        itemView.setOnLongClickListener {
            longClickListener(shopItemView)
            true
        }
    }

    private fun bindStars(numberOfStars: Float?) {
        when (numberOfStars) {
            0f -> {
            }
            0.5f -> {
                itemView.ratingImage1.setImageResource(R.drawable.ic_star_half_24)
            }
            1f -> {
                itemView.ratingImage1.setImageResource(R.drawable.ic_star_24)
            }
            1.5f -> {
                itemView.ratingImage1.setImageResource(R.drawable.ic_star_24)
                itemView.ratingImage2.setImageResource(R.drawable.ic_star_half_24)
            }
            2f -> {
                itemView.ratingImage1.setImageResource(R.drawable.ic_star_24)
                itemView.ratingImage2.setImageResource(R.drawable.ic_star_24)
            }
            2.5f -> {
                itemView.ratingImage1.setImageResource(R.drawable.ic_star_24)
                itemView.ratingImage2.setImageResource(R.drawable.ic_star_24)
                itemView.ratingImage3.setImageResource(R.drawable.ic_star_half_24)
            }
            3f -> {
                itemView.ratingImage1.setImageResource(R.drawable.ic_star_24)
                itemView.ratingImage2.setImageResource(R.drawable.ic_star_24)
                itemView.ratingImage3.setImageResource(R.drawable.ic_star_24)

            }
            3.5f -> {
                itemView.ratingImage1.setImageResource(R.drawable.ic_star_24)
                itemView.ratingImage2.setImageResource(R.drawable.ic_star_24)
                itemView.ratingImage3.setImageResource(R.drawable.ic_star_24)
                itemView.ratingImage4.setImageResource(R.drawable.ic_star_half_24)
                itemView.ratingImage5.setImageResource(R.drawable.ic_star_border_24)
            }
            4f -> {
                itemView.ratingImage1.setImageResource(R.drawable.ic_star_24)
                itemView.ratingImage2.setImageResource(R.drawable.ic_star_24)
                itemView.ratingImage3.setImageResource(R.drawable.ic_star_24)
                itemView.ratingImage4.setImageResource(R.drawable.ic_star_24)
                itemView.ratingImage5.setImageResource(R.drawable.ic_star_border_24)
            }
            4.5f -> {
                itemView.ratingImage1.setImageResource(R.drawable.ic_star_24)
                itemView.ratingImage2.setImageResource(R.drawable.ic_star_24)
                itemView.ratingImage3.setImageResource(R.drawable.ic_star_24)
                itemView.ratingImage4.setImageResource(R.drawable.ic_star_24)
                itemView.ratingImage5.setImageResource(R.drawable.ic_star_half_24)
            }
            5f -> {
                itemView.ratingImage1.setImageResource(R.drawable.ic_star_24)
                itemView.ratingImage2.setImageResource(R.drawable.ic_star_24)
                itemView.ratingImage3.setImageResource(R.drawable.ic_star_24)
                itemView.ratingImage4.setImageResource(R.drawable.ic_star_24)
                itemView.ratingImage5.setImageResource(R.drawable.ic_star_24)
            }
        }
    }
}