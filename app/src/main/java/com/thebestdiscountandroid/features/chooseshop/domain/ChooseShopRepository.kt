package com.thebestdiscountandroid.features.chooseshop.domain

import com.thebestdiscountandroid.core.exception.Failure
import com.thebestdiscountandroid.core.functional.Either

interface ChooseShopRepository {

    fun getShopsByProduct(productId: Int): Either<Failure, List<Shop>>

    fun setLimitOnUserProduct(userId: Int, productName: String, limit: Double): Either<Failure, Any>

    fun getLimitsOnUser(userId: Int): Either<Failure, List<ProductLimit>>

}