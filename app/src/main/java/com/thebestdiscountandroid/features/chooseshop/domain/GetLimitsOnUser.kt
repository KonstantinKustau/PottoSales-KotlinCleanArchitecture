package com.thebestdiscountandroid.features.chooseshop.domain

import com.thebestdiscountandroid.core.exception.Failure
import com.thebestdiscountandroid.core.functional.Either
import com.thebestdiscountandroid.core.interactor.UseCase
import javax.inject.Inject

class GetLimitsOnUser
@Inject constructor(private val chooseShopRepository: ChooseShopRepository) :
    UseCase<List<ProductLimitEntity>, GetLimitsOnUser.Params>() {

    data class Params(val userId: Int)

    override suspend fun run(params: Params): Either<Failure, List<ProductLimitEntity>> =
        chooseShopRepository.getLimitsOnUser(params.userId)

}