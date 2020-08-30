package com.thebestdiscountandroid.features.wish.presentation.expandablerecyclerview

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thebestdiscountandroid.R
import kotlinx.android.synthetic.main.row_category_wish_list.view.categoryName
import kotlinx.android.synthetic.main.row_category_wish_list.view.categoryChoiceCheckBox
import kotlinx.android.synthetic.main.row_category_wish_list.view.categoryArrow

class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val name: TextView = itemView.categoryName
    private val checkBox: CheckBox = itemView.categoryChoiceCheckBox
    private val arrow: ImageView = itemView.categoryArrow

    fun bind(
        wishCategoryView: WishCategoryView,
        shortClickListener: (WishCategoryView) -> Unit,
        longClickListener: (WishCategoryView) -> Unit
    ) {
        this.name.text = wishCategoryView.name
        if (wishCategoryView.categoryExpanded) {
            this.arrow.setImageResource(R.drawable.ic_arrow_down_24)
        } else {
            this.arrow.setImageResource(R.drawable.ic_arrow_up_24)
        }
        if (wishCategoryView.categorySelected) {
            this.checkBox.visibility = View.VISIBLE
        } else {
            this.checkBox.visibility = View.GONE
        }

        itemView.setOnClickListener {
            shortClickListener(wishCategoryView)
        }

        itemView.setOnLongClickListener {
            longClickListener(wishCategoryView)
            true
        }
    }
}