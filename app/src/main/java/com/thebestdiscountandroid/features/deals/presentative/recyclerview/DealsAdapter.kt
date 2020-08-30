package com.thebestdiscountandroid.features.deals.presentative.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thebestdiscountandroid.R
import com.thebestdiscountandroid.core.extension.inflate
import com.thebestdiscountandroid.features.wish.presentation.expandablerecyclerview.WishCategoryView
import com.thebestdiscountandroid.features.wish.presentation.expandablerecyclerview.WishProductView
import javax.inject.Inject
import kotlin.properties.Delegates

class DealsAdapter
@Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val headerType = 0
    private val itemType = 1

    internal var collection: ArrayList<DealView> by Delegates.observable(arrayListOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var shortClickListener: (DealItemView) -> Unit = { _ -> }

    internal var longClickListener: (DealItemView) -> Unit = { _ -> }

    override fun getItemViewType(position: Int): Int =
        if (collection[position] is DealHeaderView) headerType else itemType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == itemType) DealItemViewHolder(parent.inflate(R.layout.row_deals))
        else DealsHeaderViewHolder(parent.inflate(R.layout.row_deals_header))

    override fun getItemCount() = collection.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            itemType -> {
                holder as DealItemViewHolder
                val item: DealItemView = collection[position] as DealItemView
                holder.bind(item, shortClickListener, longClickListener)
            }
            headerType -> {
                holder as DealsHeaderViewHolder
                val item: DealHeaderView = collection[position] as DealHeaderView
                holder.bind(item.topDealsHeader)
            }
        }
    }

    internal fun selectDeal(dealItemView: DealItemView) {
        dealItemView.dealSelected = !dealItemView.dealSelected
        notifyDataSetChanged()
    }

    internal fun areThereSelectedItems(): Boolean {
        collection.map {
            if (it is DealItemView) {
                if (it.dealSelected) return true
            }
        }
        return false
    }

    internal fun cancelAllCheckBoxes() {
        for (item in collection) {
            if (item is DealItemView) {
                item.dealSelected = false
            }
        }
        notifyDataSetChanged()
    }
}