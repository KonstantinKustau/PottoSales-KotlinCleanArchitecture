package com.thebestdiscountandroid.features.chooseshop.domain

import com.thebestdiscountandroid.core.exception.Failure
import com.thebestdiscountandroid.core.functional.Either
import com.thebestdiscountandroid.core.interactor.UseCase
import javax.inject.Inject

class GetShopsByProduct
@Inject constructor(private val chooseShopRepository: ChooseShopRepository) :
    UseCase<List<ShopEntity>, GetShopsByProduct.Params>() {

    data class Params(val productId: Int)

    override suspend fun run(params: Params): Either<Failure, List<ShopEntity>> =
        chooseShopRepository.getShopsByProduct(params.productId)
}