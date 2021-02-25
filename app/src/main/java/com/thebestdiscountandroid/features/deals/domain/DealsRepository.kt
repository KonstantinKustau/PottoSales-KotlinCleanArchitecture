package com.thebestdiscountandroid.features.deals.domain

import com.thebestdiscountandroid.core.exception.Failure
import com.thebestdiscountandroid.core.functional.Either

interface DealsRepository {

    fun getTopDeals(userId: Int): Either<Failure, List<DealEntity>>

    fun filterProductByName(userId: Int, productName: String): Either<Failure, List<DealEntity>>

}