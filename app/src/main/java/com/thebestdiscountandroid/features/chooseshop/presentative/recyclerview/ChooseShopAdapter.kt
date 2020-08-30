package com.thebestdiscountandroid.features.chooseshop.presentative.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thebestdiscountandroid.R
import com.thebestdiscountandroid.core.extension.inflate

import javax.inject.Inject
import kotlin.properties.Delegates

class ChooseShopAdapter
@Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val headerType = 0
    private val itemType = 1

    internal var selectedColor: Int by Delegates.observable(0) { _, _, _ -> run {} }

    internal var collection: ArrayList<ShopView> by Delegates.observable(arrayListOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var shortClickListener: (ShopView) -> Unit = { _ -> }

    internal var longClickListener: (ShopItemView) -> Unit = { _ -> }

    override fun getItemViewType(position: Int): Int =
        if (collection[position] is ShopHeaderView) headerType else itemType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == itemType) ShopItemViewHolder(
            parent.inflate(R.layout.row_choose_shop),
            selectedColor
        )
        else ShopHeaderViewHolder(parent.inflate(R.layout.row_choose_shop_header))

    override fun getItemCount() = collection.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            itemType -> {
                holder as ShopItemViewHolder
                val item: ShopItemView = collection[position] as ShopItemView
                holder.bind(item, shortClickListener, longClickListener)
            }
            headerType -> {
                holder as ShopHeaderViewHolder
                val item: ShopHeaderView = collection[position] as ShopHeaderView
                holder.bind(item.limitHeader, shortClickListener)
            }
        }
    }

    internal fun selectShop(shopItemView: ShopItemView) {
        shopItemView.shopSelected = !shopItemView.shopSelected
        notifyDataSetChanged()
    }

    internal fun areThereSelectedItems(): Boolean {
        collection.map {
            if (it is ShopItemView) {
                if (it.shopSelected) return true
            }
        }
        return false
    }

    internal fun cancelAllCheckBoxes() {
        for (item in collection) {
            if (item is ShopItemView) {
                item.shopSelected = false
            }
        }
        notifyDataSetChanged()
    }
}