package com.thebestdiscountandroid.features.deals.presentative

import androidx.lifecycle.MutableLiveData
import com.thebestdiscountandroid.core.platform.BaseViewModel
import com.thebestdiscountandroid.features.deals.domain.Deal
import com.thebestdiscountandroid.features.deals.domain.GetTopDeals
import com.thebestdiscountandroid.features.deals.presentative.DealsFragment.Companion.DEALS_HEADER
import com.thebestdiscountandroid.features.deals.presentative.recyclerview.DealHeaderView
import com.thebestdiscountandroid.features.deals.presentative.recyclerview.DealItemView
import com.thebestdiscountandroid.features.deals.presentative.recyclerview.DealView
import javax.inject.Inject

class DealsViewModel
@Inject constructor(
    private val getTopDeals: GetTopDeals
) : BaseViewModel() {

    var deals: MutableLiveData<List<DealView>> = MutableLiveData()

    fun loadDeals(userId: Int) {
        getTopDeals(GetTopDeals.Params(userId)) { it ->
            it.fold(
                { handleFailure(it) },
                { handleDeals(it) })
        }
    }

    private fun handleDeals(deals: List<Deal>) {
        val dealsAdapterList: ArrayList<DealView> = arrayListOf()
        dealsAdapterList.add(DealHeaderView(DEALS_HEADER))
        deals.map {
            dealsAdapterList.add(
                DealItemView(
                    it.name,
                    it.productPrice,
                    it.productImage,
                    it.shopName,
                    false
                )
            )
        }
        this.deals.value = dealsAdapterList
    }
}