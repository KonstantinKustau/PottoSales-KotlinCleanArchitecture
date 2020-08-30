package com.thebestdiscountandroid.features.chooseshop.domain

import com.thebestdiscountandroid.core.exception.Failure
import com.thebestdiscountandroid.core.functional.Either
import com.thebestdiscountandroid.core.interactor.UseCase
import javax.inject.Inject

class SetLimitOnUserProduct
@Inject constructor(private val chooseShopRepository: ChooseShopRepository) :
    UseCase<Any, SetLimitOnUserProduct.Params>() {

    data class Params(val userId: Int, val productName: String, val limit: Double)

    override suspend fun run(params: Params): Either<Failure, Any> =
        chooseShopRepository.setLimitOnUserProduct(params.userId, params.productName, params.limit)
}