package com.thebestdiscountandroid.features.wish.presentation.expandablerecyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thebestdiscountandroid.R
import com.thebestdiscountandroid.core.extension.inflate
import javax.inject.Inject
import kotlin.properties.Delegates

class WishListsAdapter
@Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val categoryType = 0
    private val productType = 1

    /**
     * ArrayList of WishView includes WishCategoryView item and WishProductView
     * The WishCategoryView includes products. Items in these products must match the WishViewProduct in order to avoid errors.
     */
    internal var collection: ArrayList<WishView> by Delegates.observable(arrayListOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var shortClickListener: (WishView) -> Unit = { _ -> }

    internal var longClickListener: (WishView) -> Unit = { _ -> }

    override fun getItemViewType(position: Int): Int =
        if (collection[position] is WishCategoryView) categoryType else productType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == categoryType) CategoryViewHolder(parent.inflate(R.layout.row_category_wish_list))
        else ProductViewHolder(parent.inflate(R.layout.row_wish_list))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            categoryType -> {
                holder as CategoryViewHolder
                val item: WishCategoryView = collection[position] as WishCategoryView
                holder.bind(item, shortClickListener, longClickListener)
            }
            productType -> {
                holder as ProductViewHolder
                val item: WishProductView = collection[position] as WishProductView
                holder.bind(item, shortClickListener, longClickListener)
            }
        }
    }

    override fun getItemCount() = collection.size

    internal fun expandOrCollapseCategory(wishView: WishCategoryView) {
        var startingPositionForChange: Int? = null
        for ((position, item) in collection.withIndex()) {
            if (item is WishCategoryView && item.id == wishView.id) {
                startingPositionForChange = position + 1
                item.categoryExpanded = !item.categoryExpanded
            }
        }

        if (wishView.categoryExpanded) {
            collection.removeAll(wishView.products as List<WishView>)
        } else if (startingPositionForChange != null) {
            collection.addAll(startingPositionForChange, wishView.products as List<WishView>)
        }

        notifyDataSetChanged()
    }

    internal fun selectCategory(wishView: WishCategoryView) {
        wishView.categorySelected = !wishView.categorySelected
        notifyDataSetChanged()
    }

    internal fun selectProduct(wishView: WishProductView) {
        for (item in collection) {
            if (item is WishCategoryView) {
                for (product in item.products) {
                    if (product == wishView) {
                        product.productSelected = !product.productSelected
                        wishView.productSelected = product.productSelected
                    }
                }
            }
        }
        notifyDataSetChanged()
    }

    internal fun areThereSelectedItems(): Boolean {
        collection.map {
            if (it is WishCategoryView) {
                if (it.categorySelected) return true
            } else if (it is WishProductView) {
                if (it.productSelected) return true
            }
        }
        return false
    }

    internal fun cancelAllCheckBoxes() {
        for (item in collection) {
            if (item is WishCategoryView) {
                item.categorySelected = false
            } else {
                (item as WishProductView).productSelected = false
            }
        }
        notifyDataSetChanged()
    }
}